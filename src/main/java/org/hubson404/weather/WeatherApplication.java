package org.hubson404.weather;

import org.hubson404.weather.weather.OpenWeatherConfig;
import org.hubson404.weather.weather.WeatherStackConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WeatherStackConfig.class, OpenWeatherConfig.class})
//@ConfigurationPropertiesScan
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
