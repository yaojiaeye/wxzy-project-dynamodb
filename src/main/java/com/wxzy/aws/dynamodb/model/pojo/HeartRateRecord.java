package com.wxzy.aws.dynamodb.model.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import lombok.Data;

@DynamoDBTable(tableName = "t_scale_heart_rate_record")
@Data
public class HeartRateRecord implements Serializable {

    // wyze user id
    @JSONField(name = "user_id")
    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @JSONField(name = "measure_ts")
    @DynamoDBRangeKey(attributeName = "measure_ts")
    private Long measureTs;

    @JSONField(name = "device_id")
    @DynamoDBIndexRangeKey(localSecondaryIndexName = "user_id-device_id-index", attributeName = "device_id")
    private String deviceId;

    @JSONField(name = "data_id")
    @DynamoDBAttribute(attributeName = "data_id")
    private Long dataId;

    // 心率 次/分钟
    @JSONField(name = "heart_rate")
    @DynamoDBAttribute(attributeName = "heart_rate")
    private Integer heartRate;

    @JSONField(name = "is_delete")
    @DynamoDBAttribute(attributeName = "is_delete")
    private Integer isDelete;

    @JSONField(name = "family_member_id")
    @DynamoDBAttribute(attributeName = "family_member_id")
    private String familyMemberId;
}
