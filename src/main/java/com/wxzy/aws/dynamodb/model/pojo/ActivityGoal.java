package com.wxzy.aws.dynamodb.model.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.wyze.common.util.DateUtil;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "tHimaliaGoal")
public class ActivityGoal implements Serializable {

    @DynamoDBHashKey(attributeName = "did_uid")
    @JSONField(name = "did_uid")
    private String didUid;

    @JSONField(name = "device_id")
    @DynamoDBAttribute(attributeName = "device_id")
    private String deviceId;

    @DynamoDBAttribute(attributeName = "date_time")
    @DynamoDBRangeKey(attributeName = "date_time")
    private String dateTime;

    @DynamoDBAttribute(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "step_goal")
    private int stepGoal;

    @DynamoDBAttribute(attributeName = "calorie_goal")
    private int calorieGoal;

    @DynamoDBAttribute(attributeName = "steps")
    private int steps;

    @DynamoDBAttribute(attributeName = "calories")
    private int calories;

    @JSONField(name = "create_time")
    @DynamoDBAttribute(attributeName = "create_time")
    private long createTime = DateUtil.getCurrentTime();

    @JSONField(name = "update_time")
    @DynamoDBAttribute(attributeName = "update_time")
    private long updateTime = DateUtil.getCurrentTime();

}
