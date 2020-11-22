package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hubson404.weather.weather.ForecastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationCreateService locationCreateService;
    private final LocationFetchService locationFetchService;
    private final LocationMapper locationMapper;
    private final ForecastService forecastService;

    @GetMapping("/locations")
    List<LocationDTO> getAllLocations() {
        return locationFetchService.getAllLocations().stream()
                .map(locationMapper::mapToLocationDto).collect(Collectors.toList());
    }

    @GetMapping("/locations/{id}")
    LocationDTO getLocationById(@PathVariable String id) {
        Location location = locationFetchService.fetchLocationById(id);
        return locationMapper.mapToLocationDto(location);
    }

    @PostMapping("/locations")
    ResponseEntity<LocationDTO> creteLocation(@RequestBody LocationDTO locationDTO) {

        LocationDefinition locationDefinition = locationMapper.mapToLocalisationDefinition(locationDTO);
        Location newLocation = locationCreateService.createLocation(locationDefinition);

        log.info(newLocation.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapToLocationDto(newLocation));
    }


}
