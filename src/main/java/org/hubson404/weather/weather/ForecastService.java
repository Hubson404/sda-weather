package org.hubson404.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.BadRequestException;
import org.hubson404.weather.exceptions.DataProcessingErrorException;
import org.hubson404.weather.exceptions.PeriodValueOutOfBoundsException;
import org.hubson404.weather.localization.Location;
import org.hubson404.weather.localization.LocationFetchService;
import org.hubson404.weather.localization.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OpenWeatherConfig config;
    private final ForecastMapper forecastMapper;
    private final ForecastRepository forecastRepository;
    private final LocationFetchService locationFetchService;
    private final LocationRepository locationRepository;


    public Forecast getForecast(String locationName, int period) {

        Location location = locationFetchService.getLocationByCityName(locationName);

        UriComponents build = UriComponentsBuilder
                .fromHttpUrl("http://api.openweathermap.org/data/2.5/forecast")
                .queryParam("appid", config.getApiKey())
                .queryParam("q", locationName)
                .queryParam("units", "metric")
                .queryParam("lang", "en")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(build.toUri(), String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from remote service.");
        }

        String responseBody = responseEntity.getBody();
        ForecastApiResponse forecastModel;

        try {
            forecastModel = objectMapper.readValue(responseBody, ForecastApiResponse.class);

        } catch (JsonProcessingException e) {
            throw new DataProcessingErrorException("Unable to process forecast data.");
        } catch (IndexOutOfBoundsException e) {
            throw new PeriodValueOutOfBoundsException("Period value out of bounds, should be between 0 and 5.");
        }

        ForecastDTO forecastDTO = forecastMapper.mapToForecastDto(forecastModel, period,location);
        Forecast forecast = saveForecastToDatabase(forecastDTO);

        location.getForecastList().add(forecast);
        locationRepository.save(location);

        return forecast;
    }

    public Forecast saveForecastToDatabase(ForecastDTO forecastDTO) {
        Forecast forecast = new Forecast();
        forecast.setTemperature(forecastDTO.getTemperature());
        forecast.setAirPressure(forecastDTO.getAirPressure());
        forecast.setHumidity(forecastDTO.getHumidity());
        forecast.setWindSpeed(forecastDTO.getWindSpeed());
        forecast.setWindDegree(forecastDTO.getWindDegree());
        forecast.setDate(forecastDTO.getDate());
        forecast.setLocation(forecastDTO.getLocation());
        return forecastRepository.save(forecast);
    }
}
