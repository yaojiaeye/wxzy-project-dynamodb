package com.wxzy.aws.dynamodb.watch;

import java.util.List;

import lombok.Data;

public class HeartRateMeasurementData {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<c> heat_list;

    @Data
    private static class c {

        private String date;

        List<Integer> heart_rate_list;

    }
}
