package com.wxzy.aws.dynamodb.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.wxzy.aws.dynamodb.mapper.RecordMapper;
import com.wxzy.aws.dynamodb.model.enums.RecordDeleteEnum;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
@Repository
public class RecordMapperImpl implements RecordMapper {

    private static final String ATTR_NAME_USER_ID_VALUE = ":user_id";
    private static final String ATTR_NAME_FAMILY_MEMBER_ID_VALUE = ":family_member_id";
    private static final String ATTR_NAME_IS_DELETE_VALUE = ":is_delete";
    private static final String COMMON_FILTER = "is_delete = :is_delete and family_member_id = :family_member_id";

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void save(ScaleRecord scaleRecord) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        mapper.save(scaleRecord);
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchSave(List<ScaleRecord> scaleRecords) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        List<DynamoDBMapper.FailedBatch> failedBatches = mapper.batchSave(scaleRecords);
        return failedBatches;
    }

    @Override
    public List<ScaleRecord> queryByUserIdAndFamilyMemberId(String userId, String familyMemberId) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();

        eav.put(ATTR_NAME_USER_ID_VALUE, new AttributeValue().withS(userId));
        eav.put(ATTR_NAME_FAMILY_MEMBER_ID_VALUE, new AttributeValue().withS(familyMemberId));
        eav.put(ATTR_NAME_IS_DELETE_VALUE,
            new AttributeValue().withN(new Integer(RecordDeleteEnum.NOT_DELETED.getValue()).toString()));

        DynamoDBQueryExpression<ScaleRecord> queryExpression =
            new DynamoDBQueryExpression<ScaleRecord>().withKeyConditionExpression("user_id = :user_id")
                .withFilterExpression(COMMON_FILTER).withExpressionAttributeValues(eav).withScanIndexForward(false);
        PaginatedQueryList<ScaleRecord> records = mapper.query(ScaleRecord.class, queryExpression);
        return records;
    }
}
