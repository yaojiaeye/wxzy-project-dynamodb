package com.wxzy.aws.dynamodb.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.*;

public class OkHttpUtil {

    private static final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

    private static final OkHttpClient client = new OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();

    private static final OkHttpClient clientShort = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 不会开启异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        client.newCall(request).enqueue(responseCallback);
    }

    /**
     * 根据url地址获取数据
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String doGetHttpRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {

            throw new IOException("Unexpected code " + response);
        }
        return response.body().string();
    }

    /**
     * 根据url和参数构造get请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doGetHttpRequest(String url, Map<String, String> params) throws IOException {
        Request request = new Request.Builder().url(url).build();
        HttpUrl.Builder builder = request.url().newBuilder();
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpUrl httpUrl = builder.build();
        Request request1 = new Request.Builder().url(httpUrl).build();
        Response response = client.newCall(request1).execute();
        if (!response.isSuccessful()) {

            throw new IOException("Unexpected code " + response);
        }
        return response.body().string();
    }

    /**
     * 根据url地址和json数据获取数据(http)
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String doPostHttpRequest(String url, String json) throws IOException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(JSON, json)).build();

        Response response = client.newCall(request).execute();
        log.info("请求第三方响应体:{}", response.body());
        if (!response.isSuccessful()) {

            throw new IOException("Unexpected code " + response);
        }
        String respStr = response.body().string();
        return respStr;
    }

    /**
     * 根据url地址和包含requestHeader的json数据获取数据(http)
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String doPostHttpRequest(String url, String json, Map<String, String> headerContent)
        throws IOException {
        Request request = new Request.Builder().url(url).headers(Headers.of(headerContent))
            .post(RequestBody.create(JSON, json)).build();

        Response response = client.newCall(request).execute();
        log.info("请求第三方响应体:{}", response.body());
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String respStr = response.body().string();
        return respStr;
    }

}
