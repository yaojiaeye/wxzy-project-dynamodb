package com.wxzy.aws.dynamodb.model.input;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;
import com.wyze.common.util.DateUtil;

import lombok.Data;

@Data
public class DeviceInfoInput {

    @NotEmpty(message = "device_id is empty")
    @JSONField(name = "device_id")
    private String deviceId;
    @NotEmpty(message = "settings is empty")
    private Map<String, Object> settings;
    private Long updateTime = DateUtil.getCurrentTime();
}
