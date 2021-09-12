package com.wxzy.aws.dynamodb.watch;

import java.util.List;

import lombok.Data;

public class DailyActivityDataManagement {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<stepslist> steps_list;

    private List<caloriesList> calories_list;

    private List<distancelist> distance_list;

    @Data
    private static class stepslist {

        private String date;

        private List<Integer> steps_list;
    }

    @Data
    private static class caloriesList {

        private String date;

        private List<Integer> calories_list;
    }

    @Data
    private static class distancelist {

        private String date;

        private List<Integer> distance_list;
    }

}
