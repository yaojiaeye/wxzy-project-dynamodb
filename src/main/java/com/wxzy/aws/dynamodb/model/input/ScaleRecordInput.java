
package com.wxzy.aws.dynamodb.model.input;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class ScaleRecordInput {

    // 二级索引 设备id
    @NotEmpty(message = "device_id should not be null or empty")
    @JSONField(name = "device_id")
    private String deviceId;

    @NotNull(message = "measure_ts should not be null")
    @JSONField(name = "measure_ts")
    private Long measureTs;

    // 1在线测量/0离线测量
    @JSONField(name = "measure_type")
    private Byte measureType = 0;

    private String mac;

    // 岁
    private Integer age;

    // 0男，1女
    private Byte gender;

    // cm
    private Float height;

    // 体型1:Ectomorph 2:Mesomorph 3:Endomorph
    @JSONField(name = "body_type")
    private Byte bodyType;

    // 职业 //0 :unknown 1: 运动员
    private Integer occupation;

    // kg
    private Float weight;

    // 阻抗1，190-1010，欧姆
    private Integer impedance1;

    // 阻抗2，190-1010，欧姆
    private Integer impedance2;

    // 阻抗3，190-1010，欧姆
    private Integer impedance3;

    // 阻抗4，190-1010，欧姆
    private Integer impedance4;

    // 体脂率，5.0-0.75.0 %
    @JSONField(name = "body_fat")
    private Float bodyFat;

    // 肌肉重量，10.0-120.0，kg
    private Float muscle;

    // 骨盐质量，0.5-8.0 kg
    @JSONField(name = "bone_mineral")
    private Float boneMineral;

    // 体水分率，35.0-75.0 %
    @JSONField(name = "body_water")
    private Float bodyWater;

    // 蛋白质率，2.0-30.0 %
    private Float protein;

    // 内脏脂肪等级，1-60
    @JSONField(name = "body_vfr")
    private Integer bodyVfr;

    //身体年龄，6-99， 岁
    @JSONField(name = "metabolic_age")
    private Integer metabolicAge;

    // 身体质量指数，体重/（身高*身高）kg/m^2
    private Float bmi;

    // 去脂重量，体重*（1-体脂率），kg
    private Float bmr;

    @JSONField(name = "family_member_id")
    private String familyMemberId;

    private String timezone;

}
