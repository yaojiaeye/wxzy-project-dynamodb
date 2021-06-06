package com.wxzy.aws.dynamodb.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
public class RecordServiceImplTest {

    @InjectMocks
    RecordServiceImpl recordService;

    @Mock
    RecordMapper recordMapper;


    private final String userId = "uid";
    private final String familyMemberId = "fid";


    @BeforeClass
    public static void setup() {
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterClass
    public static void clean() {
    }


    @Test
    public void addScaleRecord_paramIsNull() {
        final ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        scaleRecordInput.setFamilyMemberId(familyMemberId);
        this.recordService.addScaleRecord(this.userId, scaleRecordInput);
        verify(this.recordMapper, times(1)).save(any());
    }
}
