package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.services.impl.RealtimeServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealtimeController {

    private RealtimeServiceImpl realtimeService;

    public RealtimeController(RealtimeServiceImpl realtimeService) {
        this.realtimeService = realtimeService;
    }
}
