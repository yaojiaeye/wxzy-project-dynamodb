package com.wxzy.aws.dynamodb.service;

import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface DeviceZoneService {
    // 修改zone
    void saveZone(DeviceZone deviceZone);

    // 修改zone without sqs
    void saveZoneWithoutSqs(DeviceZone deviceZone);
}
