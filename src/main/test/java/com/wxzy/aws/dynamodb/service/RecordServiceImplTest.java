package com.wxzy.aws.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.mapper.RecordMapper;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.service.impl.RecordServiceImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class RecordServiceImplTest {

    @InjectMocks
    RecordServiceImpl recordService;

    @Mock
    RecordMapper recordMapper;

    private final String userId = "uid";
    private final String familyMemberId = "fid";

    private final Long ts = 1L;

    @BeforeClass
    public static void setup() {}

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterClass
    public static void clean() {}

    @Test
    public void addScaleRecord_paramIsNull() {
        final ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        scaleRecordInput.setFamilyMemberId(familyMemberId);
        this.recordService.addScaleRecord(this.userId, scaleRecordInput);
        verify(this.recordMapper, times(1)).save(any());
    }

    @Test
    public void addHeartRateRecordList_ok() {
        List<ScaleRecordInput> records = new ArrayList<>();
        ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        scaleRecordInput.setMeasureTs(this.ts);
        records.add(scaleRecordInput);
        List<DynamoDBMapper.FailedBatch> failedBatches = new ArrayList<>();
        when(this.recordMapper.batchSave(any())).thenReturn(failedBatches);
        this.recordService.addScaleRecordList(this.userId, records);
        verify(this.recordMapper, times(1)).batchSave(any());
    }
}
