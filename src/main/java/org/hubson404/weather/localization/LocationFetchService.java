package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.InsufficientDataException;
import org.hubson404.weather.exceptions.InvalidDataException;
import org.hubson404.weather.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public Location fetchEntry(String id) {
        return locationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Couldn't find location with id " + id));
    }
}
