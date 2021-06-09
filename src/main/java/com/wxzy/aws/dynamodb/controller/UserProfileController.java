package com.wxzy.aws.dynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxzy.aws.dynamodb.service.UserProfileService;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@RestController
@RequestMapping("/plugin/scale")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/user_data_export")
    public ResultModel userDataExport() {
        String userId = "8d4fa51b8cc02aaecdea9ffa16232e7b";
        userProfileService.queryUserData(userId);
        return ResultUtil.success();
    }
}
