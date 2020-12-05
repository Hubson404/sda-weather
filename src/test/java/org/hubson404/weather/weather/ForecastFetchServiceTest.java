package org.hubson404.weather.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hubson404.weather.localization.Location;
import org.hubson404.weather.localization.LocationFetchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastFetchServiceTest {

    @Mock
    LocationFetchService locationFetchService;
    @Spy //pobiera configuracje zadane w aplikacji
    RestTemplate restTemplate;
    @Spy
    ObjectMapper objectMapper;
    @Mock
    ForecastPersistService forecastPersistService;
    @InjectMocks
    ForecastFetchService forecastFetchService;

    @Test
    void getForecast_ReturnCorrectForecast() {
        //given
        String searchedLocationName = "Warsaw";

        Location location = new Location();
        location.setCityName(searchedLocationName);

        Forecast forecast = new Forecast();
        forecast.setId(1L);
        forecast.setTemperature("0");
        forecast.setAirPressure("0");
        forecast.setHumidity("0");
        forecast.setWindDegree("0");
        forecast.setWindSpeed("0");
        forecast.setDate("0");

        when(locationFetchService.getLocationByCityName(anyString())).thenReturn(location);
        when(forecastPersistService.saveForecastToDatabase(anyObject())).thenReturn(forecast);

        //when
        forecastFetchService.getForecast(searchedLocationName, 1);

        //then

    }
}