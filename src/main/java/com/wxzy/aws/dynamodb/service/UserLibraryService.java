package com.wxzy.aws.dynamodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxzy.aws.dynamodb.mapper.UserLibraryMapper;
import com.wxzy.aws.dynamodb.model.pojo.UserLibrary;

@Service
public class UserLibraryService {

    @Autowired
    private UserLibraryMapper userLibraryMapper;

    public void save(UserLibrary userLibrary) {
        userLibraryMapper.save(userLibrary);
    }

    public UserLibrary getPictureLib(String devicePrimaryKey, String version) {
        return userLibraryMapper.getPictureLib(devicePrimaryKey, version);
    }

    public List<UserLibrary> getPictureLibByVersion(String version) {
        return userLibraryMapper.getPictureLibByVersion(version);
    }

}
