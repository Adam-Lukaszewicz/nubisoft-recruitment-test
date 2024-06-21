package com.nubisoft.nubiweather.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {
    @Bean
    public RestClient restClient(){
        return RestClient.create("https://api.weatherapi.com/v1/");
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
