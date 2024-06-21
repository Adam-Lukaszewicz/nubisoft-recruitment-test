package com.nubisoft.nubiweather.models;

import java.util.Map;

public record CurrentWeather(Map<String, Object> location, Map<String,Object> current) {
}
