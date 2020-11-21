package org.hubson404.weather.localization;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class LocationMapper {

    LocationDTO mapToLocationDto(Location newLocation) {

        LocationDTO locationDto = new LocationDTO();
        locationDto.setId(newLocation.getId());
        locationDto.setCityName(newLocation.getCityName());
        locationDto.setLongitude(newLocation.getLongitude());
        locationDto.setLatitude(newLocation.getLatitude());
        locationDto.setRegionName(newLocation.getRegionName());
        locationDto.setCountryName(newLocation.getCountryName());

        return locationDto;
    }
}
