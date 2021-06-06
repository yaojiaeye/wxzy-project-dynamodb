package com.wxzy.aws.dynamodb.controller;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.service.RecordService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
@RestController
@RequestMapping("/plugin/scale")
public class RecordController {

    @Autowired
    private RecordService recordService;


    @PostMapping("/add_record")
    public ResultModel addScaleRecord(@RequestBody @Valid final ScaleRecordInput scaleRecordInput,
                                      final BindingResult result) {
        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        final String userId = "20133439";
        this.recordService.addScaleRecord(userId, scaleRecordInput);
        return ResultUtil.success();
    }
}
