package com.wxzy.aws.dynamodb.air_quality;

import lombok.Data;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Data
public class FiresDailyApiData {

    private FiresDailyApi grass;

    private FiresDailyApi tree;

    private FiresDailyApi weed;

}
