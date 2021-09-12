package com.wxzy.aws.dynamodb.goal;

import com.alibaba.fastjson.annotation.JSONField;

public class Alarm {

    @JSONField(name = "device_id")
    private String deviceId;

    @JSONField(name = "device_model")
    private String deviceModel;

    @JSONField(name = "is_auto")
    private int isAuto;

    @JSONField(name = "city_id")
    private boolean cityId;

    @JSONField(name = "unit")
    private int unit;

}
