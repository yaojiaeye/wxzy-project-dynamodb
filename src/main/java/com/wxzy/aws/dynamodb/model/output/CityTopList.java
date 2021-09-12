package com.wxzy.aws.dynamodb.model.output;

import java.io.Serializable;

import lombok.Data;

@Data
public class CityTopList implements Serializable {

    private String country;

    private String name;

    private Integer id;

    private Coord coord;

    @Data
    public static class Coord {

        private Float lon;

        private Float lat;

    }

}
