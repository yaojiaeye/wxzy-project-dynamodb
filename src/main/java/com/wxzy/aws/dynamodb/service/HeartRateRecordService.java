package com.wxzy.aws.dynamodb.service;

import com.wxzy.aws.dynamodb.model.input.HeartRateRecordInput;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface HeartRateRecordService {
    // 添加心率测量结果
    void addHeartRateRecord(String userId, HeartRateRecordInput heartRateRecordInput);
}
