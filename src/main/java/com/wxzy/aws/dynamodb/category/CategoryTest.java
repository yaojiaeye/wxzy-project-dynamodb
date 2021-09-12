package com.wxzy.aws.dynamodb.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryTest {
    public static void main(String[] args) {

        List<PersonData> list = new ArrayList<PersonData>();

        PersonData p1 = new PersonData();
        p1.setId("1");
        p1.setName("张三");
        p1.setType("管理员");
        p1.setAge(20);
        list.add(p1);

        PersonData p2 = new PersonData();
        p2.setId("2");
        p2.setName("李四");
        p2.setType("管理员");
        p2.setAge(30);
        list.add(p2);

        PersonData p3 = new PersonData();
        p3.setId("3");
        p3.setName("王五");
        p3.setType("用户");
        p3.setAge(40);
        list.add(p3);

        PersonData p4 = new PersonData();
        p4.setId("4");
        p4.setName("马六");
        p4.setType("访客");
        p4.setAge(50);
        list.add(p4);

        Map<String, List<PersonData>> collect = list.stream().collect(Collectors.groupingBy(PersonData::getId));
        System.out.println(collect);
    }
}
