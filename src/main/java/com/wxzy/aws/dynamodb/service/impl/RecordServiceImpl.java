package com.wxzy.aws.dynamodb.service.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.mapper.RecordMapper;
import com.wxzy.aws.dynamodb.model.enums.RecordDeleteEnum;
import com.wxzy.aws.dynamodb.model.helper.ScaleRecordHelper;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import com.wxzy.aws.dynamodb.service.RecordService;
import com.wyze.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void addScaleRecord(String userId, ScaleRecordInput scaleRecordInput) {
        final ScaleRecord scaleRecord;
        scaleRecord = ScaleRecordHelper.getScaleRecord(scaleRecordInput);
        scaleRecord.setIsDelete(RecordDeleteEnum.NOT_DELETED.getValue());
        scaleRecord.setUserId(userId);
        scaleRecord.setDataId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        scaleRecord.setFamilyMemberId(userId);
        this.recordMapper.save(scaleRecord);
    }

    @Override
    public void addScaleRecordList(String userId, List<ScaleRecordInput> scaleRecordInputList) {
        List<ScaleRecord> records = new ArrayList<ScaleRecord>();
        for (int i = 0; i < scaleRecordInputList.size(); i++) {
            ScaleRecordInput recordInput = scaleRecordInputList.get(i);
            final ScaleRecord scaleRecord;
            scaleRecord = ScaleRecordHelper.getScaleRecord(recordInput);
            scaleRecord.setUserId(userId);
            scaleRecord.setMeasureTs(scaleRecord.getMeasureTs() + i);
            scaleRecord.setIsDelete(RecordDeleteEnum.NOT_DELETED.getValue());
            scaleRecord.setDataId(UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE);
            if (StringUtil.isEmpty(scaleRecord.getFamilyMemberId())) {
                scaleRecord.setFamilyMemberId(userId);
            }
            records.add(scaleRecord);
        }
        final List<DynamoDBMapper.FailedBatch> fails = this.recordMapper.batchSave(records);
        if (!fails.isEmpty()) {
            log.error(fails.toString());
        }
    }
}
