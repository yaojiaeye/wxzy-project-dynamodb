package com.wxzy.aws.dynamodb.air_quality;

import com.alibaba.fastjson.annotation.JSONField;

public class FiresDailyApi {

    @JSONField(name = "display_name")
    private String displayName;

    // 花粉是否在季节
    @JSONField(name = "in_season")
    private boolean inSeason;

    @JSONField(name = "value")
    private Integer value;

    @JSONField(name = "category")
    private String category;

    @JSONField(name = "color")
    private String color;

    @JSONField(name = "datetime")
    private String datetime;
}
