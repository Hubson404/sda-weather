package org.hubson404.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    Long id;
    String cityName;
    int latitude;
    int longitude;
    String region;
    String country;
}
