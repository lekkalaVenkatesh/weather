package com.tenera.weather.enums;

import java.util.EnumSet;

public enum WeatherTypesEnum {

	THUNDERSTORM, DRIZZLE, RAIN, SNOW, ATMOSPHERE, CLEAR, CLOUDS;

	public static EnumSet<WeatherTypesEnum> umbrellaWeatherType = EnumSet.of(WeatherTypesEnum.THUNDERSTORM,
			WeatherTypesEnum.DRIZZLE, WeatherTypesEnum.RAIN);

	static public boolean isUmbrellaRequired(String value) {
		for (WeatherTypesEnum weatherType : umbrellaWeatherType) {
			if (weatherType.name().equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

}