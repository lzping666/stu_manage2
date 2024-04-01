package com.liao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzp
 * @version 1.0
 * lzpnb!
 */
public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 将字符串格式的日期转换为Date类型
     *
     * @param dateString 字符串格式的日期
     * @return Date对象
     * @throws ParseException 如果解析失败
     */
    public static Date parseStringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return dateFormat.parse(dateString);
    }


    /**
     * 将 Date 对象转换为特定格式的字符串
     *
     * @param date 要格式化的 Date 对象
     * @return 格式化后的日期时间字符串
     */
    public static String formatDate(Date date) {
        return formatDate1(date, DEFAULT_DATE_FORMAT);
    }

    public static String formatDate1(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


}
