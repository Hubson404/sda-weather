package org.hubson404.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

   Optional<Forecast> findForecastByDate(LocalDateTime forecastDate);
}
