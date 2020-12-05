package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.NotFoundException;
import org.hubson404.weather.exceptions.UnknownLocationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't find location with id " + id));
    }

    public Location getLocationByCityName(String cityName) {
        return locationRepository.findLocationByCityName(cityName)
                .orElseThrow(() -> new UnknownLocationException("Couldn't find location: " + cityName));
    }
}
