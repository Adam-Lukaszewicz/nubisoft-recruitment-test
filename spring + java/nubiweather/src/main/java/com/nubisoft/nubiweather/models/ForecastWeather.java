package com.nubisoft.nubiweather.models;

import java.util.Map;

public record ForecastWeather(Map<String, Object> location, Map<String,Object> current, Map<String,Object> forecast){
}
