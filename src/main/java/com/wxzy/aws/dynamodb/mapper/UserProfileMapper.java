package com.wxzy.aws.dynamodb.mapper;

import com.wxzy.aws.dynamodb.model.pojo.UserProfile;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public interface UserProfileMapper {

    UserProfile loadUserInfo(String userId);
}
