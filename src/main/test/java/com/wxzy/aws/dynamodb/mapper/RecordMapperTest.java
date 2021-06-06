package com.wxzy.aws.dynamodb.mapper;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.wxzy.aws.dynamodb.mapper.impl.RecordMapperImpl;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.containers.GenericContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class RecordMapperTest {

    @InjectMocks
    private RecordMapperImpl recordMapper;

    private static AmazonDynamoDB client;

    private DynamoDBMapper mapper;

    private final Long ts = 1L;
    private final String deviceId = "did";
    private final String userId = "uid";

    private static final int DYNAMO_PORT = 8000;

    @ClassRule
    public static GenericContainer dynamoDb =
        new GenericContainer("amazon/dynamodb-local:1.12.0").withExposedPorts(DYNAMO_PORT);

    @BeforeClass
    public static void setup() {
        final String endpoint =
            String.format("http://%s:%s", dynamoDb.getContainerIpAddress(), dynamoDb.getMappedPort(DYNAMO_PORT));
        client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-west-2")).build();

        CreateTableRequest tableRequest = new DynamoDBMapper(client).generateCreateTableRequest(ScaleRecord.class);
        tableRequest.setBillingMode(BillingMode.PAY_PER_REQUEST.toString());
        // tableRequest.getLocalSecondaryIndexes().get(0).setProjection(new
        // Projection().withProjectionType(ProjectionType.ALL));
        // tableRequest.getLocalSecondaryIndexes().get(1).setProjection(new
        // Projection().withProjectionType(ProjectionType.ALL));
        client.createTable(tableRequest);

        final ScaleRecord scaleRecord = new ScaleRecord();
        scaleRecord.setMeasureTs(0L);
        scaleRecord.setDataId(1L);
        scaleRecord.setUserId("uid");
        scaleRecord.setFamilyMemberId("uid");
        scaleRecord.setIsDelete(0);
        new DynamoDBMapper(client).save(scaleRecord);
    }

    @Before
    public void init() {
        mapper = new DynamoDBMapper(client);
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(recordMapper, "amazonDynamoDB", client);
    }

    @AfterClass
    public static void clean() {
        dynamoDb.stop();
    }

    @Test
    public void save_OK() {
        final ScaleRecord scaleRecord = new ScaleRecord();
        scaleRecord.setMeasureTs(this.ts);
        scaleRecord.setDataId(this.ts);
        scaleRecord.setUserId(this.userId);
        this.recordMapper.save(scaleRecord);
    }

    @Test
    public void batchSave_OK() {
        List<ScaleRecord> list = new ArrayList<>(2);
        final ScaleRecord scaleRecord = new ScaleRecord();
        scaleRecord.setMeasureTs(this.ts);
        scaleRecord.setDataId(this.ts);
        scaleRecord.setUserId(this.userId);
        list.add(scaleRecord);
        List<DynamoDBMapper.FailedBatch> batches = this.recordMapper.batchSave(list);
        assertEquals(batches.size(), 0);
    }

}
