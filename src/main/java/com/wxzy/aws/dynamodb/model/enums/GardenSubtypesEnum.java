package com.wxzy.aws.dynamodb.model.enums;

import org.apache.commons.lang.StringUtils;

public enum GardenSubtypesEnum {

    CARROTS("CARROTS"), CUCUMBER("CUCUMBER"), GREEN_BEANS("GREEN_BEANS"), LETTUCE("LETTUCE"), ONIONS("ONIONS"),
    PEAS("PEAS,"), SQUASH_ZUCCHINI("SQUASH_ZUCCHINI"), STRAWBERRY("STRAWBERRY"), SWEET_PEPPERS("SWEET_PEPPERS"),
    TOMATO("TOMATO"), WATERMELON("WATERMELON");

    private String subType;

    GardenSubtypesEnum(String subType) {
        this.subType = subType;
    }

    public static GardenSubtypesEnum queryByType(String serializeType) {
        if (StringUtils.isBlank(serializeType)) {
            return null;
        }

        for (GardenSubtypesEnum subType : GardenSubtypesEnum.values()) {
            if (StringUtils.equals(serializeType, subType.getSubType())) {
                return subType;
            }
        }
        return null;
    }

    public String getSubType() {
        return subType;
    }

    public static void main(String[] args) {
        System.out.println(GardenSubtypesEnum.queryByType("CARROTSS"));
    }

}
