package com.tenera.weather.service.integration.openWeather;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tenera.weather.dto.integration.openWeather.OpenWeatherResult;

import constants.ConstantUtilities;

@Service
public class OpenweatherIntegrationService {

	
	private final RestTemplate restTemplate;

    public OpenweatherIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
	public OpenWeatherResult getCurrentWeather(String city) {
		
        String requestUri = ConstantUtilities.OPEN_WEATHER_URL;
		
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("q",city);
		urlParameters.put("units", "metric");
		urlParameters.put("APPID", ConstantUtilities.APPID);
		  

	     ResponseEntity<OpenWeatherResult> entity = restTemplate.getForEntity(requestUri,OpenWeatherResult.class,
		                                                                urlParameters);
	     
	    return entity.getBody();
	}

}
