package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LocationController {

    private final LocationCreateService locationCreateService;
    private final LocationFetchService locationFetchService;
    private final LocationMapper locationMapper;

    @GetMapping("/locations")
    List<LocationDTO> getAllLocations() {
        return locationFetchService.getAllLocations().stream()
                .map(locationMapper::mapToLocationDto).collect(Collectors.toList());
    }

    @GetMapping("/locations/{id}")
    LocationDTO getLocationById(@PathVariable Long id) {
        Location location = locationFetchService.getLocationById(id);
        return locationMapper.mapToLocationDto(location);
    }

//    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
