package org.hubson404.weather.weather;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.Location;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ForecastDTO {

    Long id;
    String temperature;
    String airPressure;
    String windDirection;
    String windSpeed;
    String humidity;
    String date;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cityName")
    @JsonIdentityReference(alwaysAsId = true)
    Location location;
}
