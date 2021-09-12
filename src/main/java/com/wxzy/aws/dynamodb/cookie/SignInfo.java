package com.wxzy.aws.dynamodb.cookie;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class SignInfo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        final String secret = "098ff21c253241bf9ac9e2761782a318";
        final String method = "GET";
        final String path = "/open-product-service/api/official/pattern/list";

        final Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept","application/json");
//        headers.put("Content-MD5",contentMD5);
        headers.put("Content-Type","application/x-www-form-urlencoded");
//        headers.put("Date",new Date().toString());
//        headers.put("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIiwiUk9MRV9BUEkiLCJXRUIiXSwiZXhwIjoxNjMwNTcxNTQyLCJqdGkiOiIyNzkwMjhkMC0wMmQxLTQ0ZGItYTViYy01MjA5YTUyNjMwOGEiLCJjbGllbnRfaWQiOiI1YzA5MmE4MjAxNDc0YmIwIn0.Yh0UODHcnOKRoWarB5Gb8XfnqmIa0UWA_P5XSAzkyEI");
        headers.put("X-Ca-Key","5c092a8201474bb0");

        final Map<String, String> querys = new LinkedHashMap<>();
        querys.put("model","a1qBqtDFavA");

        final Map<String, String> bodys = null;

        final List<String> signHeaderPrefixList = new ArrayList<>();

        System.err.println(SignUtils.sign(secret,method,path,headers,querys,bodys,signHeaderPrefixList));

    }

    public static byte[] encryptMD5(byte[] str) {
        return DigestUtils.md5Hex(str).getBytes();
    }
}
