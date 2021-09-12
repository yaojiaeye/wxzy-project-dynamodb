package com.wxzy.aws.dynamodb.watch;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class DailyActivityDataReport {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<c> list;

    @Data
    private static class c {

        private String date;

        private int steps;

        private int calories;

        private int spo;

        private int heart_rate;

        private int heart_rate_min;

        private int heart_rate_max;

        private int heart_rate_ave;

        private int heart_rate_rest;

        private int heart_rate_min_date;

        private int heart_rate_max_date;

        private int effective_stand;

        private List<Integer> steps_list = new ArrayList<>(24);

        private List<Integer> calories_list = new ArrayList<>(24);;

        private List<Integer> distance_list = new ArrayList<>(24);;

    }
}
