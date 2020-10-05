package com.tenera.weather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalWeatherDataDto {

	@JsonProperty("avg_temp")
	public double avgTemp;

	@JsonProperty("avg_pressure")
	public double avgPressure;
	
	public HistoricalWeatherDataDto(double avgTemp, double avgPressure) {
		super();
		this.avgTemp = avgTemp;
		this.avgPressure = avgPressure;
	}

	public HistoricalWeatherDataDto() {
	}

	@JsonProperty("history")
	List<WeatherDataDto> weatherDataHistory;

	public double getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}

	public double getAvgPressure() {
		return avgPressure;
	}

	public void setAvgPressure(double avgPressure) {
		this.avgPressure = avgPressure;
	}

	public List<WeatherDataDto> getWeatherDataHistory() {
		return weatherDataHistory;
	}

	public void setWeatherDataHistory(List<WeatherDataDto> weatherDataHistory) {
		this.weatherDataHistory = weatherDataHistory;
	}
	
	

}
