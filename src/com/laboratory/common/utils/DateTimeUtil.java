package com.laboratory.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 12:41
 */
public class DateTimeUtil {
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeUtil(){
    }

    public static LocalDateTime parseStringToLocalDateTime(String dateStr){
        return LocalDateTime.parse(dateStr,dateTimeFormatter);
    }

    public static String parseLocalDateTimeToString(LocalDateTime localDateTime){
        return dateTimeFormatter.format(localDateTime);
    }

}
