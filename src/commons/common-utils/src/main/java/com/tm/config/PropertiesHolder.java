package com.tm.config;

import java.io.IOException;
import java.util.Properties;

import com.tm.utils.common.PropertiesUtils;

public class PropertiesHolder {
	
	private static Properties properties = new Properties();
	
	public static Properties getProperties() {
		return properties;
	}
	
	public static void load(String filePath) throws IOException {
		PropertiesUtils.load(properties, filePath);
	}
	
	public static String getStringProperty(String name) {
		return properties.getProperty(name);
	}
	
	public static Integer getIntProperty(String name) {
		return Integer.parseInt(properties.getProperty(name));
	}
	
	public static Long getLongProperty(String name) {
		return Long.parseLong(properties.getProperty(name));
	}
	
	public static Boolean getBooleanProperty(String name) {
		return Boolean.parseBoolean(properties.getProperty(name));
	}

}
