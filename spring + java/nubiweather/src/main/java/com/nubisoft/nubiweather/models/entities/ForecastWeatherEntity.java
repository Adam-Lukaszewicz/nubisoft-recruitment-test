package com.nubisoft.nubiweather.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ForecastWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String location;

    private String current;

    private int forecastDays;

    public ForecastWeatherEntity() {}
    public ForecastWeatherEntity(String location, String current, int forecastDays) {
        this.location = location;
        this.current = current;
        this.forecastDays = forecastDays;
    }
}
