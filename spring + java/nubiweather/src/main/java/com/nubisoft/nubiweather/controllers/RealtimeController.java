package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.services.impl.RealtimeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RealtimeController {

    private RealtimeServiceImpl realtimeService;

    public RealtimeController(RealtimeServiceImpl realtimeService) {
        this.realtimeService = realtimeService;
    }

    @GetMapping("/realtime-weather/")
    public ResponseEntity<Map<String, CurrentWeather>> realtimeWeatherForSetCities() {
        return realtimeService.getCurrentWeatherForHamburgAndGliwice();
    }

    @GetMapping("/realtime-weather/{cityName}")
    public ResponseEntity<CurrentWeather> realtimeWeatherForCity(@PathVariable String cityName) {
        return realtimeService.getCurrentWeatherForCity(cityName);
    }
}
