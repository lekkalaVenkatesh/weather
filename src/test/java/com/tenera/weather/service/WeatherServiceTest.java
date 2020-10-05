package com.tenera.weather.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tenera.weather.enums.WeatherTypesEnum;

public class WeatherServiceTest {

	static WeatherService weatherService;

	@BeforeAll
	static void initialize() {
		
	}

	@Test
	public void testWeatherType() {

		String rainWatherType = "Rain";

		assertTrue(WeatherTypesEnum.umbrellaWeatherType.contains(rainWatherType.toUpperCase()));

	}

}
