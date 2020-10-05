package com.tenera.weather.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tenera.weather.dto.HistoricalWeatherDataDto;
import com.tenera.weather.dto.WeatherDataDto;
import com.tenera.weather.service.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherController.class)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@Test
	public void getCurrentWeather_valid() throws Exception {

		Double value = 1.0;
		WeatherDataDto weatherDataDto = new WeatherDataDto(value, value, true);

		when(weatherService.getCurrentWeatherData(Mockito.anyString())).thenReturn(weatherDataDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/weather/current?location=berlin")).andExpect(status().isOk())
				.andExpect(jsonPath("umbrella").value("true"));

	}

	@Test
	public void getCurrentWeather_BadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/weather/current")).andExpect(status().isBadRequest());

	}

	@Test
	public void getHistoryWeather_valid() throws Exception {

		Double value = 1.0;
		when(weatherService.getHistoryWeatherData(Mockito.anyString()))
				.thenReturn(new HistoricalWeatherDataDto(value, value));

		mockMvc.perform(MockMvcRequestBuilders.get("/weather/history?location=berlin")).andExpect(status().isOk())
				.andExpect(jsonPath("avg_pressure").value(value));

	}

	@Test
	public void getHistoryWeather_BadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/weather/history")).andExpect(status().isBadRequest());
	}
}
