package com.wxzy.aws.dynamodb.air_quality;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Data
public class AirQualityPollutant {

    @JSONField(name = "display_name")
    private String displayName;

    @JSONField(name = "full_name")
    private String fullName;

    @JSONField(name = "concentration_value")
    private String concentrationValue;

    @JSONField(name = "concentration_units")
    private String concentrationUnits;

    @JSONField(name = "sources")
    private String sources;

    @JSONField(name = "effects")
    private String effects;

    @JSONField(name = "aqi_information")
    private AirQualityAqi aqiInformation;

}
