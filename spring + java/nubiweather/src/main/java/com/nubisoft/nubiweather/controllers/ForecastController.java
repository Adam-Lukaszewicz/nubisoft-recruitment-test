package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.services.impl.ForecastServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    private ForecastServiceImpl forecastService;

    public ForecastController(ForecastServiceImpl forecastService) {
        this.forecastService = forecastService;
    }
}
