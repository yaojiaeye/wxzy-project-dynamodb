package com.wxzy.aws.dynamodb.model.output;

import java.util.Date;

import lombok.Data;

@Data
public class WeatherDataList {
    private Date datetime;
    private boolean data_available;
    private WeatherIndexsList indexes;

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setData_available(boolean data_available) {
        this.data_available = data_available;
    }

    public boolean getData_available() {
        return data_available;
    }

    public void setIndexes(WeatherIndexsList indexes) {
        this.indexes = indexes;
    }

    public WeatherIndexsList getIndexes() {
        return indexes;
    }
}
