package com.wxzy.aws.dynamodb.cookie;


import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Generate {
    public static void main(String[] args) throws UnsupportedEncodingException {

        final String secret = "098ff21c253241bf9ac9e2761782a318";
        final String method = "POST";
        final String path = "/open-product-service/api/official/function/generate";
        final Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept","application/json");
//        headers.put("Content-MD5",contentMD5);
        headers.put("Content-Type","application/json");
//        headers.put("Authorization","098ff21c253241bf9ac9e2761782a318");
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
