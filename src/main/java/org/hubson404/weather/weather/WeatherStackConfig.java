package org.hubson404.weather.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("org.hubson404.weather.weatherstack-api")
public class WeatherStackConfig {

    private String apiKey;
    private String uri;
    private String units;
    private String lang;

}
