package com.wxzy.aws.dynamodb.mapper;

import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface DeviceZoneRepository {
    /**
     * @param deviceZone
     */
    void saveDeviceZone(DeviceZone deviceZone);

    /**
     * @param deviceZone
     */
    void saveDeviceZoneWithoutSkippedNull(DeviceZone deviceZone);
}
