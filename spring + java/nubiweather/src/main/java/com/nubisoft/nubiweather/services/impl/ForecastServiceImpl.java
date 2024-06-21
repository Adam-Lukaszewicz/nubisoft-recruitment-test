package com.nubisoft.nubiweather.services.impl;

import com.nubisoft.nubiweather.Obfuscate;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.services.ForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class ForecastServiceImpl implements ForecastService {

    private RestClient restClient;

    public ForecastServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Map<String, ForecastWeather> getForecastWeatherForHamburgAndGliwice() {
        ResponseEntity<ForecastWeather> responseGliwice = restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, "Gliwice", "no", 1, "no")
                .retrieve()
                .toEntity(ForecastWeather.class);
        ResponseEntity<ForecastWeather> responseHamburg = restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, "Hamburg", "no", 1, "no")
                .retrieve()
                .toEntity(ForecastWeather.class);
        Map<String, ForecastWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return combined;
    }
}
