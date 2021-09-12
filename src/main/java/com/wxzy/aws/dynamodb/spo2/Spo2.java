package com.wxzy.aws.dynamodb.spo2;

import java.util.List;

public class Spo2 {

    private List<spo> list;

    private static class spo {

        private String deviceId;

        private String deviceModel;

        private String date;

        private String spo;
    }

}
