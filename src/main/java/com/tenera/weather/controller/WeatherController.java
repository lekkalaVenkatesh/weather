package com.tenera.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenera.weather.dto.HistoricalWeatherDataDto;
import com.tenera.weather.dto.WeatherDataDto;
import com.tenera.weather.service.WeatherService;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

	Logger logger = LoggerFactory.getLogger(WeatherController.class);

	private final WeatherService weatherService;

	WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("/current")
	public WeatherDataDto getCurrentWeather(@RequestParam(required = true) String location) {
		return weatherService.getCurrentWeatherData(location);
	}

	@GetMapping("/history")
	public HistoricalWeatherDataDto getHistoryWeather(@RequestParam(required = true) String location) {
		return weatherService.getHistoryWeatherData(location);
	}

}
