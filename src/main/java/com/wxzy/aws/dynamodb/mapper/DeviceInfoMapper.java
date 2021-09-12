package com.wxzy.aws.dynamodb.mapper;

import com.wxzy.aws.dynamodb.model.input.DeviceInfoInput;
import com.wxzy.aws.dynamodb.model.pojo.DeviceInfo;

public interface DeviceInfoMapper {

    DeviceInfo getDeviceInfo(String deviceId, String... keys);

    void setDeviceInfo(DeviceInfoInput deviceInfoInput);

}
