package org.hubson404.weather.localization;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String cityName;
    int latitude;
    int longitude;
    String regionName;
    String countryName;
}
