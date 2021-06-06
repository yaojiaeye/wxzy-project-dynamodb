package com.wxzy.aws.dynamodb.service.impl;

import com.wxzy.aws.dynamodb.mapper.RecordMapper;
import com.wxzy.aws.dynamodb.model.enums.RecordDeleteEnum;
import com.wxzy.aws.dynamodb.model.helper.ScaleRecordHelper;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import com.wxzy.aws.dynamodb.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
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
}
