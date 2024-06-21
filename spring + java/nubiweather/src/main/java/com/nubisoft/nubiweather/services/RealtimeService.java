package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.models.ForecastWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface RealtimeService {
    ResponseEntity<Map<String, CurrentWeather>> getCurrentWeatherForHamburgAndGliwice();

    ResponseEntity<CurrentWeather> getCurrentWeatherForCity(String cityName);
}
