package com.nubisoft.nubiweather.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ForecastControllerIntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    public ForecastControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatSetCitiesEndpointReturns200() throws Exception {
        mockMvc.perform(get("/realtime-weather/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testThatCityEndpointReturns200() throws Exception {
        mockMvc.perform(get("/realtime-weather/Zabrze").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void testThatCityEndpointReturns400OnInvalidCity() throws Exception {
        mockMvc.perform(get("/realtime-weather/ZVC").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
