package org.hubson404.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.InsufficientDataException;
import org.hubson404.weather.exceptions.InvalidDataException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LocationCreateService {

    private final LocationRepository locationRepository;

    public Location createLocation(LocationDefinition ld) {
        if (ld.getCityName().isBlank()) {
            throw new InsufficientDataException("CityName cannot be empty");
        }
        if (ld.getCountryName().isBlank()) {
            throw new InsufficientDataException("CountryName cannot be empty");
        }

        if (ld.getLongitude() < -90 || ld.getLongitude() > 90) {
            throw new InvalidDataException("Longitude value " + ld.getLongitude() + " is invalid. Pass values between -90 and 90.");
        }
        if (ld.getLatitude() < -180 || ld.getLatitude() > 180) {
            throw new InvalidDataException("Latitude value " + ld.getLatitude() + " is invalid. Pass values between -180 and 180.");
        }
        Location location = new Location();
        location.setCityName(ld.getCityName());
        location.setLongitude(ld.getLongitude());
        location.setLatitude(ld.getLatitude());
        location.setRegionName(ld.getRegionName());
        location.setCountryName(ld.getCountryName());

        return locationRepository.save(location);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void locationRepositoryInit(){
        Location location1 = new Location();
        location1.setCityName("Warsaw");
        location1.setLongitude(21);
        location1.setLatitude(52);
        location1.setCountryName("Poland");

        Location location2= new Location();
        location2.setCityName("Gdansk");
        location2.setLongitude(18);
        location2.setLatitude(54);
        location2.setCountryName("Poland");

        locationRepository.save(location1);
        locationRepository.save(location2);

    }
}
