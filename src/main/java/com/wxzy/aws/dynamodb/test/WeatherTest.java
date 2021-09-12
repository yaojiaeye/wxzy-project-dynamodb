package com.wxzy.aws.dynamodb.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wxzy.aws.dynamodb.util.OkHttpUtil;

public class WeatherTest {

    public static void main(String[] args) throws IOException {

        String url = "http://api.openweathermap.org/data/2.5/forecast/daily";
        String appid = "bb742095a1fd306068e77bdd698a9b1d";
        Integer id = 4669511;
        Map<String, String> map = new HashMap<>(10);
        map.put("appid", appid);
        map.put("id", id.toString());
        map.put("cnt", "16");

        String request = OkHttpUtil.doGetHttpRequest(url, map);
        System.out.println(request);
    }
}
