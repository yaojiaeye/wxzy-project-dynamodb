package com.wxzy.aws.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author <a href="jiayao:little@163.com">little</a>
 * version: 1.0
 * Description:xxxxxx
 **/
@SpringBootApplication
public class DynamodbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamodbApplication.class, args);
    }
}
