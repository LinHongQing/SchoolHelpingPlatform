package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cache.Configurations;
import exception.IllegalEmptyStringException;

public final class TimeUtil {
	public static final int get_weekday = 0;
	public static final int get_year = 1;
	public static final int get_month = 2;
	public static final int get_day = 3;
	public static final int get_hour = 4;
	public static final int get_minute = 5;
	public static final int get_second = 6;
	
	public static final int SUNDAY = Calendar.SUNDAY;
	public static final int MONDAY = Calendar.MONDAY;
	public static final int TUESDAY = Calendar.TUESDAY;
	public static final int WEDNESDAY = Calendar.WEDNESDAY;
	public static final int THURSDAY = Calendar.THURSDAY;
	public static final int FRIDAY = Calendar.FRIDAY;
	public static final int SATURDAY = Calendar.SATURDAY;
	
	private static final String defaultFormatString = "yyyy-MM-dd HH:mm";
	/**
	 * 取得当前时间戳
	 *
	 * @return nowTimeStamp 当前时间戳(单位:s)
	 */
	public static String getNowTimeStamp() {
		long time = System.currentTimeMillis();
		String nowTimeStamp = String.valueOf(time / 1000);
		return nowTimeStamp;
	}
	
	/**
	 * 取得当前时间对应的一些日期信息
	 * @param getCode 需要获取的信息类型
	 * @return 日期信息
	 */
	public static int getNowTimeValue(int getCode) {
		Calendar cal = Calendar.getInstance();
		switch (getCode) {
		case get_weekday:
			return cal.get(Calendar.DAY_OF_WEEK);
		case get_year:
			return cal.get(Calendar.YEAR);
		case get_month:
			return cal.get(Calendar.MONTH);
		case get_day:
			return cal.get(Calendar.DATE);
		case get_hour:
			return cal.get(Calendar.HOUR_OF_DAY);
		case get_minute:
			return cal.get(Calendar.MINUTE);
		case get_second:
			return cal.get(Calendar.SECOND);
		default:
			return Configurations.invalid_int;
		}
	}

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期;
     * @param format 如：yyyy-MM-dd HH:mm:ss;
     *
     * @return 返回结果;
     * @throws IllegalEmptyStringException 日期字符串为空异常;
     * @throws ParseException 日期字符串转换异常
     */
    public static String Date2TimeStamp(String dateStr, String format) throws IllegalEmptyStringException, ParseException {
    	if (dateStr == null || "".equals(dateStr))
    		throw new IllegalEmptyStringException("日期字符串非法!");
    	if (format == null || "".equals(format))
    		throw new IllegalEmptyStringException("日期格式字符串非法!");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
    }
    
    /**
     * 将默认日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期(默认格式 yyyy-MM-dd HH:mm);
     *
     * @return 返回结果;
     * @throws IllegalEmptyStringException 日期字符串为空异常;
     * @throws ParseException 日期字符串转换异常
     */
    public static String Date2TimeStamp(String dateStr) throws IllegalEmptyStringException, ParseException {
    	if (dateStr == null || "".equals(dateStr))
    		throw new IllegalEmptyStringException("日期字符串非法!");
    	return Date2TimeStamp(dateStr, defaultFormatString);
    }
    
    /**
     * 将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式;
     *
     * @return 返回结果;
     * @throws IllegalEmptyStringException 日期字符串为空异常;
     * @throws NumberFormatException 时间戳字符串转换异常
     */
    public static String TimeStamp2Date(String timestampString, String formats) throws IllegalEmptyStringException, NumberFormatException {
    	if (timestampString == null || "".equals(timestampString))
    		throw new IllegalEmptyStringException("时间戳字符串非法!");
    	if (formats == null || "".equals(formats))
    		throw new IllegalEmptyStringException("日期格式字符串非法!");
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
    
    /**
     * 将Unix时间戳转换成默认格式日期字符串(yyyy-MM-dd HH:mm)
     * @param timestampString 时间戳 如："1473048265";
     * 
     *
     * @return 返回结果 如:"2016-09-05 16:06";
     * @throws IllegalEmptyStringException 日期字符串为空异常;
     * @throws NumberFormatException 时间戳字符串转换异常
     */
    public static String TimeStamp2Date(String timestampString) throws IllegalEmptyStringException, NumberFormatException {
        if (timestampString == null || "".equals(timestampString))
            throw new IllegalEmptyStringException("日期格式字符串非法!");
        return TimeStamp2Date(timestampString, defaultFormatString);
    }
}
