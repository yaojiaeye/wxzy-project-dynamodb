/*
 * Copyright(c) 2019 Wyze Labs, All Rights Reserved.
 */

package com.wxzy.aws.dynamodb.model.input;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class DataIDListInput {

    @NotNull(message = "data_id_list should not be null")
    @JSONField(name = "data_id_list")
    private List<Long> dataIdList;
}
