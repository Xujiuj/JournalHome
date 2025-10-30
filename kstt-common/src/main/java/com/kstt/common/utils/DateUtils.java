package com.kstt.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author kstt
 * @date 2025-01-01
 */
public class DateUtils {
    
    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    
    /**
     * 日期型字符串转化为日期格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 日期型字符串转化为日期格式
     */
    public static Date parseDate(String str, String... parsePatterns) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        
        SimpleDateFormat parser = new SimpleDateFormat();
        parser.setLenient(true);
        ParseException pe = null;
        
        for (int i = 0; i < parsePatterns.length; i++) {
            try {
                parser.applyPattern(parsePatterns[i]);
                return parser.parse(str);
            } catch (ParseException e) {
                pe = e;
            }
        }
        
        throw new ParseException("Unable to parse the date: " + str, -1);
    }
    
    /**
     * 获取当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }
    
    /**
     * 获取当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return dateTimeNow("HH:mm:ss");
    }
    
    /**
     * 获取当前日期时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
     * 获取当前日期时间字符串 格式（yyyyMMddHHmmss）
     */
    public static String getDateTimes() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }
    
    /**
     * 获取当前日期时间字符串
     */
    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }
    
    /**
     * 日期转字符串
     */
    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }
}