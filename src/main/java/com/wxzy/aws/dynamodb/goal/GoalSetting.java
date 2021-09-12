package com.wxzy.aws.dynamodb.goal;

import com.alibaba.fastjson.annotation.JSONField;

public class GoalSetting {

    @JSONField(name = "date")
    private String date;

    @JSONField(name = "step_goal")
    private String step_goal;

    @JSONField(name = "calorie_goal")
    private String calorie_goal;

    @JSONField(name = "steps")
    private String steps;

    @JSONField(name = "calories")
    private String calories;

    @JSONField(name = "is_finsh")
    private int isFinsh;

}
