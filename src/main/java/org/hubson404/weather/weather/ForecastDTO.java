package org.hubson404.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ForecastDTO {

    Long id;
    String temperature;
    String airPressure;
    String windDegree;      // todo N, W, S, E, NW, SE 150 -> string
    String windSpeed;
    String humidity;
    String date;            // todo timestamp -> LocalDateTime
}
