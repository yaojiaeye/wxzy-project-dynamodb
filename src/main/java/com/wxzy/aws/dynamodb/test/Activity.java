package com.wxzy.aws.dynamodb.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Activity {

    public static void main(String[] args) throws IOException {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("tHimaliaActivity");

        JsonParser parser = new JsonFactory().createParser(new File("D://activity.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode)iter.next();

            String did_uid = currentNode.path("did_uid").asText();
            long record_date = currentNode.path("record_date").asLong();
            int calorie_goal = currentNode.path("calorie_goal").asInt();
            int calories = currentNode.path("calories").asInt();
            String calories_list = currentNode.path("calories_list").asText();
            long create_time = currentNode.path("create_time").asLong();
            String date = currentNode.path("date").asText();
            String date_time = currentNode.path("date_time").asText();
            String device_id = currentNode.path("device_id").asText();
            String device_model = currentNode.path("device_model").asText();
            String distance_list = currentNode.path("distance_list").asText();
            String steps_list = currentNode.path("steps_list").asText();
            int effective_stand = currentNode.path("effective_stand").asInt();
            int heart_rate = currentNode.path("heart_rate").asInt();
            int heart_rate_ave = currentNode.path("heart_rate_ave").asInt();
            int heart_rate_max = currentNode.path("heart_rate_max").asInt();
            String heart_rate_max_date = currentNode.path("heart_rate_max_date").asText();
            int heart_rate_min = currentNode.path("heart_rate_min").asInt();
            String heart_rate_min_date = currentNode.path("heart_rate_min_date").asText();
            int heart_rate_rest = currentNode.path("heart_rate_rest").asInt();
            int spo = currentNode.path("spo").asInt();
            int step_goal = currentNode.path("step_goal").asInt();
            int steps = currentNode.path("steps").asInt();
            long update_time = currentNode.path("update_time").asInt();
            String user_id = currentNode.path("user_id").asText();

            try {
                table.putItem(new Item().withPrimaryKey("did_uid", did_uid, "record_date", record_date)
                    .withInt("calorie_goal", calorie_goal).withInt("calories", calories)
                    .withLong("create_time", create_time).withString("date", date).withString("date_time", date_time)
                    .withString("device_id", device_id).withString("device_model", device_model)
                    .withString("distance_list", distance_list).withString("steps_list", steps_list)
                    .withInt("effective_stand", effective_stand).withInt("heart_rate", heart_rate)
                    .withInt("heart_rate_ave", heart_rate_ave).withInt("heart_rate_max", heart_rate_max)
                    .withString("heart_rate_max_date", heart_rate_max_date)
                    .withString("heart_rate_min_date", heart_rate_min_date).withInt("heart_rate_min", heart_rate_min)
                    .withInt("heart_rate_rest", heart_rate_rest).withInt("spo", spo).withInt("step_goal", step_goal)
                    .withInt("steps", steps).withLong("update_time", update_time)
                    .withInt("heart_rate_min", heart_rate_min).withInt("heart_rate_rest", heart_rate_rest)
                    .withString("user_id", user_id).withString("calories_list", calories_list));
                System.out.println("PutItem succeeded: " + did_uid + " " + record_date);
            } catch (Exception e) {
                System.err.println("Unable to add movie: " + did_uid + " " + record_date);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }

}
