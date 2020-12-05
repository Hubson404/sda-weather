package org.hubson404.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hubson404.weather.exceptions.BadRequestException;
import org.hubson404.weather.exceptions.DataProcessingErrorException;
import org.hubson404.weather.weather.model.ForecastModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastFetchService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OpenWeatherConfig config;
    private final ForecastMapper forecastMapper;
    private final ForecastPersistService forecastPersistService;

    public Forecast getForecast(String location, int period) {

        UriComponents build = UriComponentsBuilder.fromHttpUrl(config.getUri())
                .queryParam(config.getApiKeyParameterName(), config.getApiKey())
                .queryParam(config.getQueryParameterName(), location)
                .queryParam(config.getUnitsParameterName(), config.getUnits())
                .queryParam(config.getLanguageParameterName(), config.getLang())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(build.toUri(), String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from remote service.");
        }

        String responseBody = responseEntity.getBody();

        try {
            ForecastModel forecastModel = objectMapper.readValue(responseBody, ForecastModel.class);
            return forecastPersistService.saveForecastToDatabase(forecastMapper.mapToForecastDto(forecastModel, period));
        } catch (JsonProcessingException e) {
            throw new DataProcessingErrorException("Unable to process forecast data.");
        }
    }
}
