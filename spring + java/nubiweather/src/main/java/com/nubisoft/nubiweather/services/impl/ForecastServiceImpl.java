package com.nubisoft.nubiweather.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubisoft.nubiweather.Obfuscate;
import com.nubisoft.nubiweather.exceptions.WeatherAPIException;
import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.models.entities.ForecastWeatherEntity;
import com.nubisoft.nubiweather.repositories.ForecastRepository;
import com.nubisoft.nubiweather.services.ForecastService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastServiceImpl implements ForecastService {

    private RestClient restClient;
    private ObjectMapper objectMapper;
    private ForecastRepository forecastRepository;

    public ForecastServiceImpl(RestClient restClient, ObjectMapper objectMapper, ForecastRepository forecastRepository) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
        this.forecastRepository = forecastRepository;
    }

    @Override
    public ResponseEntity<Map<String, ForecastWeather>> getForecastWeatherForHamburgAndGliwice() {
        ResponseEntity<ForecastWeather> responseGliwice = restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, "Gliwice", "no", 1, "no")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .toEntity(ForecastWeather.class);
        ResponseEntity<ForecastWeather> responseHamburg = restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, "Hamburg", "no", 1, "no")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .toEntity(ForecastWeather.class);
        if(responseGliwice.getBody() != null){
            List forecasts = objectMapper.convertValue(responseGliwice.getBody().forecast().get("forecastday"), List.class);
            ForecastWeatherEntity forecastWeather = new ForecastWeatherEntity(responseGliwice.getBody().location().get("name").toString(), responseGliwice.getBody().current().get("temp_c").toString(), forecasts.toArray().length);
            forecastRepository.save(forecastWeather);
        }
        if(responseHamburg.getBody() != null){
            List forecasts = objectMapper.convertValue(responseHamburg.getBody().forecast().get("forecastday"), List.class);
            ForecastWeatherEntity forecastWeather = new ForecastWeatherEntity(responseHamburg.getBody().location().get("name").toString(), responseHamburg.getBody().current().get("temp_c").toString(), forecasts.toArray().length);
            forecastRepository.save(forecastWeather);
        }
        Map<String, ForecastWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return ResponseEntity.status(responseGliwice.getStatusCode()).body(combined);
    }

    @Override
    public ResponseEntity<ForecastWeather> getForecastWeatherForCity(String cityName) {
        ResponseEntity<ForecastWeather> responseCity = restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, cityName, "no", 1, "no")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    Map<String, Object> errorResponse = objectMapper.readValue(response.getBody(), Map.class);
                    Map<String, Object> errorMessage = objectMapper.convertValue(errorResponse.get("error"), Map.class);
                    throw new WeatherAPIException(errorMessage.get("message").toString(), response.getStatusCode());
                })
                .toEntity(ForecastWeather.class);
        if(responseCity.getBody() != null){
            List forecasts = objectMapper.convertValue(responseCity.getBody().forecast().get("forecastday"), List.class);
            ForecastWeatherEntity forecastWeather = new ForecastWeatherEntity(responseCity.getBody().location().get("name").toString(), responseCity.getBody().current().get("temp_c").toString(), forecasts.toArray().length);
            forecastRepository.save(forecastWeather);
        }
        return responseCity;
    }
}
