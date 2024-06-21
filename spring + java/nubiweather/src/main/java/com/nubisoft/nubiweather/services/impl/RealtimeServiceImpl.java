package com.nubisoft.nubiweather.services.impl;

import com.nubisoft.nubiweather.services.RealtimeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RealtimeServiceImpl implements RealtimeService {

    private RestClient restClient;

    public RealtimeServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }
}
