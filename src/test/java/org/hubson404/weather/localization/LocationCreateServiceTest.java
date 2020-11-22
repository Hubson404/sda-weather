package org.hubson404.weather.localization;

import org.hubson404.weather.exceptions.InsufficientDataException;
import org.hubson404.weather.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationCreateServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    LocationCreateService locationCreateService;

    @Test
    void createLocation_callsLocationRepository() {
        // given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());
        // when
        Location result = locationCreateService.createLocation(new LocationDefinition(
                "city",
                0,
                0,
                "region",
                "country"
        ));
        // then
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    void createLocation_whenCityNameIsEmpty_throwsInsufficientDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "",
                0,
                0,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InsufficientDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenCityNameIsWhitespaceOnly_throwsInsufficientDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "   ",
                0,
                0,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InsufficientDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenCountryNameIsEmpty_throwsInsufficientDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "city-name",
                0,
                0,
                "",
                ""
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InsufficientDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenCountryNameIsWhitespaceOnly_throwsInsufficientDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "country-name",
                0,
                0,
                "",
                "   "
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InsufficientDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeValueIsInvalid_throwsInvalidDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "city",
                91,
                0,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InvalidDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeValueIsInvalidNegativeCase_throwsInvalidDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "city",
                -91,
                0,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InvalidDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitudeValueIsInvalid_throwsInvalidDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "city",
                0,
                181,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InvalidDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }
    @Test
    void createLocation_whenLatitudeValueIsInvalidNegativeCase_throwsInvalidDataException() {
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(new LocationDefinition(
                "city",
                0,
                -181,
                "",
                "country"
        )));
        // then
        assertThat(result).isExactlyInstanceOf(InvalidDataException.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }
    @Test
    void createLocation_whenLongitudePositiveEdgeCase_callsLocationRepository() {
        // given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());
        // when
        Location result = locationCreateService.createLocation(new LocationDefinition(
                "city",
                90,
                0,
                "region",
                "country"
        ));
        // then
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeNegativeEdgeCase_callsLocationRepository() {
        // given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());
        // when
        Location result = locationCreateService.createLocation(new LocationDefinition(
                "city",
                -90,
                0,
                "region",
                "country"
        ));
        // then
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitudeNegativeEdgeCase_callsLocationRepository() {
        // given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());
        // when
        Location result = locationCreateService.createLocation(new LocationDefinition(
                "city",
                0,
                -180,
                "region",
                "country"
        ));
        // then
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitudePositiveEdgeCase_callsLocationRepository() {
        // given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());
        // when
        Location result = locationCreateService.createLocation(new LocationDefinition(
                "city",
                0,
                180,
                "region",
                "country"
        ));
        // then
        verify(locationRepository, times(1)).save(any(Location.class));
    }
}
