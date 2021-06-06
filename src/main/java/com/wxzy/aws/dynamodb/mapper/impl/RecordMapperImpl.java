package com.wxzy.aws.dynamodb.mapper.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.mapper.RecordMapper;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
@Slf4j
@Repository
public class RecordMapperImpl  implements RecordMapper {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void save(ScaleRecord scaleRecord) {
        final DynamoDBMapper mapper = new DynamoDBMapper(this.amazonDynamoDB);
        mapper.save(scaleRecord);
    }
}
