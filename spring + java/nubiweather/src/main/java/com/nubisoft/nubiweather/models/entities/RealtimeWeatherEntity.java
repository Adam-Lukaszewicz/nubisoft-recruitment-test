package com.nubisoft.nubiweather.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class RealtimeWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String location;

    private String current;

    public RealtimeWeatherEntity() {}
    public RealtimeWeatherEntity(String location, String current) {
        this.location = location;
        this.current = current;
    }
}
