package com.wxzy.aws.dynamodb.service.impl;

import org.springframework.stereotype.Service;

import com.wxzy.aws.dynamodb.model.input.HeartRateRecordInput;
import com.wxzy.aws.dynamodb.service.HeartRateRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Service
@Slf4j
public class EartRateRecordServiceImpl implements HeartRateRecordService {

    @Override
    public void addHeartRateRecord(String userId, HeartRateRecordInput heartRateRecordInput) {

    }
}
