package com.wxzy.aws.dynamodb.mapper;

import java.util.List;

import com.wxzy.aws.dynamodb.model.pojo.UserLibrary;

public interface UserLibraryMapper {

    void save(UserLibrary library);

    UserLibrary getPictureLib(String devicePrimaryKey, String version);

    List<UserLibrary> getPictureLibByVersion(String version);
}
