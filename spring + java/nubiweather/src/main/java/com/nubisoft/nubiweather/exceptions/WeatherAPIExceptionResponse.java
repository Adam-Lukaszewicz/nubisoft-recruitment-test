package com.nubisoft.nubiweather.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public class WeatherAPIExceptionResponse {
    private String message;
    private HttpStatusCode statusCode;
    public WeatherAPIExceptionResponse(String message, HttpStatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
