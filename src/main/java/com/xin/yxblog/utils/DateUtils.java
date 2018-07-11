package com.xin.yxblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getLocalTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
