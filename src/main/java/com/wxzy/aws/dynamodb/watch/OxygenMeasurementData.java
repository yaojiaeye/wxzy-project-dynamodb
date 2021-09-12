package com.wxzy.aws.dynamodb.watch;

import java.util.List;

import lombok.Data;

public class OxygenMeasurementData {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<c> spo_list;

    @Data
    private static class c {

        private String date;

        private int spo;

    }

}
