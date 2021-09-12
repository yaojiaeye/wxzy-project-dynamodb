package com.wxzy.aws.dynamodb.watch.vo;

import java.util.List;

public class latest7days {

    private Integer total_steps_year;

    private Integer total_sports_year;

    private Integer avg_steps_year;

    private Integer avg_sports_year;

    private Integer avg_calorie_year;

    private Integer avg_calorie_pre;

    private List<C> calorie_list;

    private static class C {

        private Integer value;

        private String date;

    }
}
