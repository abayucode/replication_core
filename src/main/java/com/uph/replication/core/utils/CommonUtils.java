package com.uph.replication.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommonUtils {

    public static String getTimestamp(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        df.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return df.format(date);
    }
}
