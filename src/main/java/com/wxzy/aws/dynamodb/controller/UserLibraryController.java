package com.wxzy.aws.dynamodb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import com.wxzy.aws.dynamodb.model.pojo.UserLibrary;
import com.wxzy.aws.dynamodb.service.UserLibraryService;
import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;

@RestController
@RequestMapping("/plugin/sport")
public class UserLibraryController {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    @Value("${aws.s3.base-url}")
    private String baseUrl;
    @Value("${aws.s3.expire}")
    private Integer expire;

    // @Autowired
    // private S3Helper s3Helper;

    @Autowired
    private UserLibraryService userLibraryService;

    @PostMapping("/picture")
    public ResultModel addScaleRecord(@RequestParam(value = "file", required = false) MultipartFile file)
        throws IOException {

        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String fileName = new StringBuffer(UUID.randomUUID().toString()).append(".").append(extension).toString();
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new GeneralException(ResultCode.UploadFailed);
        }

        final long size = (long)inputStream.available();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String subPath = String.format("%s/%s/%s", "sport", dateFormat.format(new Date()), fileName);
        final String s3Url =
            String.format("https://%s.%s/%s", this.bucketName, this.baseUrl, UriEncoder.encode(subPath));
        final String signedUrl = s3Url;

        UserLibrary userLibrary = new UserLibrary();
        userLibrary.setPicId(UUID.randomUUID().toString());
        userLibrary.setVersion("20133439");
        userLibrary.setName("yaojia");
        userLibrary.setCategoryId("20133439");
        userLibrary.setCategoryName("heilongjiang");
        userLibrary.setSize(size);
        userLibrary.setRecommend(false);
        userLibrary.setFileUrl(s3Url);
        userLibrary.setIconUrl(signedUrl);
        userLibraryService.save(userLibrary);
        return ResultUtil.success();
    }

    @PostMapping("/pic")
    public ResultModel getPicture(@RequestParam(value = "pic", required = false) final String pic) {
        UserLibrary pictureLib = userLibraryService.getPictureLib(pic, "20133439");
        List<UserLibrary> pictureLibByVersion = userLibraryService.getPictureLibByVersion("20133439");
        return ResultUtil.success(pictureLibByVersion);
    }
}
