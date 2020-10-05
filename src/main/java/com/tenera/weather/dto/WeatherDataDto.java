package com.tenera.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDataDto {

	@JsonProperty("temp")
	public double temp;

	@JsonProperty("pressure")
	public double pressure;

	@JsonProperty("umbrella")
	public boolean umbrella;

	public double getTemp() {
		return temp;
	}

	public WeatherDataDto(double temp, double pressure, boolean umbrella) {
		super();
		this.temp = temp;
		this.pressure = pressure;
		this.umbrella = umbrella;
	}

	public WeatherDataDto() {
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public boolean isUmbrella() {
		return umbrella;
	}

	public void setUmbrella(boolean umbrella) {
		this.umbrella = umbrella;
	}

	@Override
	public String toString() {
		return "WeatherData [temp=" + temp + ", pressure=" + pressure + ", umbrella=" + umbrella + "]";
	}

}
