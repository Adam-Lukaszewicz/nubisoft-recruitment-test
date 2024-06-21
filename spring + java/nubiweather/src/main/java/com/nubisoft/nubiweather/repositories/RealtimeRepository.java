package com.nubisoft.nubiweather.repositories;

import com.nubisoft.nubiweather.models.entities.RealtimeWeatherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtimeRepository extends CrudRepository<RealtimeWeatherEntity, Long> {
}
