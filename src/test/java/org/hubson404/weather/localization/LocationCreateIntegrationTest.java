package org.hubson404.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hubson404.weather.weather.ForecastRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ForecastRepository forecastRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        forecastRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    void createNewLocation_createsNewLocationAndReturn200StatusCode() throws Exception {
        // given
        LocationDTO locationDTO = LocationDTO.builder()
                .id(null)
                .cityName("cityName")
                .longitude(0)
                .latitude(0)
                .regionName("region")
                .countryName("country")
                .build();
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // when
        MvcResult result = mockMvc.perform(post).andReturn();
        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations.size()).isEqualTo(1);
    }

    @Test
    void createNewLocation_whenCityIsEmpty_returns400StatusCode() throws Exception {
        // given
        LocationDTO locationDTO = LocationDTO.builder()
                .id(null)
                .cityName("")
                .longitude(0)
                .latitude(0)
                .regionName("region")
                .countryName("country")
                .build();

        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // when
        MvcResult result = mockMvc.perform(post).andReturn();
        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }

    @Test
    void createNewLocation_whenCountryIsEmpty_returns400StatusCode() throws Exception {
        // given
        LocationDTO locationDTO = LocationDTO.builder()
                .id(null)
                .cityName("cityName")
                .longitude(0)
                .latitude(0)
                .regionName("region")
                .countryName("")
                .build();
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // when
        MvcResult result = mockMvc.perform(post).andReturn();
        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }

    @Test
    void createNewLocation_whenLongitudeIsInvalid_returns400StatusCode() throws Exception {
        // given
        LocationDTO locationDTO = LocationDTO.builder()
                .id(null)
                .cityName("cityName")
                .longitude(100)
                .latitude(0)
                .regionName("region")
                .countryName("country")
                .build();
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // when
        MvcResult result = mockMvc.perform(post).andReturn();
        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }

    @Test
    void createNewLocation_whenLatitudeIsInvalid_returns400StatusCode() throws Exception {
        // given
        LocationDTO locationDTO = LocationDTO.builder()
                .id(null)
                .cityName("cityName")
                .longitude(0)
                .latitude(200)
                .regionName("region")
                .countryName("country")
                .build();
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // when
        MvcResult result = mockMvc.perform(post).andReturn();
        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }
}
