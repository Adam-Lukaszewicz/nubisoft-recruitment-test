package com.nubisoft.nubiweather.services.impl;

import com.nubisoft.nubiweather.services.ForecastService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ForecastServiceImpl implements ForecastService {

    private RestClient restClient;

    public ForecastServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }
}
