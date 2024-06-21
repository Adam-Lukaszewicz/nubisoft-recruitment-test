package com.nubisoft.nubiweather.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubisoft.nubiweather.Obfuscate;
import com.nubisoft.nubiweather.exceptions.WeatherAPIException;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.services.ForecastService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private RestClient restClient;
    private ObjectMapper objectMapper;

    public ForecastServiceImpl(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, ForecastWeather> getForecastWeatherForHamburgAndGliwice() {
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
        Map<String, ForecastWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return combined;
    }

    @Override
    public ResponseEntity<ForecastWeather> getForecastWeatherForCity(String cityName) {
        return restClient
                .get()
                .uri("/forecast.json?key={key}&q={cityName}&aqi={aqiBool}&days={daysNumber}&alerts={alertsBool}", Obfuscate.key, "", "no", 1, "no")
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
    }
}
