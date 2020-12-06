package org.hubson404.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.BadRequestException;
import org.hubson404.weather.exceptions.DataProcessingErrorException;
import org.hubson404.weather.exceptions.PeriodValueOutOfBoundsException;
import org.hubson404.weather.localization.Location;
import org.hubson404.weather.localization.LocationFetchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OpenWeatherConfig config;
    private final ForecastMapper forecastMapper;
    private final ForecastRepository forecastRepository;
    private final LocationFetchService locationFetchService;

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
        Forecast forecast;

        try {
            ForecastModel forecastModel = objectMapper.readValue(responseBody, ForecastModel.class);
            forecast = forecastMapper.mapToForecast(forecastModel, period, location);
        } catch (JsonProcessingException e) {
            throw new DataProcessingErrorException("Unable to process forecast data.");
        } catch (IndexOutOfBoundsException e) {
            throw new PeriodValueOutOfBoundsException("Period value out of bounds, should be between 1 and 5.");
        }
        return checkIfForecastAlreadyExists(forecast);
    }

    private Forecast checkIfForecastAlreadyExists(Forecast newForecast) {
        Optional<Forecast> existingForecast = forecastRepository.findForecastByDate(newForecast.getDate());
        return existingForecast.orElseGet(() -> forecastRepository.save(newForecast));
    }

    public List<Forecast> getForecastByCityName(String location) {
        return forecastRepository.findByLocation_CityName(location);
    }
}
