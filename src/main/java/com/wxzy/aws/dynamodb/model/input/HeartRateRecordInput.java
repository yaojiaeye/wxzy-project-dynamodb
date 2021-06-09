
package com.wxzy.aws.dynamodb.model.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class HeartRateRecordInput {

    @NotEmpty(message = "device_id should not be null or empty")
    @JSONField(name = "device_id")
    private String deviceId;

    @NotNull(message = "measure_ts should not be null")
    @JSONField(name = "measure_ts")
    private Long measureTs;

    @NotNull(message = "heart_rate should not be null")
    @JSONField(name = "heart_rate")
    private Integer heartRate;

    @JSONField(name = "family_member_id")
    private String familyMemberId;
}
