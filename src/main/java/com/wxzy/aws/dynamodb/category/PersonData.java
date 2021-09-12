package com.wxzy.aws.dynamodb.category;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonData implements Serializable {

    private String id;

    private String name;

    private String type;

    private int age;

}
