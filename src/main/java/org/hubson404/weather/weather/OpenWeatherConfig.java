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
    private String uri;
    private String units;
    private String lang;

    private String apiKeyParameterName;
    private String queryParameterName;
    private String unitsParameterName;
    private String languageParameterName;

}
