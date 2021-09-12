/*
 * Copyright(c) 2019 Wyze Labs, All Rights Reserved.
 */

package com.wxzy.aws.dynamodb.model.input;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ScaleRecordListInput {

    @JSONField(name = "scale_record_list_input")
    private List<ScaleRecordInput> scaleRecordInputList;
}
