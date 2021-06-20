package com.wxzy.aws.dynamodb.air_quality;

import com.alibaba.fastjson.annotation.JSONField;

public class AirQualityAqi {

    @JSONField(name = "display_name")
    private String displayName;

    @JSONField(name = "aqi")
    private String aqi;

    @JSONField(name = "aqiDisplay")
    private String aqiDisplay;

    @JSONField(name = "color")
    private String color;

    @JSONField(name = "category")
    private String category;

    @JSONField(name = "dominant_pollutant")
    private String dominantPollutant;

}
