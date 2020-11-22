package org.hubson404.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationRepository locationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createNewLocation_createsNewLocationAndReturn200StatusCode() throws Exception {
        // given
        locationRepository.deleteAll();
        LocationDTO locationDTO = new LocationDTO(
                null,
                "city",
                0,
                0,
                "region",
                "coutry"
        );
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
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
        locationRepository.deleteAll();
        LocationDTO locationDTO = new LocationDTO(
                null,
                "",
                0,
                0,
                "region",
                "coutry"
        );
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
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
        locationRepository.deleteAll();
        LocationDTO locationDTO = new LocationDTO(
                null,
                "city-name",
                0,
                100,
                "region",
                "coutry"
        );
        String requestBody = objectMapper.writeValueAsString(locationDTO);
        MockHttpServletRequestBuilder post = post("/locations")
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
