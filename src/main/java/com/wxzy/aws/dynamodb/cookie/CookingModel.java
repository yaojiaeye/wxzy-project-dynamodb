package com.wxzy.aws.dynamodb.cookie;

import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;

@Data
public class CookingModel {

    @JSONField(name = "functionId")
    private int functionId;

    @JSONField(name = "functionName")
    private String functionName;

    @JSONField(name = "cookScript")
    private String cookScript;

    @JSONField(name = "cookScriptDetail")
    private CookingDetail cookScriptDetail;

    @Data
    public static class CookingDetail {

        @JSONField(name = "temp")
        private Integer targetTemperature;

        @DynamoDBAttribute(attributeName = "duration")
        private Integer duration;
    }
}
