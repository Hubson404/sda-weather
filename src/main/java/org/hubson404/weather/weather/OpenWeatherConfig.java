package org.hubson404.weather.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "org.hubson404.weather.openweathermap-api")
public class OpenWeatherConfig {

    private String apiKey;
}
