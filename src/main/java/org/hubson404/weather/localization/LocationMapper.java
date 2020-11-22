package org.hubson404.weather.localization;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class LocationMapper {

    LocationDTO mapToLocationDto(Location newLocation) {

        return LocationDTO.builder()
                .cityName(newLocation.getCityName())
                .longitude(newLocation.getLongitude())
                .latitude(newLocation.getLatitude())
                .regionName(newLocation.getRegionName())
                .countryName(newLocation.getCountryName())
                .build();
    }

    LocationDefinition mapToLocalisationDefinition(LocationDTO DTO) {

        return LocationDefinition.builder()
                .cityName(DTO.getCityName())
                .longitude(DTO.getLongitude())
                .latitude(DTO.getLatitude())
                .regionName(DTO.getRegionName())
                .countryName(DTO.getCountryName())
                .build();
    }
}
