package com.wxzy.aws.dynamodb.task;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
public class TaskDownlodS3 implements Runnable {

    private String key;

    public TaskDownlodS3(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        DownloadS3File.execute(key);
    }

}
