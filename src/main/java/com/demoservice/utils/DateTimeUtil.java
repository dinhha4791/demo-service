package com.demoservice.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String getCurrentDateTime(String format) {
        return formatDateTime(format, getTimeZone());
    }

    private static String formatDateTime(String format, ZonedDateTime inst) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        return fmt.format(inst);
    }

    private static ZonedDateTime getTimeZone() {
        return ZonedDateTime.now();
    }
}