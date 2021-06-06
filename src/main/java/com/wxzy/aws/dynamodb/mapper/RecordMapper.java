package com.wxzy.aws.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;

import java.util.List;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
public interface RecordMapper {
    void save(ScaleRecord scaleRecord);

    List<DynamoDBMapper.FailedBatch> batchSave(List<ScaleRecord> scaleRecords);
}
