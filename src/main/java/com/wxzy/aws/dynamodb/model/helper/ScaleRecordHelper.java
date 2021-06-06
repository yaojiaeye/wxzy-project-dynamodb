

package com.wxzy.aws.dynamodb.model.helper;

import com.wxzy.aws.dynamodb.model.input.ScaleRecordInput;
import com.wxzy.aws.dynamodb.model.pojo.ScaleRecord;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ScaleRecordHelper {

    public static ScaleRecord getScaleRecord(final ScaleRecordInput scaleRecordInput) {
        final ScaleRecord scaleRecord = new ScaleRecord();

        scaleRecord.setDeviceId(scaleRecordInput.getDeviceId());
        scaleRecord.setMeasureTs(scaleRecordInput.getMeasureTs());
        scaleRecord.setMac(scaleRecordInput.getMac());
        scaleRecord.setMeasureType(scaleRecordInput.getMeasureType());
        scaleRecord.setAge(scaleRecordInput.getAge());
        scaleRecord.setHeight(scaleRecordInput.getHeight());
        scaleRecord.setBodyType(scaleRecordInput.getBodyType());
        scaleRecord.setWeight(scaleRecordInput.getWeight());
        scaleRecord.setOccupation(scaleRecordInput.getOccupation());
        scaleRecord.setBmi(scaleRecordInput.getBmi());
        scaleRecord.setBmr(scaleRecordInput.getBmr());
        scaleRecord.setProtein(scaleRecordInput.getProtein());
        scaleRecord.setBodyFat(scaleRecordInput.getBodyFat());
        scaleRecord.setBodyVfr(scaleRecordInput.getBodyVfr());
        scaleRecord.setBodyWater(scaleRecordInput.getBodyWater());
        scaleRecord.setBoneMineral(scaleRecordInput.getBoneMineral());
        scaleRecord.setMuscle(scaleRecordInput.getMuscle());
        scaleRecord.setImpedance1(scaleRecordInput.getImpedance1());
        scaleRecord.setImpedance2(scaleRecordInput.getImpedance2());
        scaleRecord.setImpedance3(scaleRecordInput.getImpedance3());
        scaleRecord.setImpedance4(scaleRecordInput.getImpedance4());
        scaleRecord.setGender(scaleRecordInput.getGender());
        scaleRecord.setMetabolicAge(scaleRecordInput.getMetabolicAge());
        scaleRecord.setFamilyMemberId(scaleRecordInput.getFamilyMemberId());
        scaleRecord.setTimezone(scaleRecordInput.getTimezone());

        return scaleRecord;
    }
}
