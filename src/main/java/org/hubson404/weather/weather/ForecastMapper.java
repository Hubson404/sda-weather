package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.LocationDTO;
import org.hubson404.weather.localization.LocationDefinition;
import org.hubson404.weather.weather.model.ForecastModel;
import org.hubson404.weather.weather.model.Main;
import org.hubson404.weather.weather.model.Wind;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
class ForecastMapper {

    ForecastDTO mapToForecastDto(Forecast newForecast) {
        return ForecastDTO.builder()
                .id(newForecast.getId())
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windSpeed(newForecast.getWindSpeed())
                .windDegree(newForecast.getWindDegree())
                .build();
    }

    ForecastDTO mapToForecastDto(ForecastModel forecastModel, int period) {

        Main main = forecastModel.getListing().get(8*period).getMain();
        Wind wind = forecastModel.getListing().get(8*period).getWind();

        return ForecastDTO.builder()
                .temperature(main.getTemp().toString())
                .airPressure(main.getPressure().toString())
                .humidity(main.getHumidity().toString())
                .windSpeed(wind.getSpeed().toString())
                .windDegree(wind.getDeg().toString())
                .build();
    }
}
