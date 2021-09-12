package com.wxzy.aws.dynamodb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxzy.aws.dynamodb.service.DeviceZoneService;
import com.wyze.common.request.BodyReaderHttpServletRequestWrapper;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import com.wyze.common.util.EncryptUtil;
import com.wyze.common.util.HMacMD5Util;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plugin/himalia")
@Slf4j
public class PluginController {

    private static final String PLAN_DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private DeviceZoneService deviceZoneService;

    @PostMapping("/activity_data")
    public ResultModel saveZone(HttpServletRequest request) {

        try {
            final BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
            String accessToken =
                "lvtx.UVnM1iidgAiXPOPs8FKdHJgqVMC6Lsy/EkNxrc62g1PL4Ax6W7ELdJqreLzT1qSP4gf2Nc2LLauUKQLnlITuqbjBmv5MMRK7D/7gLvKYA+c/nXzKj0iI83V2mE67mFmKkwg8XKMYGONyIXkJzj3nO4kL9JjpSLwsqsiGmJbGsQQZYNOh2l7DOENk/XU3awnmol/VQg==";
            String appKey = "XXXXXXXXXXX";
            final String md5 = EncryptUtil.getMD5(accessToken + appKey);
            final String oldSign = HMacMD5Util.HMacMD5Signature(md5, HMacMD5Util.getSignBody(requestWrapper));
            System.out.println(oldSign + "bbbvCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
            return ResultUtil.success(oldSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.success();
    }
}
