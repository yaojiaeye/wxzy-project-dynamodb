package com.wxzy.aws.dynamodb.model.output;

import lombok.Data;

@Data
public class ListWeatherResponse {

    private String metadata;

    private WeatherDataList data;

    private String error;
}
