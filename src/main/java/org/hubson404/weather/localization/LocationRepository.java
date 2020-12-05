package org.hubson404.weather.localization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository  extends JpaRepository<Location,Long> {

    Optional<Location> findLocationByCityName(String cityName);

}
