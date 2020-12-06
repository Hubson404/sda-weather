package org.hubson404.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//@Validated
@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;

    @GetMapping("/forecast")
    public ForecastDTO getForecast(@RequestParam String location,
                                   @RequestParam(required = false, defaultValue = "1")
//                                   @Min(1)
//                                   @Max(5)
                                           int period) {
        Forecast forecast = forecastService.getForecast(location, period);
        ForecastDTO forecastDTO = forecastMapper.mapToForecastDto(forecast);
        return forecastDTO;
    }

    @GetMapping("/forecasts")
    public List<ForecastDTO> getAllForecastsForLocation(@RequestParam String location) {
        List<Forecast> forecasts = forecastService.getForecastByCityName(location);
        List<ForecastDTO> forecastDTOs = forecasts.stream()
                .map(forecastMapper::mapToForecastDto)
                .collect(Collectors.toList());
        return forecastDTOs;

    }
}
