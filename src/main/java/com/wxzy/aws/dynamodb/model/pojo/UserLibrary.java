package com.wxzy.aws.dynamodb.model.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.wyze.common.util.DateUtil;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "t_polaris_picture_lib")
public class UserLibrary implements Serializable {

    @JSONField(name = "pic_id")
    @DynamoDBHashKey(attributeName = "pic_id")
    @DynamoDBAttribute(attributeName = "pic_id")
    private String picId;

    @JSONField(name = "version")
    @DynamoDBAttribute(attributeName = "version")
    private String version;

    @DynamoDBAttribute
    private String name;

    @JSONField(name = "category_id")
    @DynamoDBAttribute(attributeName = "category_id")
    private String categoryId;

    @JSONField(name = "category_name")
    @DynamoDBAttribute(attributeName = "category_name")
    private String categoryName;

    @DynamoDBAttribute
    private Long size;

    @JSONField(name = "is_recommend")
    @DynamoDBAttribute(attributeName = "is_recommend")
    private boolean isRecommend;

    @JSONField(name = "icon_url")
    @DynamoDBAttribute(attributeName = "icon_url")
    private String iconUrl;

    @JSONField(name = "file_url")
    @DynamoDBAttribute(attributeName = "file_url")
    private String fileUrl;

    @JSONField(name = "signed_url")
    @DynamoDBAttribute(attributeName = "signed_url")
    private String signedUrl;

    @JSONField(name = "create_time")
    @DynamoDBAttribute(attributeName = "create_time")
    private long createTime = DateUtil.getCurrentTime();

    @JSONField(name = "update_time")
    @DynamoDBAttribute(attributeName = "update_time")
    private long updateTime = DateUtil.getCurrentTime();

}
