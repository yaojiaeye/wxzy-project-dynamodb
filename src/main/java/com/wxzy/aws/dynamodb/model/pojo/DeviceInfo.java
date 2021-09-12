package com.wxzy.aws.dynamodb.model.pojo;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@DynamoDBTable(tableName = "tHimaliaDevice")
public class DeviceInfo {

    private String deviceId;

    private JSONObject settings;

}
