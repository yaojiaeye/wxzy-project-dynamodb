package com.wxzy.aws.dynamodb.model.output;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class WeatherIndexsList {

    private WeatherBaqi baqi;

    public void setBaqi(WeatherBaqi baqi) {
        this.baqi = baqi;
    }

    public WeatherBaqi getBaqi() {
        return baqi;
    }
}
