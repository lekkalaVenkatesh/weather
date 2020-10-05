package com.tenera.weather.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tenera.weather.dto.WeatherDataDto;
import com.tenera.weather.dto.integration.openWeather.Main;
import com.tenera.weather.dto.integration.openWeather.OpenWeatherResult;
import com.tenera.weather.dto.integration.openWeather.Weather;
import com.tenera.weather.entity.WeatherData;
import com.tenera.weather.repository.WeatherDataRepository;
import com.tenera.weather.service.integration.openWeather.OpenweatherIntegrationService;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {
	
	private static OpenWeatherResult openWeatherResult;

	private static WeatherService weatherService;

	@Mock
	private static WeatherDataRepository weatherDataRepository;

	@Mock
	private static OpenweatherIntegrationService openweatheIntegrationService;

	@BeforeAll
	static void initialize() {
		weatherService = new WeatherService(openweatheIntegrationService, weatherDataRepository);
		openWeatherResult = initilaizeOpenWeatherResult();
	}

	@Test
	public void mapToWeatherDataDto_WeatherData() {
		WeatherData weatherData = new WeatherData();
		weatherData.setTemp(18.0d);
		WeatherDataDto weatherDataDto = weatherService.mapToWeatherDataDto(weatherData);
		assertEquals(18.0d,weatherDataDto.getTemp());
	}

	@Test
	public void mapToWeatherDataDto_OpenWeatherResult() {
		WeatherDataDto weatherDataDto = weatherService.mapToWeatherDataDto(openWeatherResult);
		assertEquals(18.0d,weatherDataDto.getTemp());
	}

	static OpenWeatherResult initilaizeOpenWeatherResult() {
		OpenWeatherResult openWeatherResult = new OpenWeatherResult();

		List<Weather> weatherList = new ArrayList<Weather>();
		Weather weather = new Weather();
		weather.setMain("Rain");
		weatherList.add(weather);
		
		openWeatherResult.setWeather(weatherList);

		Main main = new Main();
		main.setTemp(18.0d);
		main.setPressure(181);
		openWeatherResult.setMain(main);

		return openWeatherResult;
	}

}
