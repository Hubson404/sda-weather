package org.hubson404.weather.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastApiResponse {

//    @JsonProperty("cod")
//    private String cod;
//    @JsonProperty("message")
//    private Integer message;
//    @JsonProperty("cnt")
//    private Integer cnt;
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
//        @JsonProperty("weather")
//        private List<Weather> weather = null;
//        @JsonProperty("clouds")
//        private Clouds clouds;
        @JsonProperty("wind")
        private Wind wind;
//        @JsonProperty("visibility")
//        private Integer visibility;
//        @JsonProperty("pop")
//        private Integer pop;
//        @JsonProperty("sys")
//        private Sys sys;
        @JsonProperty("dt_txt")
        private String dtTxt;

        @Data
        public static class Main {
            @JsonProperty("temp")
            private Double temp;
//            @JsonProperty("feels_like")
//            private Double feelsLike;
//            @JsonProperty("temp_min")
//            private Double tempMin;
//            @JsonProperty("temp_max")
//            private Double tempMax;
            @JsonProperty("pressure")
            private Integer pressure;
//            @JsonProperty("sea_level")
//            private Integer seaLevel;
//            @JsonProperty("grnd_level")
//            private Integer grndLevel;
            @JsonProperty("humidity")
            private Integer humidity;
//            @JsonProperty("temp_kf")
//            private Double tempKf;

        }

//        @Data
//        static class Weather {
//            @JsonProperty("id")
//            private Integer id;
//            @JsonProperty("main")
//            private String main;
//            @JsonProperty("description")
//            private String description;
//            @JsonProperty("icon")
//            private String icon;
//
//        }

//        @Data
//        static class Clouds {
//            @JsonProperty("all")
//            private Integer all;
//
//        }

        @Data
        public static class Wind {
            @JsonProperty("speed")
            private Double speed;
            @JsonProperty("deg")
            private Integer deg;

        }

//        @Data
//        static class Sys {
//            @JsonProperty("pod")
//            private String pod;
//
//        }
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
//        @JsonProperty("population")
//        private Integer population;
//        @JsonProperty("timezone")
//        private Integer timezone;
//        @JsonProperty("sunrise")
//        private Integer sunrise;
//        @JsonProperty("sunset")
//        private Integer sunset;


        static class Coord {
            @JsonProperty("lat")
            private Double lat;
            @JsonProperty("lon")
            private Double lon;

        }

    }
}


