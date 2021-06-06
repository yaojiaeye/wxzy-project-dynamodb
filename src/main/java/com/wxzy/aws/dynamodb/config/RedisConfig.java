///*
// * Copyright(c) 2020 Wyze Labs, All Rights Reserved.
// */
//
//package com.wxzy.aws.dynamodb.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    /**
//     * redisTemplate
//     *
//     * @return
//     */
//    @Bean("redisTemplate")
//    public RedisTemplate<String, String> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
//
//        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
//
//}
