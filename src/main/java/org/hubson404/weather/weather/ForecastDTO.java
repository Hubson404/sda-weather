package org.hubson404.weather.weather;

import lombok.Data;

@Data
public class ForecastDTO {

    String temperature;
    String airPressure;
    String windDirection;
    String windDegree;
    String windSpeed;
    String humidity;
}
