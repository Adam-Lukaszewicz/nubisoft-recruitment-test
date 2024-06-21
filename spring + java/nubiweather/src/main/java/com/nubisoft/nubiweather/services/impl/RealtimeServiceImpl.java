package com.nubisoft.nubiweather.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubisoft.nubiweather.Obfuscate;
import com.nubisoft.nubiweather.exceptions.WeatherAPIException;
import com.nubisoft.nubiweather.exceptions.WeatherAPIExceptionHandler;
import com.nubisoft.nubiweather.models.BasicMessage;
import com.nubisoft.nubiweather.models.CurrentWeather;
import com.nubisoft.nubiweather.models.ForecastWeather;
import com.nubisoft.nubiweather.services.RealtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RealtimeServiceImpl implements RealtimeService {

    private RestClient restClient;
    private ObjectMapper objectMapper;

    public RealtimeServiceImpl(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<Map<String, CurrentWeather>> getCurrentWeatherForHamburgAndGliwice() throws WeatherAPIException {
        ResponseEntity<CurrentWeather> responseGliwice = restClient
                .get()
                .uri("/current.json?key={key}&q={cityName}&aqi={aqiBool}", Obfuscate.key, "Gliwice", "no")
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
                .toEntity(CurrentWeather.class);
        ResponseEntity<CurrentWeather> responseHamburg = restClient
                .get()
                .uri("/current.json?key={key}&q={cityName}&aqi={aqiBool}", Obfuscate.key, "Hamburg", "no")
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
                .toEntity(CurrentWeather.class);
        Map<String, CurrentWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return ResponseEntity.status(responseGliwice.getStatusCode()).body(combined);
    }

    @Override
    public ResponseEntity<CurrentWeather> getCurrentWeatherForCity(String cityName) {
        return restClient
                .get()
                .uri("/current.json?key={key}&q={cityName}&aqi={aqiBool}", Obfuscate.key, cityName, "no")
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
                .toEntity(CurrentWeather.class);
    }
}
