package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.Location;
import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
public class Forecast {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String temperature;
    String airPressure;
    String windDirection;
    String windSpeed;
    String humidity;
    Instant forecastDate;

    @ManyToOne
    Location location;
}
