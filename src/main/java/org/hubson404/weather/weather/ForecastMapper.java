package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.LocationDTO;
import org.hubson404.weather.localization.LocationDefinition;
import org.hubson404.weather.weather.model.ForecastModel;
import org.hubson404.weather.weather.model.Listing;
import org.hubson404.weather.weather.model.Main;
import org.hubson404.weather.weather.model.Wind;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .date(newForecast.getDate())
                .build();
    }

    ForecastDTO mapToForecastDto(ForecastModel forecastModel, int period) {
        int listingsPerPeriod = 8;
        Listing listing;

        if (period == 0) {
            listing = forecastModel.getListing().get(0);
        } else {
            listing = forecastModel.getListing().get(listingsPerPeriod * period - 1);
        }

        Main main = listing.getMain();
        Wind wind = listing.getWind();

        return ForecastDTO.builder()
                .temperature(main.getTemp().toString())
                .airPressure(main.getPressure().toString())
                .humidity(main.getHumidity().toString())
                .windSpeed(wind.getSpeed().toString())
                .windDegree(wind.getDeg().toString())
                .date(listing.getDt().toString())
                .build();
    }
}
