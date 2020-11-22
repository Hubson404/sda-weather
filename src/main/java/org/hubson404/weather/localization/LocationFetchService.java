package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location fetchLocationById(String id) {
        return locationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Couldn't find location with id " + id));
    }
}
