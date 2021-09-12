package com.wxzy.aws.dynamodb.watch.vo;

import java.util.List;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Data
public class A {

    private List<C> steps_list;

    private List<C> calories_list;

    private static class C {

        private Integer value;

        private String date;

    }

}
