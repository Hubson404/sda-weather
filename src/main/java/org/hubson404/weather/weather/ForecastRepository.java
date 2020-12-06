package org.hubson404.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

   Optional<Forecast> findForecastByForecastDate(Instant forecastDate);
   List<Forecast> findByLocation_CityName(String cityName);
}
