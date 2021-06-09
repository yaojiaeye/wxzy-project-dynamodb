package com.wxzy.aws.dynamodb.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wxzy.aws.dynamodb.model.helper.DynamodbTestHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Slf4j
public class Download {

    private static final String ACCESS_KEY = "ASIA5G2LGWHZSPAMVFNW";

    private static final String SECRET_KEY = "A6GTDgVBSbx2tUqfS1/kuAw8/+m4KG+JD+GDPwTA";

    private static final String BUCKET_NAME = "w-scale-res-908006437363-us-west-2";

    private static final String REGION_NAME = "us-west-2";

    private static final String ROOT_PATH = "/";

    private static final String SPLIT_LINE = "\\/";

    static final BasicSessionCredentials credentials =
        new BasicSessionCredentials(ACCESS_KEY, SECRET_KEY, DynamodbTestHelper.session_token);

    private static final AmazonS3 s3 =
        AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
            // 设置服务器所属地区
            .withRegion(REGION_NAME).build();

    /**
     * 使用线程池
     * 
     * @param keyList
     */
    public void build(List<S3ObjectSummary> keyList) {
        if (null != keyList && keyList.size() > 0) {
            ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("download-pool").build();
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(30, 40, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(4000), nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
            for (S3ObjectSummary summary : keyList) {
                TaskDownlod taskDownlod = new TaskDownlod(summary.getKey());
                poolExecutor.submit(taskDownlod);
            }
            poolExecutor.shutdown();
        }
    }

    public static void execute(String key) {
        S3Object fileObject = getFile(key);
        String[] splitPath = fileObject.getKey().split(SPLIT_LINE);
        String downlodTgzBasePath = DynamodbTestHelper.S3_FILE_PATH + ROOT_PATH
            + fileObject.getKey().substring(0, fileObject.getKey().length() - 1);
        String updateTgzPath = downlodTgzBasePath + ROOT_PATH + splitPath[splitPath.length - 1];
        File uploadPathDir = new File(downlodTgzBasePath);
        try {
            if (!uploadPathDir.exists()) {
                if (!uploadPathDir.mkdirs()) {
                    throw new IOException(String.format("mkdirs error, path=%s", uploadPathDir.getCanonicalPath()));
                }
            }
            IOUtils.copy(fileObject.getObjectContent(), new FileOutputStream(updateTgzPath));
        } catch (IOException e) {
            log.error("文件复制流出错", e);
        }
    }

    private static S3Object getFile(String key) {
        return getFile(BUCKET_NAME, key);
    }

    private static S3Object getFile(String bucketName, String key) {
        S3Object s3Object = null;
        try {
            Thread.sleep(200);
            s3Object = s3.getObject(bucketName, key);
        } catch (AmazonServiceException e) {
        } catch (Exception e) {
        }
        return s3Object;
    }
}
