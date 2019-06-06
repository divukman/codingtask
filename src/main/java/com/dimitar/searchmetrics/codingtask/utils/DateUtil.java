package com.dimitar.searchmetrics.codingtask.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date setTime(final Date date, final int hours, final int minutes, final int seconds, final int millis) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, millis);
        Date newDate = calendar.getTime();
        return newDate;
    }
}
