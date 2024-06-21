package com.nubisoft.nubiweather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {
    @Bean
    public RestClient restClient(){
        return RestClient.builder().baseUrl("https://api.weatherapi.com/v1").build();
    }
}
