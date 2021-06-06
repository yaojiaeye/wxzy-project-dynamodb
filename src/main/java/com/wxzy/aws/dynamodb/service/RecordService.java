package com.wxzy.aws.dynamodb.service;

import java.util.List;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface RecordService {

    // 添加人体测量结果
    void addScaleRecord(String userId, ScaleRecordInput scaleRecordInput);

    // 批量上传人体测量结果
    void addScaleRecordList(String userId, List<ScaleRecordInput> scaleRecordInputList);

    List<ScaleRecord> getLatestRecord(String userId, String familyId);
}
