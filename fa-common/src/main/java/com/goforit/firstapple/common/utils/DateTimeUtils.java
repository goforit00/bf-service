package com.goforit.firstapple.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by goforit on 18/2/12.
 */
public class DateTimeUtils {


    private static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_TIMESTAMP_COMPACT = "yyyyMMddHHmmss";
    private static final String FORMAT_TIMESTAMP_COMPACT2 = "yyMMddHHmmss";
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private static final String FORMAT_DATE_COMPACT = "yyyyMMdd";
    private static final String FORMAT_DATE_YM = "yyyyMM";
    private static final String FORMAT_DATE_MD = "MM/dd";
    private static final String FORMAT_DATE_MD_SHORT = "M/d";
    private static final String FORMAT_TIME = "HHmmss";
    private static final String FORMAT_TIMESTAMP_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_TIMESTAMP_DATE_FLOOR = "yyyy-MM-dd 00:00:00";

    private static final Long one_day = 24 * 60 * 60L;

    private static ThreadLocal<DateFormat> timestampFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIMESTAMP);
        }
    };

    private static ThreadLocal<DateFormat> compactTimestampFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIMESTAMP_COMPACT);
        }
    };

    private static ThreadLocal<DateFormat> compactTimestampFormat2 = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIMESTAMP_COMPACT2);
        }
    };

    private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_DATE);
        }
    };

    private static ThreadLocal<DateFormat> compactDateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_DATE_COMPACT);
        }
    };

    private static ThreadLocal<DateFormat> dateYmFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_DATE_YM);
        }
    };

    private static ThreadLocal<DateFormat> dateMdFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_DATE_MD);
        }
    };

    private static ThreadLocal<DateFormat> dateMdFormatShort = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_DATE_MD_SHORT);
        }
    };

    private static ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIME);
        }
    };

    private static ThreadLocal<DateFormat> timestampHourMinuteFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIMESTAMP_HOUR_MINUTE);
        }
    };

    private static ThreadLocal<DateFormat> timestampDateFloorFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_TIMESTAMP_DATE_FLOOR);
        }
    };

    public static String toTimestampString(Date date) {
        return timestampFormat.get().format(date);
    }

    public static String toCompactTimestampString(Date date) {
        return compactTimestampFormat.get().format(date);
    }

    public static String toCompactTimestampString2(Date date) {
        return compactTimestampFormat2.get().format(date);
    }

    public static String toDateString(Date date) {
        return dateFormat.get().format(date);
    }

    public static String toCompactDateString(Date date) {
        return compactDateFormat.get().format(date);
    }

    public static String toYmDateString(Date date) {
        return dateYmFormat.get().format(date);
    }

    public static String toMdDateString(Date date) {
        return dateMdFormat.get().format(date);
    }

    public static String toShortMdDateString(Date date) {
        return dateMdFormatShort.get().format(date);
    }

    public static String toTimeString(Date date) {
        return timeFormat.get().format(date);
    }

    public static String toTimestampHourMinuteString(Date date) {
        return timestampHourMinuteFormat.get().format(date);
    }

    public static String toTimestampDateFloorString(Date date) {
        return timestampDateFloorFormat.get().format(date);
    }

    public static Date toTimestampDateFloor(String s) {
        try {
            return timestampDateFloorFormat.get().parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toTimestampHourMinute(String s) {
        try {
            return timestampHourMinuteFormat.get().parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toTimestamp(String s) {
        try {
            return timestampFormat.get().parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toDate(String s) {
        try {
            return dateFormat.get().parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDatePart(String dateText) {

        if (org.apache.commons.lang3.StringUtils.isBlank(dateText)) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        if (dateText.length() > 10) {
            return dateText.substring(0, 10);
        }
        return dateText;
    }

    public static Date getLastWeekday(Date pivot, int weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pivot);
        cal.add(Calendar.DATE, -1);
        while (cal.get(Calendar.DAY_OF_WEEK) != weekday) {
            cal.add(Calendar.DATE, -1);
        }
        return cal.getTime();
    }

    public static Date getNextWeekday(Date pivot, int weekday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pivot);
        cal.add(Calendar.DATE, 1);
        while (cal.get(Calendar.DAY_OF_WEEK) != weekday) {
            cal.add(Calendar.DATE, 1);
        }
        return cal.getTime();
    }

    public static Date getOneDayBefore() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }

    public static Long getOneDayBeforeTimeStamp() {
        Long now = getNowTimestamp();

        return now - one_day;
    }

    /**
     * 获取时分秒为0的日期
     * @param date
     * @param day
     * @return
     */
    public static Date getDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 获取以小时为维度的完整时间格式
     * @param date
     * @param hour 推移小时数
     * @return
     */
    public static Date getHourOfDayDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间戳 -> date (yyyy-MM-dd HH:mm:ss)
     * @param s
     * @return
     */
    public static Date timeStampToDate(String s) {
        String res = timeStampToDateStr(s);
        return toTimestamp(res);
    }
    public static String timeStampToDateStr(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_TIMESTAMP);
        long lt = new Long(s);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取时间戳(精确到秒)
     */
    public static Long toUnixTimestamp(Date date) {
        if(date!=null){
            return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
        }
        return null;
    }

    /**
     * 将定时任务中的分钟参数转换为日期
     */
    public static Date getIntervalMinutesTime(int minutes) {
        return DateUtils.addMinutes(new Date(), minutes);
    }

    /**
     * 获取当前时间戳(精确到秒)
     */
    public static Long getUnixTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当天0点时间戳(精确到秒)
     */
    public static Long getTodayZeroUnixTimestamp() {
        return getZeroUnixTimestamp(0);
    }

    /**
     * 获取昨天0点时间戳(精确到秒)
     */
    public static Long getYesterdayZeroUnixTimestamp() {
        return getZeroUnixTimestamp(-1);
    }

    /**
     * 将时间戳(秒)转换成日期
     */
    public static Date toDate(long second) {
        return new Date(second * 1000);
    }

    /**
     * 获取某天0点时间戳(精确到秒)
     */
    public static Long getZeroUnixTimestamp(int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTimeInMillis() / 1000;
    }

    public static Long getUnixTimestamp(Date day) {
        if (day == null){
            return null;
        }
        return day.getTime() / 1000;
    }

    /**
     * 获取 timestamp 相差 day天的 时间戳
     *
     * @param second
     *            秒
     * @param day
     *            天
     * @return 时间戳 (秒)
     */
    public static Long getZeroUnixTimestamp(long second, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(second * 1000);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 获取前1天0点时间戳(精确到秒)
     */
    public static Long getOneDayBeforeZeroUnixTimestamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 获取前1天0点时间戳(精确到秒)
     */
    public static Long getOneDayBeforeZeroUnixTimestamp(long second) {
        return getZeroUnixTimestamp(second, -1);
    }

    /*
     * 获取当前日期时间
     */
    public static String getNowDate() {
        return toDateString(new Date());
    }

    /**
     * 当前时间(时间戳,秒级别)
     *
     * @return
     */
    public static Long getNowTimestamp() {
        return System.currentTimeMillis() / 1000;
    }


    public static Double getDistanceHours(Date end, Date start) {
        try {
            if(null == end || null == start) {
                return null;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            long eMillis = cal.getTimeInMillis();
            cal.setTime(start);
            long sMillis = cal.getTimeInMillis();

            double hours = (eMillis - sMillis)/1000/60/60.0;
            DecimalFormat df = new DecimalFormat("##.#");
            String hourDiff = df.format(hours);

            return Double.parseDouble(hourDiff);
        } catch (Exception e) {
            return null;
        }
    }
}
