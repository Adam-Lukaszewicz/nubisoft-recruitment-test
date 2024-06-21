package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface RealtimeService {
    Map<String, CurrentWeather> getCurrentWeatherForHamburgAndGliwice();
}
