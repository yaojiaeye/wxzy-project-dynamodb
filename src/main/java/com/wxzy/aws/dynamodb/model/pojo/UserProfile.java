package com.wxzy.aws.dynamodb.model.pojo;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@DynamoDBTable(tableName = "t_user_profile")
@Data
public class UserProfile implements Serializable {

    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "birthdate")
    private String birthdate;

    @DynamoDBAttribute(attributeName = "weight")
    private Float weight;

    @DynamoDBAttribute(attributeName = "height")
    private Float height;

    @DynamoDBAttribute(attributeName = "gender")
    private Byte gender;

}
