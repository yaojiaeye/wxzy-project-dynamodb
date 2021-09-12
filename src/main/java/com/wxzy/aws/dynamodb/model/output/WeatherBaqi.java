package com.wxzy.aws.dynamodb.model.output;

import lombok.Data;

@Data
public class WeatherBaqi {

    private String display_name;

    private int aqi;

    private String aqi_display;

    private String color;

    private String category;

    private String dominant_pollutant;

}
