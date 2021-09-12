package com.wxzy.aws.dynamodb.goal;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Index {

    private Setting disturb;

    private Setting reminder;

    private Setting wake;

    private Goal goal;

    private List<Alarm> alarms;

    private Weather weather;

    private static class Setting {

        private boolean is_on;

        @JSONField(name = "start_time")
        private int start_time;

        @JSONField(name = "end_time")
        private int end_time;
    }

    private static class Goal {

        @JSONField(name = "date")
        private String date;

        @JSONField(name = "step_goal")
        private String step_goal;

        @JSONField(name = "calorie_goal")
        private String calorie_goal;
    }

    private static class Alarm {

        private int time;

        private int repeat;
    }

    private static class Weather {

        @JSONField(name = "is_auto")
        private int isAuto;

        @JSONField(name = "city_id")
        private boolean cityId;

        @JSONField(name = "unit")
        private int unit;
    }

}
