package org.hubson404.weather.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastModel {

    @JsonProperty("list")
    private List<WeatherListing> listing = null;

    @Data
    public static class WeatherListing {
        @JsonProperty("dt")
        private Integer dt;
        @JsonProperty("main")
        private Main main;
        @JsonProperty("wind")
        private Wind wind;
        @JsonProperty("dt_txt")
        private String dtTxt;

        @Data
        public static class Main {
            @JsonProperty("temp")
            private Double temp;
            @JsonProperty("pressure")
            private Integer pressure;
            @JsonProperty("humidity")
            private Integer humidity;
        }

        @Data
        public static class Wind {
            @JsonProperty("speed")
            private Double speed;
            @JsonProperty("deg")
            private Integer deg;
        }
    }
}


