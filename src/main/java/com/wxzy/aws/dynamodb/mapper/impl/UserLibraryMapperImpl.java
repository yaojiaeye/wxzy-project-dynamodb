package com.wxzy.aws.dynamodb.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.wxzy.aws.dynamodb.mapper.UserLibraryMapper;
import com.wxzy.aws.dynamodb.model.pojo.UserLibrary;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserLibraryMapperImpl implements UserLibraryMapper {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void save(UserLibrary library) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        mapper.save(library);
    }

    @Override
    public UserLibrary getPictureLib(String devicePrimaryKey, String version) {
        final Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":pic_id", new AttributeValue().withS(devicePrimaryKey));

        final DynamoDBQueryExpression query = new DynamoDBQueryExpression();
        query.withKeyConditionExpression("pic_id = :pic_id ").withExpressionAttributeValues(eav)
            .withScanIndexForward(false);

        final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(this.amazonDynamoDB);
        final List<UserLibrary> list = dynamoDBMapper.query(UserLibrary.class, query);
        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public List<UserLibrary> getPictureLibByVersion(String version) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        final DynamoDBScanExpression scan = new DynamoDBScanExpression();
        final List<UserLibrary> scaleRecordList = mapper.scan(UserLibrary.class, scan);
        return scaleRecordList;
    }
}
