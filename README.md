# Gliwice and Hamburg weather API

## Getting started

To use the application you need to insert your https://www.weatherapi.com/ API key into the Obfuscate class in the project. This is done to avoid leaking your API key.
Next, run the application using the NubiweatherApplication class.
Now you can test the application by going to localhost:8080 and trying to hit one of the endpoints the application exposes (listed in ForecastController and RealtimeController).

## Features implemented

This application implements two controllers:

### Forecast controller

This controller returns the forecasted weather for the next day with in 3 hours long intervals.
It exposes two endpoints:
/forecast-weather - returns the forecasted weather for Hamburg and Gliwice simultaneously,
/forecast-weather/{cityName} - returns the forecasted weather for the given city.

### Realtime controller

This controller returns the current weather.
It exposes two endpoints:
/realtime-weather - returns the current weather for Hamburg and Gliwice simultaneously,
/realtime-weather/{cityName} - returns the current weather for the given city.

### Database integration

The application saves the respones of the weather api to an in memory database. This is done purely as a showcase - the entities are much simpler compared to the models they are supposed to represent and data isn't being read from the database.