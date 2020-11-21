package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationCreateService locationCreateService;
    private final LocationFetchService locationFetchService;
    private final LocationMapper locationMapper;

    @GetMapping("/locations")
    public Iterable<Location> getAllLocations() {
        return locationCreateService.getAllLocations();
    }

    @GetMapping("/locations/{id}")
    LocationDTO getEntries(@PathVariable String id) {
        Location location = locationFetchService.fetchEntry(id);
        return locationMapper.mapToLocationDto(location);
    }

    @PostMapping("/locations")
    ResponseEntity<LocationDTO> creteEntry(@RequestBody LocationDTO locationDTO) {
        String cityName = locationDTO.getCityName();
        int longitude = locationDTO.getLongitude();
        int latitude = locationDTO.getLatitude();
        String regionName = locationDTO.getRegion();
        String coutryName = locationDTO.getCountry();
        Location newLocation = locationCreateService.createLocation(
                cityName,
                longitude,
                latitude,
                regionName,
                coutryName
        );

        log.info(newLocation.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapToLocationDto(newLocation));
    }


}
