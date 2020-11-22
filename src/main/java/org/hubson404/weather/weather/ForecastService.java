package org.hubson404.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    private final WeatherStackConfig config;

    public ForecastDTO getForecast(String location) {

        UriComponents build = UriComponentsBuilder.fromHttpUrl(config.getUri())
                .queryParam("access_key", config.getApiKey())
                .queryParam("query", location)
                .queryParam("units", config.getUnits())
                .queryParam("lang", config.getLang())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(build.toUri(), String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Nie można pobrać danych z serwisu zewnętrznego");
        }

        String responseBody = responseEntity.getBody();

        try {
            ForecastDTO forecastDTO = objectMapper.readValue(responseBody, ForecastDTO.class);
            return forecastDTO;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Błąd podczas przetwarzania informacji o pogodzie");
        }
    }
}
