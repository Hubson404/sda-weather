package org.hubson404.weather.localization;

import org.assertj.core.api.Assertions;
import org.hubson404.weather.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationFetchServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    LocationFetchService locationFetchService;

    @Test
    void getAllLocations() {
        //given
        when(locationRepository.findAll()).thenReturn(Arrays.asList(new Location(), new Location(), new Location()));
        //when
        List<Location> allLocations = locationFetchService.getAllLocations();
        //then
        assertThat(allLocations.size()).isEqualTo(3);

        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void fetchLocation_whenHappyPath_returnsLocation() {
        //given
        when(locationRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Location()));
        //when
        Location location = locationFetchService.fetchLocationById("1");
        //then
        assertThat(location).isNotNull();
        verify(locationRepository, times(1)).findById(anyLong());
    }

    @Test
    void fetchLocation_whenLocationDoesntExists_ThrowsNotFoundException() {
        //given
        when(locationRepository.findById(anyLong())).thenThrow(NotFoundException.class);
        //when
        Throwable result = catchThrowable(() -> locationFetchService.fetchLocationById("1"));
        // then
        Assertions.assertThat(result).isExactlyInstanceOf(NotFoundException.class);
        verify(locationRepository, times(1)).findById(anyLong());

    }
}