package com.wxzy.aws.dynamodb.mapper;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface RecordMapper {
    void save(ScaleRecord scaleRecord);

    List<DynamoDBMapper.FailedBatch> batchSave(List<ScaleRecord> scaleRecords);

    List<ScaleRecord> queryByUserIdAndFamilyMemberId(String userId, String familyMemberId);
}
