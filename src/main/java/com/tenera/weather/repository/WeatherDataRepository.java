package com.tenera.weather.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tenera.weather.entity.WeatherData;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Long>{
	
	List<WeatherData> findTop5ByCityIgnoreCaseOrderByIdDesc(String city);
}
