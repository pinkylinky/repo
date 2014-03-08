package com.tm.utils.datatypes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	
	public static final String DATE_FORMAT_RU_SHORT = "dd.MM.yyyy";
	
	public static final String DATE_FORMAT_EN_SHORT = "MM/dd/yyyy";
	
	public static final String TIME_FORMAT_H_M_S = "hh:mi:ss";
	
	public static final String TIME_FORMAT_H_M = "hh:mi";
	
	public static final String TIME_FORMAT_M_S = "mi:ss";
	
	public static final String DATETIME_FORMAT_RU_SHORT_H_M_S = "dd.MM.yyyy hh:mi:ss";
	
	public static String defaultDateFormat = DATE_FORMAT_RU_SHORT;
	
	public static void setDefaultDateFormat(String format) {
		defaultDateFormat = format;
	}
	
	public static String getDefaultDateFormat() {
		return defaultDateFormat;
	}
	
	/*
	 * Date <-> String
	 */
	
	public static String dateToString(Date date) {
		return dateToString(date, defaultDateFormat);
	}
	
	public static String dateToString(Date date, String format) {
		DateFormat df = getDateFormat(format);
		return df.format(date);
	}
	
	public static Date stringToDate(String str) throws ParseException {
		return stringToDate(str, defaultDateFormat);
	}
	
	public static Date parseDate(String str) throws ParseException {
		return stringToDate(str);
	}
	
	public static Date stringToDate(String str, String format) throws ParseException {
		DateFormat df = getDateFormat(format);
		return df.parse(str);
	}
	
	/*
	 * Date rolling
	 */
	
	public static int getYear(Date date) {
		Calendar c = dateAsCalendar(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * @param date
	 * @return month number (1-12)
	 */
	public static int getMonth(Date date) {
		Calendar c = dateAsCalendar(date);
		return c.get(Calendar.MONTH) + 1;
	}
	
	public static int getDayOfMonth(Date date) {
		Calendar c = dateAsCalendar(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Calendar dateAsCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	private static final Map<String, DateFormat> dateFormatCache = new HashMap<String, DateFormat>();
	
	private static DateFormat getDateFormat(String format) {
		DateFormat df = dateFormatCache.get(format);
		if (df == null) {
			df = new SimpleDateFormat(format);
			dateFormatCache.put(format, df);
		}
		return df;
	}
	
	public static int getMillis(int amount, TimeUnit timeUnit) {
		switch(timeUnit) {
			case MILLISECONDS : return amount;
			case SECONDS : return amount * 1000;
			case MINUTES : return amount * 1000 * 60 ;
			case HOURS   : return amount * 1000 * 60 * 60;
			default 	 : return amount;
		}
	}

}
