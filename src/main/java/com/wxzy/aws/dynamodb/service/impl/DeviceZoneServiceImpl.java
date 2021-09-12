package com.wxzy.aws.dynamodb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wxzy.aws.dynamodb.mapper.DeviceZoneRepository;
import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;
import com.wxzy.aws.dynamodb.service.DeviceZoneService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Service
@Slf4j
public class DeviceZoneServiceImpl implements DeviceZoneService {

    @Autowired
    @Lazy
    private DeviceZoneRepository deviceZoneRepository;

    @Override
    public void saveZone(DeviceZone deviceZone) {
        this.deviceZoneRepository.saveDeviceZoneWithoutSkippedNull(deviceZone);
    }

    @Override
    public void saveZoneWithoutSqs(DeviceZone deviceZone) {
        this.deviceZoneRepository.saveDeviceZone(deviceZone);
    }
}
