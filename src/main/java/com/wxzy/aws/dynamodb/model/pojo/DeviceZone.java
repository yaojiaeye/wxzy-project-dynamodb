/*
 * Copyright(c) 2020 Wyze Labs, All Rights Reserved.
 */

package com.wxzy.aws.dynamodb.model.pojo;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.wyze.common.util.DateUtil;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@DynamoDBTable(tableName = "t_irrigation_zone")
public class DeviceZone {
    @DynamoDBHashKey(attributeName = "did_uid")
    @JSONField(name = "did_uid")
    private String didUid;

    @NotEmpty(message = "device_id is need")
    @JSONField(name = "device_id")
    @DynamoDBAttribute(attributeName = "device_id")
    private String deviceId;

    @NotEmpty(message = "zone_id is need")
    @DynamoDBRangeKey(attributeName = "zone_id")
    @JSONField(name = "zone_id")
    private String zoneId;

    @JSONField(name = "user_id")
    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute
    private Long updated = DateUtil.getCurrentTime();

    @JSONField(name = "enabled")
    @DynamoDBNamed("enabled")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private Boolean isEnabled;

    @JSONField(name = "zone_number")
    @DynamoDBAttribute(attributeName = "zone_number")
    private Integer zoneNumber;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private Double area;

    @JSONField(name = "available_water_capacity")
    @DynamoDBAttribute(attributeName = "available_water_capacity")
    private Double availableWaterCapacity;

    @JSONField(name = "root_depth")
    @DynamoDBAttribute(attributeName = "root_depth")
    private Float rootDepth;

    @DynamoDBAttribute
    private Double efficiency;

    @JSONField(name = "crop_coefficient")
    @DynamoDBAttribute(attributeName = "crop_coefficient")
    private Double cropCoefficient;

    @JSONField(name = "manage_allow_depletion")
    @DynamoDBAttribute(attributeName = "manage_allow_depletion")
    private Double manageAllowDepletion;

    @JSONField(name = "soil_type")
    @DynamoDBAttribute(attributeName = "soil_type")
    private String soilType;

    @JSONField(name = "crop_type")
    @DynamoDBAttribute(attributeName = "crop_type")
    private String cropType;

    @JSONField(name = "nozzle_type")
    @DynamoDBAttribute(attributeName = "nozzle_type")
    private String nozzleType;

    @JSONField(name = "exposure_type")
    @DynamoDBAttribute(attributeName = "exposure_type")
    private String exposureType;

    @JSONField(name = "photo_url")
    @DynamoDBAttribute(attributeName = "photo_url")
    private String photoUrl;

    @JSONField(name = "slope_type")
    @DynamoDBAttribute(attributeName = "slope_type")
    private String slopeType;

    @JSONField(name = "zone_disable_reason")
    @DynamoDBAttribute(attributeName = "zone_disable_reason")
    private String zoneDisableReason;

    @JSONField(name = "flow_rate")
    @DynamoDBAttribute(attributeName = "flow_rate")
    private Double flowRate;

    @JSONField(name = "wired")
    @DynamoDBNamed("wired")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private Boolean isWired;

    @JSONField(name = "soil_moisture_level_at_end_of_day_pct")
    @DynamoDBAttribute(attributeName = "soil_moisture_level_at_end_of_day_pct")
    private Double soilMoistureLevelAtEndOfDayPct;

    @JSONField(name = "smart_schedule_id")
    @DynamoDBAttribute(attributeName = "smart_schedule_id")
    private String smartScheduleId;

    @JSONField(name = "smart_duration")
    @DynamoDBAttribute(attributeName = "smart_duration")
    private Long smartDuration;

    @DynamoDBTypeConvertedJson
    private List<Map<String, Object>> schedules;

    @JSONField(name = "latest_events")
    @DynamoDBAttribute(attributeName = "latest_events")
    private List<LatestEventsBean> latestEvents;

    @JSONField(name = "camera_device_id")
    @DynamoDBAttribute(attributeName = "camera_device_id")
    private String cameraDeviceId;

    @JSONField(name = "number_of_sprinkler_heads")
    @DynamoDBAttribute(attributeName = "number_of_sprinkler_heads")
    private Integer numberOfSprinklerHeads;

    // 新增字段
    @JSONField(name = "garden_subtypes")
    @DynamoDBAttribute(attributeName = "garden_subtypes")
    private List<GardenSubtypes> gardenSubtypes;

    @JSONField(name = "tree_subtypes")
    @DynamoDBAttribute(attributeName = "tree_subtypes")
    private List<TreeSubtypes> treeSubtypes;

    @JSONField(name = "manual_root_depth")
    @DynamoDBAttribute(attributeName = "manual_root_depth")
    private Float manualRootDepth;

    @JSONField(name = "manual_crop_coefficient")
    @DynamoDBAttribute(attributeName = "manual_crop_coefficient")
    private Float manualCropCoefficient;

    @Data
    @DynamoDBDocument
    public static class LatestEventsBean {
        /**
         * duration : 7 end_local : 2020-09-25T21:13:04 end_ts : 1601082784 schedule_name : App Quick Run schedule_type
         * : MANUAL
         */
        @JSONField(name = "duration")
        @DynamoDBAttribute(attributeName = "duration")
        private int duration;
        @JSONField(name = "end_local")
        @DynamoDBAttribute(attributeName = "end_local")
        private String endLocal;
        @JSONField(name = "end_ts")
        @DynamoDBAttribute(attributeName = "end_ts")
        private int endTs;
        @JSONField(name = "schedule_name")
        @DynamoDBAttribute(attributeName = "schedule_name")
        private String scheduleName;
        @JSONField(name = "schedule_type")
        @DynamoDBAttribute(attributeName = "schedule_type")
        private String scheduleType;
    }

    @Data
    @DynamoDBDocument
    public static class GardenSubtypes {

        @JSONField(name = "subtype")
        @DynamoDBAttribute(attributeName = "subtype")
        private String subtype;

        @JSONField(name = "plant_date")
        @DynamoDBAttribute(attributeName = "plant_date")
        private String plantDate;

    }

    @Data
    @DynamoDBDocument
    public static class TreeSubtypes {

        @JSONField(name = "subtype")
        @DynamoDBAttribute(attributeName = "subtype")
        private String subtype;

        @JSONField(name = "plant_date")
        @DynamoDBAttribute(attributeName = "plant_date")
        private String plantDate;

    }
}
