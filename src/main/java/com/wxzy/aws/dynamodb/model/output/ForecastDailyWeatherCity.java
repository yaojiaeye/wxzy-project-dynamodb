package com.wxzy.aws.dynamodb.model.output;

import java.util.List;

import lombok.Data;

@Data
public class ForecastDailyWeatherCity implements WeatherResponse {

    private City city;

    private int cod;

    private Float message;

    private int cnt;

    private List<WeatherDailyCityList> list;

    @Data
    public static class City {

        private int id;

        private String name;

        private Coord coord;

        private String country;

        private int population;

        private int timezone;
    }

    @Data
    public static class Coord {

        private Float lon;

        private Float lat;

    }

}
