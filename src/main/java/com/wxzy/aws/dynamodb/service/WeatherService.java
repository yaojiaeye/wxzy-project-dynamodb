package com.wxzy.aws.dynamodb.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.wxzy.aws.dynamodb.model.output.ForecastDailyWeatherCity;
import com.wxzy.aws.dynamodb.model.output.WeatherResponse;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

@Service
public class WeatherService {

    @Autowired
    private WebClient webClient;

    public void hello(DeferredResult<ResultModel> result) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "4669511");
        map.put("appid", "bb742095a1fd306068e77bdd698a9b1d");
        webClient.get().uri("http://api.openweathermap.org/data/2.5/weather/{id}/{appid}").retrieve()
            .bodyToMono(String.class).doOnError(e -> {
            }).subscribe(e -> {
                result.setResult(ResultUtil.success(e));
            });
    }

    public void hello1(DeferredResult<ResultModel> result) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("id", "4669511");
        builder.part("appid", "bb742095a1fd306068e77bdd698a9b1d");
        builder.part("cnt", "16");

        Map<String, String> map = new HashMap<>();
        map.put("id", "4669511");
        map.put("appid", "bb742095a1fd306068e77bdd698a9b1d");

        MultiValueMap<String, String> resultmap = new LinkedMultiValueMap<String, String>(10);
        resultmap.add("id", "4669511");
        resultmap.add("appid", "bb742095a1fd306068e77bdd698a9b1d");
        resultmap.add("cnt", "16");

        MultiValueMap<String, HttpEntity<?>> parts = builder.build();
        webClient.post().uri("http://api.openweathermap.org/data/2.5/forecast/daily")
            .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromFormData(resultmap)).retrieve()
            .bodyToMono(String.class).doOnError(e -> {

            }).subscribe(e -> {
                result.setResult(ResultUtil.success(e));
            });
    }

    public void hello2(DeferredResult<ResultModel> result) {
        String uri = "http://api.openweathermap.org/data/2.5/forecast/daily";
        Map<String, String> params = new HashMap<>();
        params.put("id", "4669511");
        params.put("appid", "bb742095a1fd306068e77bdd698a9b1d");
        params.put("cnt", "16");
        uri = uri + UriComponentsBuilder.newInstance().queryParams(params.entrySet().stream()
            .filter(e -> !StringUtils.isEmpty(e.getValue())).collect(Collectors.toMap(Map.Entry::getKey,
                e -> Collections.singletonList(e.getValue()), (key, value) -> value, LinkedMultiValueMap::new)))
            .build().toString();
        WeatherResponse response = new ForecastDailyWeatherCity();
        webClient.get().uri(uri).retrieve().bodyToMono(response.getClass()).doOnError(e -> {
            System.err.println(e);
        }).subscribe(e -> {
            result.setResult(ResultUtil.success(e));
        });
    }
}
