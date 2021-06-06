package com.wxzy.aws.dynamodb.controller;
import com.wxzy.aws.dynamodb.service.impl.RecordServiceImpl;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
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

}
