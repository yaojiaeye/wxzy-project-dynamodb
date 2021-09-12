package com.wxzy.aws.dynamodb.model.enums;

import org.apache.commons.lang.StringUtils;

public enum TreeSubtypesEnum {

    APPLE("APPLE"), BANANA("BANANA"), CITRUS("CITRUS"), CHERRY("CHERRY"), GRAPE("GRAPE"), OLIVES("OLIVES"),
    PEACH("PEACH"), PISTACHIOS("PISTACHIOS"), WALNUT_ORCHARD("WALNUT_ORCHARD");

    private String subType;

    private TreeSubtypesEnum(String subType) {
        this.subType = subType;
    }

    public static TreeSubtypesEnum queryByType(String type) {
        if (StringUtils.isBlank(type)) {
            return null;
        }

        for (TreeSubtypesEnum subType : TreeSubtypesEnum.values()) {
            if (StringUtils.equals(type, subType.getSubType())) {
                return subType;
            }
        }
        return null;
    }

    public String getSubType() {
        return subType;
    }

    public static void main(String[] args) {
        System.out.println(TreeSubtypesEnum.queryByType("APPLEe"));
    }

}
