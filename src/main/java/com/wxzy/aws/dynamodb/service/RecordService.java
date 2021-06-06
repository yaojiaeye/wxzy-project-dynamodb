package com.wxzy.aws.dynamodb.service;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
public interface RecordService {

    // 添加人体测量结果
    void addScaleRecord(String userId, ScaleRecordInput scaleRecordInput);
}
