package com.wxzy.aws.dynamodb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxzy.aws.dynamodb.mapper.UserProfileMapper;
import com.wxzy.aws.dynamodb.model.pojo.UserProfile;
import com.wxzy.aws.dynamodb.service.UserProfileService;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public UserProfile queryUserData(String userId) {
        return userProfileMapper.loadUserInfo(userId);
    }
}
