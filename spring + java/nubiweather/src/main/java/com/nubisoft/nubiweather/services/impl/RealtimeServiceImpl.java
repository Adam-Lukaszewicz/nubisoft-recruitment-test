package com.nubisoft.nubiweather.services.impl;

import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.services.RealtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RealtimeServiceImpl implements RealtimeService {

    private RestClient restClient;

    public RealtimeServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Map<String, CurrentWeather> getCurrentWeatherForHamburgAndGliwice() {
        ResponseEntity<CurrentWeather> responseGliwice = restClient
                .get()
                .uri("/current.json?key={key}&q={cityName}&aqi={aqiBool}", "**", "Gliwice", "no")
                .retrieve()
                .toEntity(CurrentWeather.class);
        ResponseEntity<CurrentWeather> responseHamburg = restClient
                .get()
                .uri("/current.json?key={key}&q={cityName}&aqi={aqiBool}", "**", "Hamburg", "no")
                .retrieve()
                .toEntity(CurrentWeather.class);
        Map<String, CurrentWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return combined;
    }
}
