package com.wxzy.aws.dynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.wxzy.aws.dynamodb.service.WeatherService;
import com.wyze.common.response.ResultModel;

@RestController
@RequestMapping("/plugin/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public DeferredResult<ResultModel> weather() {

        DeferredResult<ResultModel> result = new DeferredResult<ResultModel>();
        weatherService.hello2(result);
        return result;
    }

}
