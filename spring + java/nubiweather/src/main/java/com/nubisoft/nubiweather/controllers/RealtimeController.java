package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.services.impl.RealtimeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RealtimeController {

    private RealtimeServiceImpl realtimeService;

    public RealtimeController(RealtimeServiceImpl realtimeService) {
        this.realtimeService = realtimeService;
    }

    @GetMapping("/realtime-weather/")
    public Map<String, CurrentWeather> realtimeWeather() {
        return realtimeService.getCurrentWeatherForHamburgAndGliwice();
    }
}
