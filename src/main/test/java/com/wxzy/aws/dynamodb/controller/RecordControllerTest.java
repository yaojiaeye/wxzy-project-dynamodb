package com.wxzy.aws.dynamodb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordListInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import com.wxzy.aws.dynamodb.service.impl.RecordServiceImpl;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.util.SessionUtil;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@RunWith(PowerMockRunner.class)
public class RecordControllerTest {

    @InjectMocks
    RecordController recordController;

    @Mock
    RecordServiceImpl recordService;

    @Mock
    BindingResult result;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addScaleRecord_Ok() {
        when(result.hasErrors()).thenReturn(true);
        FieldError fieldError =
            new FieldError("DeviceSettingInput", "deviceId", "device_id should not be null or empty");
        when(result.getFieldError()).thenReturn(fieldError);

        GeneralException exception = assertThrows(GeneralException.class, () -> {
            this.recordController.addScaleRecord(null, this.result);
        });
        assertEquals(ResultCode.ParameterError.getCode(), exception.getCode());
    }

    @Test
    public void addScaleRecordList_deviceIdIsNUll() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        final ScaleRecordListInput scaleRecordListInput = new ScaleRecordListInput();
        final ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        final List<ScaleRecordInput> list = new ArrayList<>();
        list.add(scaleRecordInput);
        scaleRecordListInput.setScaleRecordInputList(list);
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            this.recordController.addScaleRecordList(scaleRecordListInput, this.result);
        });
        assertTrue(exception.getCode().equals(ResultCode.ParameterError.getCode()));
    }

    @Test
    public void addHeartRateRecordList_measureTsIsNUll() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        final ScaleRecordListInput scaleRecordListInput = new ScaleRecordListInput();
        final ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        scaleRecordInput.setDeviceId("did");
        final List<ScaleRecordInput> list = new ArrayList<>();
        list.add(scaleRecordInput);
        scaleRecordListInput.setScaleRecordInputList(list);
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            this.recordController.addScaleRecordList(scaleRecordListInput, this.result);
        });
        assertTrue(exception.getCode().equals(ResultCode.ParameterError.getCode()));
    }

    @Test
    public void addHeartRateRecordList_ok() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        final ScaleRecordListInput scaleRecordListInput = new ScaleRecordListInput();
        final ScaleRecordInput scaleRecordInput = new ScaleRecordInput();
        scaleRecordInput.setDeviceId("did");
        scaleRecordInput.setMeasureTs(100L);
        final List<ScaleRecordInput> list = new ArrayList<>();
        list.add(scaleRecordInput);
        scaleRecordListInput.setScaleRecordInputList(list);
        final SessionUtil sessionUtil = mock(SessionUtil.class);
        PowerMockito.mockStatic(SessionUtil.class);
        Mockito.when(SessionUtil.getInstance()).thenReturn(sessionUtil);
        PowerMockito.when(sessionUtil.get("user_id")).thenReturn("uid");
        ResultModel<String> resultModel = this.recordController.addScaleRecordList(scaleRecordListInput, this.result);
        assertEquals(resultModel.getCode(), ResultCode.SUCCESS.getCode());
    }

    @Test
    public void listRangeParamIsNull_ok() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        List<ScaleRecord> records = new ArrayList<>(10);

        Mockito.when(this.recordService.getRecordList("20133439", "20133439", 0l, 3l, true)).thenReturn(records);

    }
}
