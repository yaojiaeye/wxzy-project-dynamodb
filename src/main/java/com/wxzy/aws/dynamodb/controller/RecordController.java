package com.wxzy.aws.dynamodb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordListInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import com.wxzy.aws.dynamodb.service.RecordService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
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

    /**
     * Batch upload of body composition measurement results
     * 
     * @param scaleRecordListInput
     * @param result
     * @return
     */
    @PostMapping("/add_record_bulk")
    public ResultModel addScaleRecordList(@RequestBody @Valid final ScaleRecordListInput scaleRecordListInput,
        final BindingResult result) {

        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        for (final ScaleRecordInput scaleRecordInput : scaleRecordListInput.getScaleRecordInputList()) {
            if (scaleRecordInput.getDeviceId() == null) {
                throw new GeneralException(ResultCode.ParameterError,
                    "Required String parameter 'device_id' is not present");
            }
            if (scaleRecordInput.getMeasureTs() == null) {
                throw new GeneralException(ResultCode.ParameterError,
                    "Required String parameter 'measure_ts' is not present");
            }
        }
        final String userId = "20133439";
        this.recordService.addScaleRecordList(userId, scaleRecordListInput.getScaleRecordInputList());
        return ResultUtil.success();
    }

    /**
     * get the user's last two measurement data
     * 
     * @param familyId
     * @return
     */
    @PostMapping("/get_latest_record")
    public ResultModel
        getLatestRecord(@RequestParam(value = "family_member_id", required = false) final String familyId) {
        final String userId = "20133439";
        String controllerFamilyMemberId = familyId;
        if (controllerFamilyMemberId == null) {
            controllerFamilyMemberId = userId;
        }
        List<ScaleRecord> scaleRecordList = this.recordService.getLatestRecord(userId, controllerFamilyMemberId);
        return ResultUtil.success(scaleRecordList, scaleRecordList.size(), scaleRecordList.size());
    }

}
