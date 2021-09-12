
package com.wxzy.aws.dynamodb.model.enums;
public enum RecordDeleteEnum {

    DELETED(1),
    NOT_DELETED(0);

    private Integer value;

    RecordDeleteEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
