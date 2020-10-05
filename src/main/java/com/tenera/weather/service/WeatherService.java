package com.tenera.weather.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenera.weather.dto.HistoricalWeatherDataDto;
import com.tenera.weather.dto.WeatherDataDto;
import com.tenera.weather.dto.integration.openWeather.OpenWeatherResult;
import com.tenera.weather.dto.integration.openWeather.Weather;
import com.tenera.weather.entity.WeatherData;
import com.tenera.weather.enums.WeatherTypesEnum;
import com.tenera.weather.repository.WeatherDataRepository;
import com.tenera.weather.service.integration.openWeather.OpenweatherIntegrationService;

@Service
public class WeatherService {

	Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private final OpenweatherIntegrationService openweatheIntegrationService;

	private final WeatherDataRepository weatherDataRepository;

	WeatherService(OpenweatherIntegrationService openweatheIntegrationService,
			WeatherDataRepository weatherDataRepository) {
		this.openweatheIntegrationService = openweatheIntegrationService;
		this.weatherDataRepository = weatherDataRepository;
	}

	public WeatherDataDto getCurrentWeatherData(String city) {

		OpenWeatherResult openWeatherResult = openweatheIntegrationService.getCurrentWeather(city);

		WeatherDataDto weatherDataDto = mapToWeatherDataDto(openWeatherResult);

		saveWeatherData(openWeatherResult, weatherDataDto);

		return weatherDataDto;
	}

	public HistoricalWeatherDataDto getHistoryWeatherData(String city) {

		List<WeatherData> weatherDataList = weatherDataRepository.findTop5ByCityIgnoreCaseOrderByIdDesc(city);

		return getHistoricalWeatherDataDto(weatherDataList);
	}

	HistoricalWeatherDataDto getHistoricalWeatherDataDto(List<WeatherData> weatherDataList) {

		HistoricalWeatherDataDto historicalWeatherDataDto = null;

		if (weatherDataList != null) {
			
			logger.info("size"+ weatherDataList.size());

			historicalWeatherDataDto = new HistoricalWeatherDataDto();

			historicalWeatherDataDto.setAvgPressure(weatherDataList.stream()
					.mapToDouble(weatherData -> weatherData.getPressure()).average().orElse(0.0));
			historicalWeatherDataDto.setAvgTemp(
					weatherDataList.stream().mapToDouble(weatherData -> weatherData.getTemp()).average().orElse(0.0));

			historicalWeatherDataDto.setWeatherDataHistory(weatherDataList.stream()
					.map(weatherData -> mapToWeatherDataDto(weatherData)).collect(Collectors.toList()));

		}

		return historicalWeatherDataDto;

	}

	WeatherDataDto mapToWeatherDataDto(WeatherData weatherData) {

		WeatherDataDto weatherDataDto = new WeatherDataDto();

		weatherDataDto.setTemp(weatherData.getTemp());
		weatherDataDto.setPressure(weatherData.getPressure());
		weatherDataDto.setUmbrella(weatherData.isUmbrella());

		return weatherDataDto;

	}

	WeatherDataDto mapToWeatherDataDto(OpenWeatherResult openWeatherResult) {

		WeatherDataDto weatherDataDto = new WeatherDataDto();

		weatherDataDto.setTemp(openWeatherResult.getMain().getTemp());
		weatherDataDto.setPressure(openWeatherResult.getMain().getPressure());
		weatherDataDto.setUmbrella(iSUmbrellaRequired(openWeatherResult.getWeather()));

		return weatherDataDto;

	}

	boolean iSUmbrellaRequired(List<Weather> weathers) {
		return weathers.stream().anyMatch(weather -> WeatherTypesEnum.isUmbrellaRequired(weather.getMain()));
	}

	void saveWeatherData(OpenWeatherResult openWeatherResult, WeatherDataDto weatherDataDto) {

		WeatherData weatherData = new WeatherData();
		weatherData.setPressure(weatherDataDto.getPressure());
		weatherData.setTemp(weatherDataDto.getTemp());
		weatherData.setUmbrella(weatherDataDto.isUmbrella());
		weatherData.setOpenWeatherId(openWeatherResult.getId());
		weatherData.setCityName(openWeatherResult.getName());
		weatherData.setCountry(openWeatherResult.getSys().getCountry());

		weatherDataRepository.save(weatherData);
	}

}
