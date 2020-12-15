package org.hubson404.weather.localization;

import org.hubson404.weather.weather.ForecastRepository;
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
class LocationFetchIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ForecastRepository forecastRepository;

    @Test
    void fetchEntryDetails_returnsDetailsOfEntry() throws Exception {
        // given
        forecastRepository.deleteAll();
        locationRepository.deleteAll();
        Location location = new Location();
        location.setCityName("new-city");
        location.setLongitude(0);
        location.setLatitude(0);
        location.setRegionName("new-region");
        location.setCountryName("new-country");
        Location savedLocation = locationRepository.save(location);
        Long id = savedLocation.getId();

        MockHttpServletRequestBuilder request = get("/locations/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void fetchLocationDetails_whenRepositoryIsEmpty_returnsDetailsOfLocation() throws Exception {
        // given
        forecastRepository.deleteAll();
        locationRepository.deleteAll();
        MockHttpServletRequestBuilder request = get("/locations/1")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(request).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
