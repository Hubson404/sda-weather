package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationCreateService locationCreateService;
    private final LocationFetchService locationFetchService;
    private final LocationMapper locationMapper;

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationFetchService.getAllLocations();
    }

    @GetMapping("/locations/{id}")
    LocationDTO getEntries(@PathVariable String id) {
        Location location = locationFetchService.fetchEntry(id);
        return locationMapper.mapToLocationDto(location);
    }

    @PostMapping("/locations")
    ResponseEntity<LocationDTO> creteEntry(@RequestBody LocationDTO locationDTO) {

        LocationDefinition locationDefinition = new LocationDefinition(
                locationDTO.getCityName(),
                locationDTO.getLongitude(),
                locationDTO.getLatitude(),
                locationDTO.getRegionName(),
                locationDTO.getCountryName());

        Location newLocation = locationCreateService.createLocation(locationDefinition);

        log.info(newLocation.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapToLocationDto(newLocation));
    }
}
