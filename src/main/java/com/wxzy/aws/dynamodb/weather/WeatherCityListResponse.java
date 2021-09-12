package com.wxzy.aws.dynamodb.weather;

import java.util.List;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class WeatherCityListResponse {

    private City city;

    private String cod;

    private double message;

    private int cnt;

    private List<SunList> list;

    @Data
    public static class City {

        private int id;

        private String name;

        private List<Coord> coord;

        private String country;

        private int population;

        private int timezone;
    }

    @Data
    public static class SunList {
        private int dt;

        private int sunrise;

        private int sunset;

        private Temp temp;

        private FeelsLike feels_like;

        private int pressure;

        private int humidity;

        private List<Weather> weather;

        private double speed;

        private int deg;

        private double gust;

        private int clouds;

        private double pop;

        private double rain;
    }

    @Data
    public class Weather {
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
    public static class Coord {
        private float lon;

        private float lat;
    }

    @Data
    public static class Temp {

        private int day;

        private double min;

        private double max;

        private double night;

        private double eve;

        private double morn;

    }
}
