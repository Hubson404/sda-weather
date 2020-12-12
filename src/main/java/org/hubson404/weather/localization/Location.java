package org.hubson404.weather.localization;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.weather.Forecast;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "location")
    List<Forecast> forecastList = new ArrayList<>();
}
