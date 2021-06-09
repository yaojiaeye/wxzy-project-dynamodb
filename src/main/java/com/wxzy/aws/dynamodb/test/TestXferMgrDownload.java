package com.wxzy.aws.dynamodb.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.wxzy.aws.dynamodb.model.helper.DynamodbTestHelper;
import com.wxzy.aws.dynamodb.task.Download;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:实现从Amazon S3文件的下载
 **/
public class TestXferMgrDownload {

    private static final String ACCESS_KEY = "ASIA5G2LGWHZSPAMVFNW";

    private static final String SECRET_KEY = "A6GTDgVBSbx2tUqfS1/kuAw8/+m4KG+JD+GDPwTA";

    private static final String BUCKET_NAME = "w-scale-res-908006437363-us-west-2";

    private static final String REGION_NAME = "us-west-2";

    private static final String ROOT_PATH = "/";

    private static final String SPLIT_LINE = "\\/";

    static final BasicSessionCredentials credentials =
        new BasicSessionCredentials(ACCESS_KEY, SECRET_KEY, DynamodbTestHelper.session_token);

    // static final ListObjectsRequest listObjectsRequest =
    // new ListObjectsRequest().withBucketName(BUCKET_NAME).withPrefix("scale/2021-06-06/");

    static final ListObjectsRequest listObjectsRequest =
        new ListObjectsRequest(BUCKET_NAME, "scale/2021-05-08/", null, null, 4000);

    private static final AmazonS3 s3 =
        AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
            // 设置服务器所属地区
            .withRegion(REGION_NAME).build();

    public static void main(String[] args) throws Exception {
        recursionAllFiles();
    }

    private static S3Object getFile(String bucketName, String key) {
        S3Object s3Object = null;
        try {
            s3Object = s3.getObject(bucketName, key);
        } catch (AmazonServiceException e) {
        } catch (SdkClientException e) {
        }
        return s3Object;
    }

    private static void copyRecursionFile(String key) throws Exception {
        S3Object fileObject = getFile(key);
        String[] splitPath = fileObject.getKey().split(SPLIT_LINE);
        String uploadTgzBasePath = DynamodbTestHelper.S3_FILE_PATH + ROOT_PATH
            + fileObject.getKey().substring(0, fileObject.getKey().length() - 1);;
        String updateTgzPath = uploadTgzBasePath + ROOT_PATH + splitPath[splitPath.length - 1];
        File uploadPathDir = new File(uploadTgzBasePath);
        if (!uploadPathDir.exists()) {
            if (!uploadPathDir.mkdirs()) {
                throw new IOException(String.format("mkdirs error, path=%s", uploadPathDir.getCanonicalPath()));
            }
        }
        IOUtils.copy(fileObject.getObjectContent(), new FileOutputStream(updateTgzPath));
    }

    private static S3Object getFile(String key) {
        return getFile(BUCKET_NAME, key);
    }

    private static void recursionAllFiles() throws Exception {

        ObjectListing current = s3.listObjects(listObjectsRequest);
        List<S3ObjectSummary> keyList = new ArrayList<>(current.getObjectSummaries());
        while (current.isTruncated()) {
            current = s3.listNextBatchOfObjects(current);
            keyList.addAll(current.getObjectSummaries());
        }
        Download download = new Download();
        download.build(keyList);
    }

}
