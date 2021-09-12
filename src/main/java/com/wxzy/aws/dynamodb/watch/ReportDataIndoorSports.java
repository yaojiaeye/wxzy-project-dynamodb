package com.wxzy.aws.dynamodb.watch;

import java.util.List;

import lombok.Data;

public class ReportDataIndoorSports {

    private String device_id;

    private String user_id;

    private String device_model;

    private List<c> report_list;

    @Data
    private static class c {

        private String date;

        private String start_time;
        private String end_time;
        private int sum_time;
        private int sum_distance;
        private int sum_calorie;
        private int sum_steps;
        private int max_pace;
        private int min_pace;
        private int max_step_frequency;
        private int ave_heart_rate;
        private int max_heart_rate;
        private int min_heart_rate;
        private float training_effect;
        private int max_oxygen_uptake;
        private int body_energy_cost;
        private int estimated_recovery_time;
        private int peak_time;
        private int anaerobic_time;
        private int cardio_time;
        private int fat_reduction_time;
        private int warn_up_time;

    }

}
