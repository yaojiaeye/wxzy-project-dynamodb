package com.wxzy.aws.dynamodb.model.output;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class WeatherCurrentCityResponse {

    private Coord coord;

    private List<Weather> weather;

    private String base;

    private Main main;

    private int visibility;

    private Wind wind;

    private Clouds clouds;

    private int dt;

    private Sys sys;

    private int timezone;

    private int id;

    private String name;

    private int cod;

    @Data
    public static class Coord {

        private double lon;

        private double lat;
    }

    @Data
    public static class Weather {
        private int id;

        private String main;

        private String description;

        private String icon;
    }

    @Data
    public static class Main {
        private double temp;

        @JSONField(name = "feels_like")
        private double feelsLike;

        @JSONField(name = "temp_min")
        private double tempMin;

        @JSONField(name = "temp_max")
        private double tempMax;

        private int pressure;

        private int humidity;
    }

    @Data
    public static class Wind {
        private double speed;

        private int deg;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Sys {
        private int type;

        private int id;

        private String country;

        private int sunrise;

        private int sunset;
    }
}
