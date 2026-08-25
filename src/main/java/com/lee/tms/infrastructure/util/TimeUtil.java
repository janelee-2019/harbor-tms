package com.lee.tms.infrastructure.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public final class TimeUtil
{
    public static String getReadableDuration(Duration duration)
    {
        if (duration.toDays() > 0)
        {
            return duration.toDays() + "天";
        }
        else if (duration.toHours() > 0)
        {
            return duration.toHours() + "小时";
        }
        else
        {
            return "不到1小时";
        }
    }

    // 2021-11-26 lee
    public static LocalDateTime getLocalDateTime(String shortDate)
    {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(shortDate, DATEFORMATTER);
        LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.MIN.toLocalTime());
        return ldt;
    }

    // 2021-12-23 lee
    public static String getFirstDayOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    // 2021-12-23 lee
    public static String getLastDayOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
}
