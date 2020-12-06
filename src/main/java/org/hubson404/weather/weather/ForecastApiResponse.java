package org.hubson404.weather.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastApiResponse {

    @JsonProperty("list")
    private List<WeatherListing> listing = null;
    @JsonProperty("city")
    private City city;

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

    @Data
    static class City {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("coord")
        private Coord coord;
        @JsonProperty("country")
        private String country;

        @Data
        static class Coord {
            @JsonProperty("lat")
            private Double lat;
            @JsonProperty("lon")
            private Double lon;
        }
    }
}


