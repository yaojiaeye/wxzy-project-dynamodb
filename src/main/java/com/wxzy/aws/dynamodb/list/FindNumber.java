package com.wxzy.aws.dynamodb.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FindNumber {

    public static void main(String[] args) {
        Integer[] a = new Integer[] { 6, 3, 9, 3, 2, 4, 5, 7 };
        Integer[] b = new Integer[] { 5, 8, 6, 2, 1, 9 };
        List _a = Arrays.asList(a);
        List _b = Arrays.asList(b);

        // 创建集合
        Collection realA = new ArrayList<Integer>(_a);
        Collection realB = new ArrayList<Integer>(_b);

        // 求交集
        realA.retainAll(realB);
        System.out.println("交集结果：" + realA);
    }

}
