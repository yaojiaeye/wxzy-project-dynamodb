package com.wxzy.aws.dynamodb.watch.vo;

import java.util.List;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class CustomerDial {

    private List<Recommend> recommend;

    private List<Preset> list;

    private static class Custom {

        private String pic_id;

        private String file_url;
    }

    private static class Recommend {

        private String pic_id;

        private String file_url;
    }

    private static class Preset {

        private String category_name;

        private List<A> images;

    }

    private static class A {

        private String pic_id;

        private String category_id;

        private String name;

        private String category_name;

        private String icon_url;

        private String file_url;
    }
}
