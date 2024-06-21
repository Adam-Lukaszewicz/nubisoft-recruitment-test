package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.ForecastWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ForecastService {
    Map<String, ForecastWeather> getForecastWeatherForHamburgAndGliwice();

    ResponseEntity<ForecastWeather> getForecastWeatherForCity(String cityName);
}
