package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.InsufficientDataException;
import org.hubson404.weather.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationCreateService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() { // todo move to another service
        return locationRepository.findAll();
    }

    public Location createLocation(String cityName,
                                   int latitude,
                                   int longitude,
                                   String region,
                                   String country
    ) {
        if (cityName.isEmpty()) {
            throw new InsufficientDataException("CityName cannot be empty");
        }
        if (region.isEmpty()) {
            throw new InsufficientDataException("RegionName cannot be empty");
        }
        if (country.isEmpty()) {
            throw new InsufficientDataException("CountryName cannot be empty");
        }
        if (longitude < -90 || longitude > 90) {
            throw new InvalidDataException("Longitude value is invalid");   // todo pass this value
        }
        if (latitude < -180 || latitude > 180) {
            throw new InvalidDataException("Latitude value is invalid");
        }

        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setRegion(region);
        location.setCountry(country);

        return locationRepository.save(location);
    }

}
