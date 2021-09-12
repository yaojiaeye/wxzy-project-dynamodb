package com.wxzy.aws.dynamodb.mapper.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.wxzy.aws.dynamodb.mapper.DeviceInfoMapper;
import com.wxzy.aws.dynamodb.model.input.DeviceInfoInput;
import com.wxzy.aws.dynamodb.model.pojo.DeviceInfo;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DeviceInfoMapperImpl implements DeviceInfoMapper {

    @Autowired
    @Qualifier("amazonDynamoDB")
    private AmazonDynamoDB amazonDynamoDB;

    private String tableName = "tHimaliaDevice";

    @Override
    public DeviceInfo getDeviceInfo(String deviceId, String... keys) {
        DynamoDB dynamo = new DynamoDB(this.amazonDynamoDB);
        Table table = dynamo.getTable(this.tableName);
        GetItemSpec getItemSpec = (new GetItemSpec()).withPrimaryKey("device_id", deviceId);
        if (keys != null && keys.length > 0) {
            Map<String, String> nameMap = new HashMap(16);
            StringBuilder projectionExpression = new StringBuilder("model");
            projectionExpression.append(",");
            String[] var8 = keys;
            int var9 = keys.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                String key = var8[var10];
                nameMap.put("#" + key, key);
                projectionExpression.append("#").append(key).append(",");
            }

            projectionExpression.deleteCharAt(projectionExpression.length() - 1);
            getItemSpec.withNameMap(nameMap).withProjectionExpression(projectionExpression.toString());
        }

        Item item = table.getItem(getItemSpec);
        if (item == null) {
            throw new GeneralException(ResultCode.NotExist);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(item.toJSONPretty());
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(deviceId);
            deviceInfo.setSettings(jsonObject);
            return deviceInfo;
        }
    }

    @Override
    public void setDeviceInfo(DeviceInfoInput deviceInfoInput) {
        DynamoDB dynamo = new DynamoDB(this.amazonDynamoDB);
        Table table = dynamo.getTable(this.tableName);
        Map<String, Object> map = deviceInfoInput.getSettings();
        UpdateItemSpec updateItemSpec =
            (new UpdateItemSpec()).withPrimaryKey("device_id", deviceInfoInput.getDeviceId());
        List<AttributeUpdate> list = new ArrayList();
        Iterator var7 = map.entrySet().iterator();

        while (var7.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var7.next();
            AttributeUpdate attributeUpdate = (new AttributeUpdate((String)entry.getKey())).put(entry.getValue());
            list.add(attributeUpdate);
        }

        updateItemSpec.withAttributeUpdate(list);
        table.updateItem(updateItemSpec);
    }
}
