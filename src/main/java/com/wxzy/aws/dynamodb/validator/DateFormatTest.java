package com.wxzy.aws.dynamodb.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wxzy.aws.dynamodb.model.enums.GardenSubtypesEnum;
import com.wxzy.aws.dynamodb.model.pojo.DeviceZone;

public class DateFormatTest {
    public static void main(String[] args) {
        DeviceZone deviceZone = new DeviceZone();
        deviceZone.setDidUid("did_uid");
        deviceZone.setDeviceId("did");
        deviceZone.setZoneId("zid");
        deviceZone.setManualCropCoefficient(Float.valueOf(10));
        deviceZone.setManualRootDepth(Float.valueOf(12));
        List<DeviceZone.GardenSubtypes> gardenSubtypes = new ArrayList<>();
        DeviceZone.GardenSubtypes gardenSubtype = new DeviceZone.GardenSubtypes();
        gardenSubtype.setSubtype(GardenSubtypesEnum.queryByType("CARROTS").getSubType());
        gardenSubtype.setPlantDate(new Date().toString());
        gardenSubtypes.add(gardenSubtype);
        deviceZone.setGardenSubtypes(gardenSubtypes);
        System.out.println(deviceZone);

        System.out.println(
            org.apache.commons.validator.DateValidator.getInstance().isValid("2021-01-31", "yyyy-MM-dd", false));
    }
}
