package org.hubson404.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDefinition {

    String cityName;
    int longitude;
    int latitude;
    String regionName;
    String countryName;
}
