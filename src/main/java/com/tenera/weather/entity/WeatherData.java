package com.tenera.weather.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private Integer openWeatherId;
	
	private String city;
	
	private String country;
	
	public double temp;
	
	public double pressure;

	public boolean umbrella;
	

	public double getTemp() {
		return temp;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOpenWeatherId() {
		return openWeatherId;
	}

	public void setOpenWeatherId(Integer openWeatherId) {
		this.openWeatherId = openWeatherId;
	}

	public String getCity() {
		return city;
	}

	public void setCityName(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


}
