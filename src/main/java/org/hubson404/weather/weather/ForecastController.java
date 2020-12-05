package org.hubson404.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastFetchService forecastFetchService;
    private final ForecastMapper forecastMapper;

    @GetMapping("/forecast")
    public ResponseEntity<ForecastDTO> getForecast(@RequestParam String location, @RequestParam(required = false, defaultValue = "1") int period) {
        Forecast forecast = forecastFetchService.getForecast(location, period);
        ForecastDTO forecastDTO = forecastMapper.mapToForecastDto(forecast);
        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastDTO);
    }

}
