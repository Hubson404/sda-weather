package org.hubson404.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastPersistService {

    private final ForecastRepository forecastRepository;

    public Forecast saveForecastToDatabase(ForecastDTO forecastDTO) {
        Forecast forecast = new Forecast();
        forecast.setTemperature(forecastDTO.getTemperature());
        forecast.setAirPressure(forecastDTO.getAirPressure());
        forecast.setHumidity(forecastDTO.getHumidity());
        forecast.setWindSpeed(forecastDTO.getWindSpeed());
        forecast.setWindDegree(forecastDTO.getWindDegree());
        forecast.setDate(forecastDTO.getDate());
        return forecastRepository.save(forecast);
    }
}
