package com.wxzy.aws.dynamodb.service;

import com.wxzy.aws.dynamodb.model.input.DeviceInfoInput;
import com.wxzy.aws.dynamodb.model.pojo.DeviceInfo;

public interface DeviceInfoService {

    DeviceInfo getDeviceInfo(String deviceId, String... keys);

    void setDeviceInfo(DeviceInfoInput deviceInfoInput);

}
