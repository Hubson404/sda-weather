package org.hubson404.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hubson404.weather.exceptions.DataProcessingErrorException;
import org.hubson404.weather.localization.Location;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
                .windDirection(newForecast.getWindDirection())
                .date(newForecast.getForecastDate().toString())
                .location(newForecast.getLocation())
                .build();
    }

    public Forecast mapToForecast(ForecastModel forecastModel, int period, Location location) {

        ForecastModel.WeatherListing weatherListing = getWeatherListing(forecastModel, period);
        ForecastModel.WeatherListing.Main main = weatherListing.getMain();
        ForecastModel.WeatherListing.Wind wind = weatherListing.getWind();

        Forecast forecast = new Forecast();
        forecast.setTemperature(main.getTemp().toString());
        forecast.setAirPressure(main.getPressure().toString());
        forecast.setHumidity(main.getHumidity().toString());
        forecast.setWindSpeed(wind.getSpeed().toString());
        forecast.setWindDirection(formatWindDirection(wind.getDeg()));
        forecast.setForecastDate(convertDate(weatherListing.getDtTxt()));
        forecast.setLocation(location);

        return forecast;
    }

    private ForecastModel.WeatherListing getWeatherListing(ForecastModel fM, int period) {

        LocalDateTime soughtDateTime = LocalDate.now().plusDays(period).atTime(12, 0);
        String soughtDateTimeString = soughtDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<ForecastModel.WeatherListing> listing = fM.getListing();

        Optional<ForecastModel.WeatherListing> weatherListing = listing.stream()
                .filter(p -> (convertDate(p.getDtTxt()).equals(convertDate(soughtDateTimeString))))
                .findAny();

        if (weatherListing.isPresent()) {
            return weatherListing.get();
        } else if (period == 0) {
            return listing.get(0);
        } else if (period == 5) {
            return listing.get(listing.size() - 1);
        } else {
            throw new DataProcessingErrorException("Unable to process forecast data.");
        }
    }

    private String formatWindDirection(Integer windDir) {
        String windDirection;

        if (windDir >= 326 || windDir < 34) {
            windDirection = "N";
        } else if (windDir < 79) {
            windDirection = "NE";
        } else if (windDir < 124) {
            windDirection = "E";
        } else if (windDir < 169) {
            windDirection = "SE";
        } else if (windDir < 214) {
            windDirection = "S";
        } else if (windDir < 259) {
            windDirection = "SW";
        } else if (windDir < 304) {
            windDirection = "W";
        } else {
            windDirection = "NW";
        }
        return windDirection;
    }

    private Instant convertDate(String timestamp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime forecastDate = LocalDateTime.parse(timestamp, dateTimeFormatter);
        Instant instant = forecastDate.toInstant(ZoneOffset.systemDefault().getRules().getOffset(forecastDate));
        return instant;
    }

}


