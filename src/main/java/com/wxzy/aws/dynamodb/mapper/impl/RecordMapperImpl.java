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

    @Override
    public List<ScaleRecord> queryByUserIdAndDataId(String userId, Long dataId) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(ATTR_NAME_USER_ID_VALUE, new AttributeValue().withS(userId));
        eav.put(":data_id", new AttributeValue().withN(dataId.toString()));
        eav.put(ATTR_NAME_IS_DELETE_VALUE,
            new AttributeValue().withN(RecordDeleteEnum.NOT_DELETED.getValue().toString()));

        final DynamoDBQueryExpression query = new DynamoDBQueryExpression();
        query.withKeyConditionExpression("user_id = :user_id and data_id = :data_id")
            .withFilterExpression("is_delete = :is_delete").withIndexName("user_id-data_id-index")
            .withConsistentRead(false).withExpressionAttributeValues(eav);

        final List<ScaleRecord> scaleRecordList = mapper.query(ScaleRecord.class, query);
        return scaleRecordList;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchDelete(List<ScaleRecord> scaleRecords) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final List<DynamoDBMapper.FailedBatch> fails = mapper.batchDelete(scaleRecords);
        return fails;
    }

    @Override
    public List<ScaleRecord> getRecordList(String userId, Long startTime, Long endTime, Boolean forward,
        String familyMemberId) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final Map<String, AttributeValue> eav = new HashMap<>();

        eav.put(ATTR_NAME_USER_ID_VALUE, new AttributeValue().withS(userId));
        eav.put(":startTime", new AttributeValue().withN(startTime.toString()));
        eav.put(":endTime", new AttributeValue().withN(endTime.toString()));
        eav.put(ATTR_NAME_IS_DELETE_VALUE,
            new AttributeValue().withN(String.valueOf(RecordDeleteEnum.NOT_DELETED.getValue())));
        eav.put(ATTR_NAME_FAMILY_MEMBER_ID_VALUE, new AttributeValue().withS(familyMemberId));

        DynamoDBQueryExpression<ScaleRecord> query = new DynamoDBQueryExpression<ScaleRecord>();
        query.withKeyConditionExpression("user_id = :user_id and measure_ts between :startTime and :endTime")
            .withExpressionAttributeValues(eav).withFilterExpression(COMMON_FILTER).withScanIndexForward(forward);

        PaginatedQueryList<ScaleRecord> records = mapper.query(ScaleRecord.class, query);
        return records;
    }

    @Override
    public List<ScaleRecord> getRecordListByNum(String userId, String familyMemberId, Integer recordNum, Long endTime,
        Boolean forward) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final Map<String, AttributeValue> eav = new HashMap<>();

        eav.put(ATTR_NAME_USER_ID_VALUE, new AttributeValue().withS(userId));
        eav.put(":endTime", new AttributeValue().withN(endTime.toString()));
        eav.put(ATTR_NAME_IS_DELETE_VALUE,
            new AttributeValue().withN(String.valueOf(RecordDeleteEnum.NOT_DELETED.getValue())));
        eav.put(ATTR_NAME_FAMILY_MEMBER_ID_VALUE, new AttributeValue().withS(familyMemberId));

        DynamoDBQueryExpression query = new DynamoDBQueryExpression();
        query.withKeyConditionExpression("user_id = :user_id and measure_ts < :endTime")
            .withFilterExpression(COMMON_FILTER).withExpressionAttributeValues(eav).withScanIndexForward(forward);

        PaginatedQueryList list = mapper.query(ScaleRecord.class, query);
        return list;
    }
}
