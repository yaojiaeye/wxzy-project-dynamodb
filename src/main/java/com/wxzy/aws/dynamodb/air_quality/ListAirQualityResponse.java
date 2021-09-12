package com.wxzy.aws.dynamodb.air_quality;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class ListAirQualityResponse {

    // @JSONField(name = "datetime")
    // private String datetime;
    //
    // @JSONField(name = "airQualityAqi")
    // private AirQualityAqi airQualityAqi;
    //
    // @JSONField(name = "pollutants")
    // private List<AirQualityPollutant> airPollutants;
    //
    // @JSONField(name = "health_recommendations")
    // private AirQualityHealth healthRecommendations;

    @JSONField(name = "data")
    private List<FiresDailyApiData> data;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
