package com.tenera.weather.enums;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeatherTypesEnumTest {

	@ParameterizedTest
	@ValueSource(strings = { "Thunderstorm", "Rain", "Drizzle" })
	public void testIsUmbrellaRequiredValid(String value) {
		assertTrue(WeatherTypesEnum.isUmbrellaRequired(value));

	}

	@ParameterizedTest
	@ValueSource(strings = { "clear" })
	public void testIsUmbrellaRequiredNotValid(String value) {
		assertFalse(WeatherTypesEnum.isUmbrellaRequired(value));

	}
}
