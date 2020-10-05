package com.tenera.weather.service.integration;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenera.weather.dto.integration.openWeather.OpenWeatherResult;

import constants.ConstantUtilities;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.test.properties")
public class OpenWeatherIntegrationServiceTest {

	String requestUri = ConstantUtilities.OPEN_WEATHER_URL;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testValidCity() throws Exception {
		
		String cityName = "London";

		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("q", cityName);
		setAppId(urlParameters);

		ResponseEntity<OpenWeatherResult> response = restTemplate.getForEntity(requestUri, OpenWeatherResult.class,
				urlParameters);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getName()).isEqualToIgnoringCase(cityName);

	}
	
	@Test
	public void testInValidCity() throws Exception {
		
		String cityName = "Berl";

		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("q", cityName);
		setAppId(urlParameters);

		ResponseEntity<OpenWeatherResult> response = restTemplate.getForEntity(requestUri, OpenWeatherResult.class,
				urlParameters);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}
	
	void setAppId(Map<String, String> urlParameters){
		urlParameters.put("APPID", ConstantUtilities.APPID);
	}

}
