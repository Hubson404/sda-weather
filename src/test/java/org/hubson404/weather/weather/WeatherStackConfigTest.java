package org.hubson404.weather.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherStackConfigTest {

    @Mock
    WeatherStackConfig weatherStackConfig;

    @Test
    void getUri_returnsUri() {
        //given
        when(weatherStackConfig.getUri()).thenReturn("http://api.weatherstack.com/current");
        //when
        String uri = weatherStackConfig.getUri();
        //then
        assertThat(uri).isEqualTo("http://api.weatherstack.com/current");
    }

}