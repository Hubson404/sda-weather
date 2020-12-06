package org.hubson404.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;

    @GetMapping("/forecast")
    public ForecastDTO getForecast(@RequestParam String location,
                                   @RequestParam(required = false, defaultValue = "1") int period) {
        Forecast forecast = forecastService.getForecast(location, period);
        ForecastDTO forecastDTO = forecastMapper.mapToForecastDto(forecast);
        return forecastDTO;
    }
}
