/*
 * Tencent is pleased to support the open source community by making Tars available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.wxzy.aws.dynamodb.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTime {

    public static String yestoday() {
        return LocalDateTime.now().plus(-1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String tomorrow() {
        return LocalDateTime.now().plus(1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String today() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static long formatDateToLong(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()
            .toEpochMilli();
    }

    public static long dateAddDay(final String dateStr, final int days) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(days)
            .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String formatDate(final String date, final int days) {
        final String dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .plus(days, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dateTime;
    }

    public static void main(String[] args) {

        System.err.println(DateTime.formatDate("2021-08-14", 1));
    }
}
