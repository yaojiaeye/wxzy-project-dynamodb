package com.wxzy.aws.dynamodb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alibaba.excel.EasyExcel;
import com.wxzy.aws.dynamodb.model.input.DataIDListInput;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.input.ScaleRecordListInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import com.wxzy.aws.dynamodb.service.RecordService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
@RestController
@RequestMapping("/plugin/scale")
public class RecordController {

    private static final Integer MAX_RECORD = 1000;

    @Autowired
    private RecordService recordService;

    @PostMapping("/add_record")
    public ResultModel addScaleRecord(@RequestBody @Valid final ScaleRecordInput scaleRecordInput,
        final BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        final String userId = "20133439";
        log.info("用户请求信息 userId  {} ", request.getHeader("yaojia"));
        // this.recordService.addScaleRecord(userId, scaleRecordInput);
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

    /**
     * delete
     *
     * @param dataIdList
     * @param result
     * @return
     */
    @PostMapping("/delete_record")
    public ResultModel delete(@RequestBody @Valid final DataIDListInput dataIdList, final BindingResult result) {
        if (result.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, result.getFieldError().getDefaultMessage());
        }
        final String userId = "20133439";
        this.recordService.removeScaleRecordList(userId, dataIdList);
        return ResultUtil.success();
    }

    /**
     * 
     * @param familyId
     * @param startTime
     * @param endTime
     * @param forward
     * @return
     */
    @PostMapping("/get_record_range")
    public ResultModel getRecordRange(@RequestParam(value = "family_member_id", required = false) final String familyId,
        @RequestParam(value = "start_time", required = false) final long startTime,
        @RequestParam(value = "end_time", required = false) final long endTime,
        @RequestParam(value = "forward", required = false) final Boolean forward) {

        Long startTs = startTime;
        if (startTs == null) {
            startTs = Long.MIN_VALUE;
        }
        Long endTs = endTime;
        if (endTs == null) {
            endTs = Long.MAX_VALUE;
        }
        Boolean resultForward = forward;
        if (resultForward == null) {
            resultForward = true;
        }

        final String userId = "20133439";
        String controllerFamilyMemberId = familyId;
        if (controllerFamilyMemberId == null) {
            controllerFamilyMemberId = userId;
        }
        List<ScaleRecord> recordList =
            recordService.getRecordList(userId, controllerFamilyMemberId, startTs, endTs, resultForward);
        return ResultUtil.success(recordList, recordList.size(), recordList.size());
    }

    /**
     * 
     * @param recordNumber
     * @param endTime
     * @param familyMemberId
     * @param forward
     * @return
     */
    @GetMapping("/get_record_list")
    public ResultModel list(@RequestParam(value = "record_number", required = false) final Integer recordNumber,
        @RequestParam(value = "end_time", required = false) final Long endTime,
        @RequestParam(value = "family_member_id", required = false) final String familyMemberId,
        @RequestParam(value = "forward", required = false) final Boolean forward) {

        Integer recordLimit = recordNumber;
        if (recordLimit == null) {
            recordLimit = MAX_RECORD;
        }
        Long endTs = endTime;
        if (endTs == null) {
            endTs = Long.MAX_VALUE;
        }
        Boolean resultForward = forward;
        if (resultForward == null) {
            resultForward = true;
        }

        final String userId = "20133439";
        String controllerFamilyMemberId = familyMemberId;
        if (controllerFamilyMemberId == null) {
            controllerFamilyMemberId = userId;
        }
        List<ScaleRecord> list = this.recordService.getRecordListByNumber(userId, controllerFamilyMemberId, recordLimit,
            endTs, resultForward);
        String fileName = "D:\\tmp\\" + "write" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, ScaleRecord.class).sheet("模板").doWrite(list);
        return ResultUtil.success(list, list.size(), list.size());
    }
}
