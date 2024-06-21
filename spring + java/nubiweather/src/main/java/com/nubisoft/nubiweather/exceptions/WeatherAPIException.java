package com.nubisoft.nubiweather.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class WeatherAPIException extends RuntimeException {
    private HttpStatusCode httpStatusCode;
    public WeatherAPIException(final String message, final HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
