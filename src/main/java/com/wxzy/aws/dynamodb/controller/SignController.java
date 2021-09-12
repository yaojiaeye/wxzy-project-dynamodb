package com.wxzy.aws.dynamodb.controller;


import com.wxzy.aws.dynamodb.watch.vo.A;
import com.wyze.common.request.BodyReaderHttpServletRequestWrapper;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import com.wyze.common.util.HMacMD5Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/plugin/sign")
public class SignController {



    @SneakyThrows
    @PostMapping("/sign")
    public ResultModel userSign(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, InvalidKeyException {


        return ResultUtil.success(new A());
    }

}
