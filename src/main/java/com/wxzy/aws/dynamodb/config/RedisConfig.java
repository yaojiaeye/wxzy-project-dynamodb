package com.wxzy.aws.dynamodb.config;// package com.wxzy.aws.dynamodb.config;
//
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
//
/// **
// * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
// **/
// @Configuration
// public class RedisConfig {
//
// /**
// *
// * @return
// */
// @Primary
// @Bean
// @ConfigurationProperties(prefix = "spring.cache.redis.shop")
// public RedisProperties shopRedisProperties() {
// return new RedisProperties();
// }
//
// /**
// *
// * @param properties
// * @return
// */
// @Bean
// public RedisTemplate shopRedisTemplate(@Qualifier("shopRedisProperties") final RedisProperties properties) {
// return this.redisTemplate(properties);
// }
//
// private RedisTemplate redisTemplate(final RedisProperties properties) {
//
// final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
// configuration.setHostName(properties.getHost());
// configuration.setPort(properties.getPort());
// configuration.setDatabase(properties.getDatabase());
//
// final JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
//
// final RedisTemplate redisTemplate = new RedisTemplate();
// redisTemplate.setConnectionFactory(factory);
// redisTemplate.setKeySerializer(new StringRedisSerializer());
// redisTemplate.setValueSerializer(new StringRedisSerializer());
// redisTemplate.setHashKeySerializer(new StringRedisSerializer());
// redisTemplate.setHashValueSerializer(new StringRedisSerializer());
// return redisTemplate;
// }
// }
