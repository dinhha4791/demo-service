package com.demoservice.constants;

public class RestConstants {
    // Exception
    public static final String DATETIME_PATTERN = "yyyyMMddHHmmss";
    public static final String SUCCESS_CODE = "00";
    public static final String SUCCESS_DESC = "Success";
    public static final String BUSINESS_EXCEPTION_CODE = "10";
    public static final String BUSINESS_EXCEPTION_DESC = "Business Exception";
    public static final String SYSTEM_ERROR_CODE = "99";
    public static final String SYSTEM_ERROR_DESC = "System Error";

    // api uri
    public static final String API = "/api";
    public static final String BOOKS = "/books";
    public static final String BOOKS_SEARCH_BY_AUTHOR = "/search-by-author";
    public static final String AUTHORS = "/authors";
    public static final String AUTHORS_SEARCH_BY_EMAIL_AND_PHONE_NUMBER = "/search-by-email-and-phone-number";
}
