package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
