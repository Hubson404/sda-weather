package org.hubson404.weather.weather;

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
class ForecastFetchServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ForecastFetchService forecastFetchService;

    @Test
    void fetchForecast_returnsDetailsOfForecast() throws Exception {
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
    void fetchForecast_whenLocationParameterIsEmpty_throwsException() throws Exception {
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
    void fetchForecast_whenLocationIsNotInDatabase_returnsDetailsOfForecast() throws Exception {
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
