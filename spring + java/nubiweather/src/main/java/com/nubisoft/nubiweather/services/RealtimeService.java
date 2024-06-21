package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.RealtimeWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface RealtimeService {
    ResponseEntity<Map<String, RealtimeWeather>> getCurrentWeatherForHamburgAndGliwice();

    ResponseEntity<RealtimeWeather> getCurrentWeatherForCity(String cityName);
}
