package com.wxzy.aws.dynamodb.model.output;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class WeatherDailyCityList {

    private int dt;

    private int sunrise;

    private int sunset;

    private Temp temp;

    @JSONField(name = "feels_like")
    private FeelsLike feelsLike;

    private int pressure;

    private int humidity;

    private List<Weather> weather;

    private double speed;

    private int deg;

    private double gust;

    private int clouds;

    private double pop;

    private double rain;

    @Data
    public static class Weather {
        private int id;

        private String main;

        private String description;

        private String icon;
    }

    @Data
    public static class FeelsLike {

        private double day;

        private double night;

        private double eve;

        private double morn;
    }

    @Data
    public static class Temp {

        private int day;

        private Float min;

        private Float max;

        private Float night;

        private Float eve;

        private Float morn;

    }
}
