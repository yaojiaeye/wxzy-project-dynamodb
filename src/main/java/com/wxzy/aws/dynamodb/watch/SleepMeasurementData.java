package com.wxzy.aws.dynamodb.watch;

import java.util.List;

import lombok.Data;

public class SleepMeasurementData {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<c> sleep_list;

    @Data
    private static class c {

        private String date;

        private int sum_time;

        private int sum_dept_time;

        private int sum_light_time;

        private int sum_awake_time;

        private List<b> item_list;

    }

    @Data
    private static class b {

        private int time;

        private int mode;

    }
}
