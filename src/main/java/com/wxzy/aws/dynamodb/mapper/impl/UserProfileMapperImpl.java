package com.wxzy.aws.dynamodb.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.mapper.UserProfileMapper;
import com.wxzy.aws.dynamodb.model.pojo.UserProfile;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
@Repository
public class UserProfileMapperImpl implements UserProfileMapper {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public UserProfile loadUserInfo(String userId) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        return mapper.load(UserProfile.class, userId);
    }
}
