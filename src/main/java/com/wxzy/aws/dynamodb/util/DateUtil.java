package com.wxzy.aws.dynamodb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DIY_PATTERN_DATE_TIME = "yyyyMMddHHmmss";

    private static final String datetime_Format = "yyyyMMddHHmmss";

    /**
     * 解析日期与时间
     */
    public static Date parseDatetime(String str) {
        Date date = null;
        try {
            date = datetimeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd HH:mm:ss", e);
        }
        return date;
    }

    public static long timeStamp(final String date) {
        final LocalDateTime localDateTime =
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME));
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static long timeStamps(final String date) {
        final LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static String formatDate(final String date) {
        final String time = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .plus(0, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return time;
    }

    public static void main(String[] args) {

        System.out.println(timeStamp("20210630050600"));
        System.out.println(timeStamps("2021-06-30"));

    }
}
