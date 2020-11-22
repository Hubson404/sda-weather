package org.hubson404.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDTO {

    Long id;
    String cityName;
    int latitude;
    int longitude;
    String regionName;
    String countryName;
}
