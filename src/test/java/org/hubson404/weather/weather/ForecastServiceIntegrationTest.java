package org.hubson404.weather.weather;

import org.hubson404.weather.localization.Location;
import org.hubson404.weather.localization.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ForecastService forecastService;
    @Autowired
    ForecastRepository forecastRepository;
    @Autowired
    LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Seting up test...");
        forecastRepository.deleteAll();
        locationRepository.deleteAll();

        Location location1 = new Location();
        location1.setCityName("Warsaw");
        location1.setLongitude(55);
        location1.setLatitude(66);
        location1.setCountryName("Poland");

        locationRepository.save(location1);
        System.out.println("Setup finished");
    }

    @Test
    void fetchForecast_periodParameterIsZero_returnsResponseStatusCode200() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Warsaw")
                .param("period", "0")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void fetchForecast_periodParameterIs5_returnsResponseStatusCode200() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Warsaw")
                .param("period", "5")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void fetchForecast_periodParameterIsEmpty_returnsResponseStatusCode200() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Warsaw")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void fetchForecast_periodParameterIsMoreThan5_returnsResponseStatusCode400() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Warsaw")
                .param("period", "6")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void fetchForecast_periodParameterIsLessThanZero_returnsResponseStatusCode400() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Warsaw")
                .param("period", "-1")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void fetchForecast_whenLocationParameterIsEmpty_returnsResponseStatusCode400() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void fetchForecast_whenLocationIsNotInDatabase_returnsResponseStatusCode404() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get("/forecast")
                .param("location", "Krakow")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
