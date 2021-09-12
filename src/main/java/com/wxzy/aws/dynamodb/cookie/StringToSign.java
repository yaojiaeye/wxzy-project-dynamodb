package com.wxzy.aws.dynamodb.cookie;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class StringToSign {

    public static void main(String[] args) throws Exception {

        // body
        String signBody = "model=a1qBqtDFavA";
        byte[] bodyStream = signBody.getBytes("UTF-8");
        String contentMD5 =  Base64.encodeBase64URLSafeString(encryptMD5(bodyStream));

        log.info("bodyStream",contentMD5);

        // 计算hears
        final Map<String, String> heads = new HashMap<>();
        heads.put("Authorization","098ff21c253241bf9ac9e2761782a318");
        heads.put("X-Ca-Key","5c092a8201474bb0");
        StringBuffer head = new StringBuffer();
        Iterator<Map.Entry<String, String>> it = heads.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            head.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        log.info("Headers",head);

        // 计算url
        String path = "https://devgateway.joyami.com/open-product-service/api/official/pattern/list";
        final Map<String, String> params = new HashMap<>();
        String url = currentRequestPath(path, params);
        log.info("Url",url);

        // 计算加密字符串
        final Map<String, Object> signMap = new HashMap<>();
        signMap.put("HTTPMethod","GET");
        signMap.put("Accept","text/html");
        signMap.put("Content-MD5",contentMD5);
        signMap.put("Content-Type","application/x-www-form-urlencoded");
        signMap.put("Date", new Date());
        signMap.put("Headers", head.toString());
        signMap.put("Url", url);
        StringBuilder stringTosign = new StringBuilder();
        stringTosign.append(signMap.get("HTTPMethod")).append("\n")
                    .append(signMap.get("Accept")).append("\n")
                    .append(signMap.get("Content-MD5")).append("\n")
                    .append(signMap.get("Content-Type")).append("\n")
                    .append(signMap.get("Date")).append("\n")
                    .append(signMap.get("Headers")).append("\n")
                    .append(signMap.get("Url"));

        log.info("stringTosign",stringTosign);
        // 计算签名
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        String secret= "098ff21c253241bf9ac9e2761782a318";
        byte[] keyBytes = secret.getBytes("UTF-8");
        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

        String sign = new String(Base64.encodeBase64(hmacSha256.doFinal(stringTosign.toString().getBytes("UTF-8")), Boolean.parseBoolean("UTF-8")));
        System.err.println("sign"+sign);
    }


    /**
     * 从输入流中获取字符串
     */
    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error("Stream 转 String 出错！", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 将字符串 MD5 加密
     * @return
     */
    public static byte[] encryptMD5(byte[] str) {
        return DigestUtils.md5Hex(str).getBytes();
    }

    protected static final String currentRequestPath(final String path, final Map<String, String> params) {
        final String uri = path + UriComponentsBuilder.newInstance().queryParams(params.entrySet().stream()
                .filter(e -> !StringUtils.isEmpty(e.getValue())).collect(Collectors.toMap(Map.Entry::getKey,
                        e -> Collections.singletonList(e.getValue()), (key, value) -> value, LinkedMultiValueMap::new)))
                .build().toString();
        return uri;
    }

}
