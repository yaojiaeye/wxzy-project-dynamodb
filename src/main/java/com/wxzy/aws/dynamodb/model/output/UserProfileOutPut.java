package com.wxzy.aws.dynamodb.model.output;

import java.io.Serializable;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Data
public class UserProfileOutPut implements Serializable {

    private String userId;

    private String age;

    private Float weight;

    private Float height;

    private String gender;
}
