/*
 * Copyright(c) 2019-2020 Wyze Labs, All Rights Reserved. This file is created on 2020/12/21 by Alan Yin.
 */

package com.wxzy.aws.dynamodb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

import com.wyze.common.exception.GeneralException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DateUtilX {

    /** The ms of one hour */
    public static final int HOUR = 3600 * 1000;
    /** The ms of one day */
    public static final int DAY = HOUR * 24;
    public static final int SECONDS = 60;
    public static final int SECONDS_IN_A_HOUR = 3600;
    public static final int MINUTES = 60;
    public static final int MICRO_SECONDS = 1000;
    public static final int DATE_STR_LENGTH = 8;
    public static final int DATE_STR_FIRST_SPLIT_POSITION = 4;
    public static final int DATE_STR_FIRST_SECOND_POSITION = 7;

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DIY_PATTERN_DATE_TIME = "yyyyMMddHHmmss";
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(PATTERN_DATE);
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat(PATTERN_TIME);
    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat(PATTERN_DATE_TIME);

    private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    private static final String TIME_FORMAT = "HH:mm";

    private DateUtilX() {

    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Date getDate(final SimpleDateFormat simpleDateFormat, final String dateText) {
        try {
            final Date date = simpleDateFormat.parse(dateText);
            return date;
        } catch (final ParseException parseException) {
            log.error("date error ", parseException);
            parseException.printStackTrace();
            throw new GeneralException(6001, "date parse error");
        }
    }

    /**
     * Formats a date into date-time string.
     * 
     * @param simpleDateFormat
     * @param date
     * @return
     */
    public static String format(final SimpleDateFormat simpleDateFormat, final Date date) {
        return simpleDateFormat.format(date);
    }

    /**
     * Formats the date of now into date-time string.
     * 
     * @param simpleDateFormat
     * @return
     */
    public static String format(final SimpleDateFormat simpleDateFormat) {
        final Date date = Calendar.getInstance().getTime();
        return simpleDateFormat.format(date);
    }

    public static boolean checkDate(final SimpleDateFormat simpleDateFormat, final String dateText) {
        try {
            simpleDateFormat.parse(dateText);
            return true;
        } catch (final ParseException parseException) {
            return false;
        }
    }

    /**
     * Get hour & minute & second
     * 
     * @param time
     *            such as HH:mm:ss
     * @return
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static int[] getHHmmss(final String time) {
        final String[] splits = time.split(":");
        final int[] hms = new int[3];
        hms[0] = Integer.valueOf(splits[0]);
        hms[1] = Integer.valueOf(splits[1]);
        hms[2] = Integer.valueOf(splits[2]);
        return hms;
    }

    /**
     * Convert HHmmss to seconds
     * 
     * @param time
     * @return
     */
    public static int convertToSeconds(final String time) {
        final int[] hhmmss = getHHmmss(time);
        final int seconds = (hhmmss[0] * MINUTES + hhmmss[1]) * SECONDS + hhmmss[2];
        return seconds;
    }

    /**
     * Get raw offset by seconds Example: if timezone is UTC+8, then return 8 * 3600 seconds.
     * 
     * @param timezone
     * @return
     */
    public static int getRawOffsetBySeconds(final String timezone) {
        final TimeZone timeZone = TimeZone.getTimeZone(timezone);
        return timeZone.getRawOffset() / MICRO_SECONDS;
    }

    /**
     * Convert yyyyMMddHHmmss to millisecond
     * 
     * @param dateTimeStr
     * @param timeZone
     * @return
     */
    public static long convertToillisecond(final String dateTimeStr, final String timeZone) {
        final LocalDateTime localDateTime =
            LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME));
        return localDateTime.atZone(ZoneId.of(timeZone)).toInstant().toEpochMilli();
    }

    /**
     * 将yyyyMMddHHmmss格式的时间字符串转为 yyyy-MM-dd 格式的日期字符串
     * 
     * @param dateTimeStr
     * @return
     */
    public static String getDateStr(final String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr) || dateTimeStr.length() < DATE_STR_LENGTH) {
            return null;
        }
        // 只截取yyyyMMdd 部分
        final StringBuilder stringBuilder = new StringBuilder(dateTimeStr.substring(0, 8));
        stringBuilder.insert(DATE_STR_FIRST_SPLIT_POSITION, "-");
        stringBuilder.insert(DATE_STR_FIRST_SECOND_POSITION, "-");
        return stringBuilder.toString();
    }

    /**
     * 为日期加上指定天数
     * 
     * @param dateStr
     *            yyyy-MM-dd
     * @param days
     * @param timeZone
     *            时区
     * @return 返回增加天数后的日期的long值
     */
    public static long dateAddDay(final String dateStr, final int days, final String timeZone) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(days)
            .atStartOfDay(ZoneId.of(timeZone)).toInstant().toEpochMilli();
    }

    /**
     * 日期字符串 yyyy-MM-dd 转为 long 型
     * 
     * @param dateStr
     *            yyyy-MM-dd
     * @return long
     */
    public static long dateStrToLong(final String dateStr, final String timeZone) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay(ZoneId.of(timeZone)).toInstant()
            .toEpochMilli();
    }

    public static String hour(final String date) {
        final String hour = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME))
            .plus(0, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return hour;
    }

    public static String hour(final int h, final String date) {
        final String hour = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME))
            .plus(h - 1, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return hour;
    }

    public static long timeStamp(final String date) {
        final LocalDateTime localDateTime =
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME));
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String formatDate(final String date) {
        final String dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DIY_PATTERN_DATE_TIME))
            .plus(0, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern(PATTERN_DATE));
        return dateTime;
    }

    public static String formatDate(final String date, final int days) {
        final String dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE))
            .plus(days, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern(PATTERN_DATE));
        return dateTime;
    }

    public static long formatDateToLong(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()
            .toEpochMilli();
    }

    public static long formatDateToLong(final String date, final int days) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(days)
            .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long yesterdayDate(final String date, final int days) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(days)
            .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static void main(String[] args) {
        final long startTime = DateUtilX.formatDateToLong("2021-08-01");
        final long endTime = DateUtilX.formatDateToLong("2021-08-01", 6);
        System.out.println(startTime + "cccccc");
        System.out.println(endTime + "bbbbbbb");
    }
}
