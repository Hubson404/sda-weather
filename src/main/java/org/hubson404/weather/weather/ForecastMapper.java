package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.localization.Location;
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
                .date(newForecast.getDate())
                .build();
    }

    ForecastDTO mapToForecastDto(ForecastApiResponse forecastApiResponse, int period, Location location) {
        int listingsPerPeriod = 8;

        ForecastApiResponse.WeatherListing weatherListing;

        if (period == 0) {
            weatherListing = forecastApiResponse.getListing().get(0);
        } else {
            weatherListing = forecastApiResponse.getListing().get(listingsPerPeriod * period - 1);  // todo it's genius!
        }

        ForecastApiResponse.WeatherListing.Main main = weatherListing.getMain();
        ForecastApiResponse.WeatherListing.Wind wind = weatherListing.getWind();
        Integer windDirectionInDegrees = wind.getDeg();

        return ForecastDTO.builder()
                .temperature(main.getTemp().toString())
                .airPressure(main.getPressure().toString())
                .humidity(main.getHumidity().toString())
                .windSpeed(wind.getSpeed().toString())
                .windDegree(windDirectionInDegrees.toString())
                .date(weatherListing.getDt().toString())
                .location(location)
                .build();
    }
}
