package com.wxzy.aws.dynamodb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.wxzy.aws.dynamodb.model.input.DeviceInfoInput;
import com.wxzy.aws.dynamodb.model.pojo.DeviceInfo;
import com.wxzy.aws.dynamodb.service.DeviceInfoService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import com.wyze.common.util.StringUtil;

@RestController
@RequestMapping("/plugin/himalia")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @PostMapping("/device_info")
    public ResultModel setDeviceInfo(@RequestBody @Valid final DeviceInfoInput deviceInfoInput,
        final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new GeneralException(ResultCode.ParameterError, bindingResult.getFieldError().getDefaultMessage());
        }
        this.deviceInfoService.setDeviceInfo(deviceInfoInput);
        return ResultUtil.success();
    }

    @GetMapping("/device_info")
    public ResultModel getDeviceInfo(@RequestParam("device_id") final String deviceId, final String[] keys) {

        if (StringUtil.isEmpty(deviceId)) {
            throw new GeneralException(ResultCode.ParameterError,
                "device_id should not be null or empty in Get Device Info");
        }
        // 检查用户是否有权查询设备，并支持分享用户
        final DeviceInfo deviceInfo = this.deviceInfoService.getDeviceInfo(deviceId, keys);
        JSONObject settings = deviceInfo.getSettings();
        JSONObject o = (JSONObject)settings.get("goal");
        return ResultUtil.success(deviceInfo);
    }
}
