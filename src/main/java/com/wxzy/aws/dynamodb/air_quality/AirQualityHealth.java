package com.wxzy.aws.dynamodb.air_quality;

import com.alibaba.fastjson.annotation.JSONField;

public class AirQualityHealth {

    @JSONField(name = "general_population")
    private String generalPopulation;

    @JSONField(name = "elderly")
    private String elderly;

    @JSONField(name = "lung_diseases")
    private String lungDiseases;

    @JSONField(name = "heart_diseases")
    private String heartDiseases;

    @JSONField(name = "active")
    private String active;

    @JSONField(name = "pregnant_women")
    private String pregnantWomen;

    @JSONField(name = "children")
    private String children;

}
