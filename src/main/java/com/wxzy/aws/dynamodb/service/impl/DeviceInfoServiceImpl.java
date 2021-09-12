package com.wxzy.aws.dynamodb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxzy.aws.dynamodb.mapper.DeviceInfoMapper;
import com.wxzy.aws.dynamodb.model.input.DeviceInfoInput;
import com.wxzy.aws.dynamodb.model.pojo.DeviceInfo;
import com.wxzy.aws.dynamodb.service.DeviceInfoService;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public DeviceInfo getDeviceInfo(String deviceId, String... keys) {
        return this.deviceInfoMapper.getDeviceInfo(deviceId, keys);
    }

    @Override
    public void setDeviceInfo(DeviceInfoInput deviceInfoInput) {
        this.deviceInfoMapper.setDeviceInfo(deviceInfoInput);
    }
}
