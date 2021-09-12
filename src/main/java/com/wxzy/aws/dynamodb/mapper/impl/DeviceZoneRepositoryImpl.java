package com.wxzy.aws.dynamodb.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.wxzy.aws.dynamodb.mapper.DeviceZoneRepository;
import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
@Repository
public class DeviceZoneRepositoryImpl implements DeviceZoneRepository {

    @Autowired
    @Qualifier("amazonDynamoDB")
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void saveDeviceZone(DeviceZone deviceZone) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        mapper.save(deviceZone, DynamoDBMapperConfig.builder()
            .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());
    }

    @Override
    public void saveDeviceZoneWithoutSkippedNull(DeviceZone deviceZone) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        mapper.save(deviceZone);
    }
}
