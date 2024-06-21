package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.services.impl.RealtimeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public BasicMessage index() {
        return new BasicMessage("Nubiweather API");
    }

}