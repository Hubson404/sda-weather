package org.hubson404.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDTO {

    String temperature;
    String airPressure;
    String windDirection;
    String windDegree;
    String windSpeed;
    String humidity;
}
