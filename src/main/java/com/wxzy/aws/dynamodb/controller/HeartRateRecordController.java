package com.wxzy.aws.dynamodb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxzy.aws.dynamodb.model.input.HeartRateRecordInput;
import com.wxzy.aws.dynamodb.service.HeartRateRecordService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:心率测量
 **/
@RestController
@RequestMapping("/plugin/scale")
public class HeartRateRecordController {

    @Autowired
    private HeartRateRecordService heartRateRecordService;

    @PostMapping("/add_heart_rate_record")
    public ResultModel addHeartRateRecord(@RequestBody @Valid final HeartRateRecordInput input,
        final BindingResult result) {

        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        final String userId = "20133439";
        this.heartRateRecordService.addHeartRateRecord(userId, input);
        return ResultUtil.success();
    }

}
