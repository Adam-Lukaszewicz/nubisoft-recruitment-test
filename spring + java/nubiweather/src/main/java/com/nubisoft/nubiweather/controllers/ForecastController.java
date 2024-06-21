package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.services.impl.ForecastServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ForecastController {

    private ForecastServiceImpl forecastService;

    public ForecastController(ForecastServiceImpl forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast-weather/")
    Map<String, ForecastWeather> getForecastWeatherForSetCities() {
        return forecastService.getForecastWeatherForHamburgAndGliwice();
    }

    @GetMapping("/forecast-weather/{cityName}")
    ResponseEntity<ForecastWeather> getForecastWeatherForCity(@PathVariable String cityName) {
        return forecastService.getForecastWeatherForCity(cityName);
    }
}
