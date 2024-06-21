package com.nubisoft.nubiweather.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubisoft.nubiweather.Obfuscate;
import com.nubisoft.nubiweather.exceptions.WeatherAPIException;
import com.nubisoft.nubiweather.models.RealtimeWeather;
import com.nubisoft.nubiweather.models.entities.RealtimeWeatherEntity;
import com.nubisoft.nubiweather.repositories.RealtimeRepository;
import com.nubisoft.nubiweather.services.RealtimeService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class RealtimeServiceImpl implements RealtimeService {

    private RestClient restClient;
    private ObjectMapper objectMapper;
    private RealtimeRepository realtimeRepository;

    public RealtimeServiceImpl(RestClient restClient, ObjectMapper objectMapper, RealtimeRepository realtimeRepository) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
        this.realtimeRepository = realtimeRepository;
    }

    @Override
    public ResponseEntity<Map<String, RealtimeWeather>> getCurrentWeatherForHamburgAndGliwice() throws WeatherAPIException {
        ResponseEntity<RealtimeWeather> responseGliwice = restClient
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
                .toEntity(RealtimeWeather.class);
        ResponseEntity<RealtimeWeather> responseHamburg = restClient
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
                .toEntity(RealtimeWeather.class);
        if(responseGliwice.getBody() != null){
            RealtimeWeatherEntity weatherEntity = new RealtimeWeatherEntity(responseGliwice.getBody().location().get("name").toString(), responseGliwice.getBody().current().get("temp_c").toString());
            realtimeRepository.save(weatherEntity);
        }
        if(responseHamburg.getBody() != null){
            RealtimeWeatherEntity weatherEntity = new RealtimeWeatherEntity(responseHamburg.getBody().location().get("name").toString(), responseHamburg.getBody().current().get("temp_c").toString());
            realtimeRepository.save(weatherEntity);
        }
        Map<String, RealtimeWeather> combined = new HashMap<>();
        combined.put("Gliwice", responseGliwice.getBody());
        combined.put("Hamburg", responseHamburg.getBody());
        return ResponseEntity.status(responseGliwice.getStatusCode()).body(combined);
    }

    @Override
    public ResponseEntity<RealtimeWeather> getCurrentWeatherForCity(String cityName) {
        ResponseEntity<RealtimeWeather> responseCity = restClient
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
                .toEntity(RealtimeWeather.class);
        if(responseCity.getBody() != null){
            RealtimeWeatherEntity weatherEntity = new RealtimeWeatherEntity(responseCity.getBody().location().get("name").toString(), responseCity.getBody().current().get("temp_c").toString());
            realtimeRepository.save(weatherEntity);
        }
        return responseCity;
    }
}
