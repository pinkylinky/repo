package com.tm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class PropertiesUtils {
	
	public static Properties load(String fileName) throws IOException {
		Properties prop = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream(fileName);
			prop.load(is);
		} finally {
			if (is != null)
				is.close();
		}
		return prop;
	}
	
	public static Properties load(Properties prop, String fileName) throws IOException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(fileName);
			prop.load(is);
		} finally {
			if (is != null)
				is.close();
		}
		return prop;
	}
	
	public static String getStringProperty(Properties properties, String name) {
		return properties.getProperty(name);
	}
	
	public static Integer getIntegerProperty(Properties properties, String name) {
		return Integer.parseInt(properties.getProperty(name));
	}
	
	public static boolean isIntegerProperty(Properties properties, String name) {
		try {
			Integer.parseInt(properties.getProperty(name));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Long getLongProperty(Properties properties, String name) {
		return Long.parseLong(properties.getProperty(name));
	}
	
	public static Date getDateProperty(Properties properties, String name) throws ParseException {
		return DateUtils.parseDate(properties.getProperty(name));
	}
	
	public static Boolean getBooleanProperty(Properties properties, String name) {
		return Boolean.parseBoolean(properties.getProperty(name));
	}
	
	public static boolean isBooleanProperty(Properties properties, String name) {
		return "true".equalsIgnoreCase(properties.getProperty(name)) 
				|| "false".equalsIgnoreCase(properties.getProperty(name));
	}

}
