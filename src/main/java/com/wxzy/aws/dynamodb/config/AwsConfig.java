package com.wxzy.aws.dynamodb.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.wyze.awsutils.secretsmanager.SecretsManagerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class AwsConfig {


    @Value("${spring.profiles.active}")
    private String profile ;

    @Value("${aws.region}")
    private String region ;

    /**
     * @param accessKey
     * @param secretKey
     * @return
     */
    @Bean
    @Profile("dev")
    public AWSCredentialsProvider staticCredentialsProvider(@Value("${aws.access-key}") final String accessKey,
        @Value("${aws.secret-key}") final String secretKey) {

        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);

        return provider;
    }

    /**
     * @param awsCredentialsProvider
     * @return
     */
    @Bean
    public SecretsManagerHelper secretsManagerHelper(final AWSCredentialsProvider awsCredentialsProvider) {
        return new SecretsManagerHelper(awsCredentialsProvider, this.region);
    }

    /**
     * @param awsCredentialsProvider
     * @return
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB(final AWSCredentialsProvider awsCredentialsProvider) {

        if ("dev".equals(this.profile)) {
            return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", this.region)).build();
        }

        return AmazonDynamoDBClientBuilder.standard().withRegion(this.region).withCredentials(awsCredentialsProvider)
            .build();
    }
}
