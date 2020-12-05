package org.hubson404.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

interface ForecastRepository extends JpaRepository<Forecast, Long> {
}
