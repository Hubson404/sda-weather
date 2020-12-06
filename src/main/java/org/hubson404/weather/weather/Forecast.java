package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.Location;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Forecast {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String temperature;
    String airPressure;
    String windDegree;
    String windSpeed;
    String humidity;
    String date;

    @ManyToOne
    Location location;
}
