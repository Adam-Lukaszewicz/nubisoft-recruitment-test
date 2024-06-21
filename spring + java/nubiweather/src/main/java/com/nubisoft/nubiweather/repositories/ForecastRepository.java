package com.nubisoft.nubiweather.repositories;

import com.nubisoft.nubiweather.models.entities.ForecastWeatherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<ForecastWeatherEntity, Long> {
}
