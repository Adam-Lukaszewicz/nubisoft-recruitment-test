package com.nubisoft.nubiweather.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WeatherAPIExceptionHandler {
    @ExceptionHandler(WeatherAPIException.class)
    public ResponseEntity<WeatherAPIExceptionResponse> handleWeatherAPIException(WeatherAPIException e) {
        return ResponseEntity
                .status(e.getHttpStatusCode())
                .body(new WeatherAPIExceptionResponse(e.getMessage(), e.getHttpStatusCode()));
    }
}
