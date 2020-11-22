package org.hubson404.weather.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    WeatherStackConfig weatherStackConfig;

    @InjectMocks
    ForecastService forecastService;

    @Test
    void getForecast_returnForecastDto(){
        //given
        // when
        ForecastDTO forecast = forecastService.getForecast("Warsaw");
        // then
        assertThat(forecast).isNotNull();
    }
}