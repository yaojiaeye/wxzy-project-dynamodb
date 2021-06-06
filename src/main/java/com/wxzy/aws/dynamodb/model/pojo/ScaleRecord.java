package com.wxzy.aws.dynamodb.model.pojo;
import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import java.io.Serializable;

@DynamoDBTable(tableName = "t_scale_record")
@Data
public class ScaleRecord implements Serializable {

    @JSONField(name = "user_id")
    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @JSONField(name = "measure_ts")
    @DynamoDBRangeKey(attributeName = "measure_ts")
    private Long measureTs;

    @JSONField(name = "device_id")
//    @DynamoDBIndexRangeKey(localSecondaryIndexName = "user_id-device_id-index", attributeName = "device_id")
    private String deviceId;

    @JSONField(name = "data_id")
//    @DynamoDBIndexRangeKey(localSecondaryIndexName = "user_id-data_id-index", attributeName = "data_id")
    private Long dataId;

    @DynamoDBAttribute
    private String mac;

    // 1在线测量/0离线测量
    @JSONField(name = "measure_type")
    @DynamoDBAttribute(attributeName = "measure_type")
    private Byte measureType;

    @DynamoDBAttribute
    private Integer age;

    // 0男/1女
    @DynamoDBAttribute
    private Byte gender;

    // cm
    @DynamoDBAttribute
    private Float height;

    // 体型1:Ectomorph 2:Mesomorph 3:Endomorph
    @JSONField(name = "body_type")
    @DynamoDBAttribute(attributeName = "body_type")
    private Byte bodyType;

    @DynamoDBAttribute
    private Integer occupation;

    // kg
    @DynamoDBAttribute
    private Float weight;

    // 阻抗1，190-1010，欧姆
    @DynamoDBAttribute
    private Integer impedance1;

    // 阻抗2，190-1010，欧姆
    @DynamoDBAttribute
    private Integer impedance2;

    // 阻抗3，190-1010，欧姆
    @DynamoDBAttribute
    private Integer impedance3;

    // 阻抗4，190-1010，欧姆
    @DynamoDBAttribute
    private Integer impedance4;

    // 体脂率，5.0-0.75.0 %
    @JSONField(name = "body_fat")
    @DynamoDBAttribute(attributeName = "body_fat")
    private Float bodyFat;

    // 肌肉重量，10.0-120.0，kg
    @DynamoDBAttribute
    private Float muscle;

    // 骨盐质量，0.5-8.0 kg
    @JSONField(name = "bone_mineral")
    @DynamoDBAttribute(attributeName = "bone_mineral")
    private Float boneMineral;

    // 体水分率，35.0-75.0 %
    @JSONField(name = "body_water")
    @DynamoDBAttribute(attributeName = "body_water")
    private Float bodyWater;

    // 蛋白质率，2.0-30.0 %
    @DynamoDBAttribute
    private Float protein;

    // 内脏脂肪等级，1-60
    @JSONField(name = "body_vfr")
    @DynamoDBAttribute(attributeName = "body_vfr")
    private Integer bodyVfr;

    //身体年龄，6-99， 岁
    @JSONField(name = "metabolic_age")
    @DynamoDBAttribute(attributeName = "metabolic_age")
    private Integer metabolicAge;

    // 身体质量指数，，体重/（身高*身高）kg/m^2
    @DynamoDBAttribute
    private Float bmi;

    // 去脂重量，体重*（1-体脂率），kg
    @DynamoDBAttribute
    private Float bmr;

    @JSONField(name = "is_delete")
    @DynamoDBAttribute(attributeName = "is_delete")
    private Integer isDelete;

    @JSONField(name = "family_member_id")
    @DynamoDBAttribute(attributeName = "family_member_id")
    private String familyMemberId;

    @DynamoDBAttribute
    private String timezone;
    @DynamoDBAttribute(attributeName = "fitbit_weight")
    private Boolean fitbitWeight;
    @DynamoDBAttribute(attributeName = "fitbit_fat")
    private Boolean fitbitFat;

}
