package com.demoservice.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class StringFormatUtil {
    private static final String QUERY_LIKE_PATTERN = "%{0}%";

    public static String convertToLikeQueryFormat(String expression) {
        return MessageFormat.format(QUERY_LIKE_PATTERN, StringUtils.lowerCase(expression));
    }
}
