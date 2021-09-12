package com.wxzy.aws.dynamodb.md5;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Signature {

    public static void main(String[] args) throws Exception {

        String stringToSign = "yaojiaiteye@163.com";

        String secret = "yaojia20133439";

        Mac hmacSha256 = Mac.getInstance("HmacSHA256");

        byte[] keyBytes = secret.getBytes("UTF-8");

        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA25 6"));

        String sign = new String(
            Base64.encodeBase64(hmacSha256.doFinal(stringToSign.getBytes("UTF-8")), Boolean.parseBoolean("UTF-8")));

        System.out.println(sign);
    }
}
