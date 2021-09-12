package com.wxzy.aws.dynamodb.util;

import java.io.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
public class WebUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 下载文件
     */
    public static void downloadFile(HttpServletResponse response, String filePath) {
        try {
            String originalFileName = FilenameUtils.getName(filePath);
            String downloadedFileName = new String(originalFileName.getBytes("GBK"), "ISO8859_1");

            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + downloadedFileName + "\"");

            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            StreamUtil.copyStream(inputStream, outputStream);
        } catch (Exception e) {
            logger.error("下载文件出错！", e);
            throw new RuntimeException(e);
        }
    }
}
