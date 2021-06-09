package com.wxzy.aws.dynamodb.service;

import com.wxzy.aws.dynamodb.model.pojo.UserProfile;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface UserProfileService {

    public UserProfile queryUserData(String userId);

}
