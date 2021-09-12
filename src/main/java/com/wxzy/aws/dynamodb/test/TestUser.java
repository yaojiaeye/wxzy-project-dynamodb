package com.wxzy.aws.dynamodb.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.wxzy.aws.dynamodb.model.helper.DynamodbTestHelper;
import com.wxzy.aws.dynamodb.model.output.UserProfileOutPut;
import com.wxzy.aws.dynamodb.model.pojo.UserProfile;
import com.wyze.common.util.StringUtil;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class TestUser {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = getClient(DynamodbTestHelper.ACCESS_KEY, DynamodbTestHelper.SECRET_KEY,
            DynamodbTestHelper.SESSION, "us-west-2");
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        List<UserProfileOutPut> userProfileList = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(DynamodbTestHelper.FILE_PATH));
        List<String> list = getString(inputStream);
        for (String userId : list) {
            Thread.sleep(200);
            UserProfile userProfile = mapper.load(UserProfile.class, userId);
            UserProfileOutPut outPut = new UserProfileOutPut();
            outPut.setUserId(userProfile.getUserId());
            if (StringUtil.isNotEmpty(userProfile.getBirthdate())) {
                outPut.setAge(age(userProfile.getBirthdate()));
            }
            if (userProfile.getHeight() != null) {
                outPut.setHeight(userProfile.getHeight());
            }
            if (userProfile.getWeight() != null) {
                outPut.setWeight(userProfile.getWeight());
            }
            if (userProfile.getGender() != null) {
                outPut.setGender(
                    userProfile.getGender() == 0 ? DynamodbTestHelper.MAN_NAME : DynamodbTestHelper.WOMAN_NAME);
            }
            userProfileList.add(outPut);
        }

        EasyExcel.write(DynamodbTestHelper.FILE_NAME, UserProfileOutPut.class).sheet("用户数据").doWrite(userProfileList);

    }

    public static AmazonDynamoDB getClient(final String accessKey, final String secretKey, final String session,
        final String region) {
        final BasicSessionCredentials credentials = new BasicSessionCredentials(accessKey, secretKey, session);
        final AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
        return AmazonDynamoDBClientBuilder.standard().withRegion(region).withCredentials(provider).build();
    }

    /**
     * 从输入流中获取字符串
     */
    public static List<String> getString(InputStream is) {
        List<String> list = new ArrayList<>(2000);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                list.add(split[1]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String age(String str) {
        Calendar cal = Calendar.getInstance();
        int a = Integer.parseInt((str.subSequence(0, 4).toString()));
        int b = cal.get(Calendar.YEAR);
        if (a <= b) {
            return (b - a) + "";
        } else {
            return null;
        }
    }
}
