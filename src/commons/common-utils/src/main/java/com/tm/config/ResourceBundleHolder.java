package com.tm.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.tm.utils.datatypes.StringUtils;

public class ResourceBundleHolder {
	
	public static final String LOCALE_RU = "ru";
	
	private static Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();
	
	public static ResourceBundle getBundle() {
		return getBundle(LOCALE_RU);
	}
	
	public static ResourceBundle getBundle(String locale) {
		ResourceBundle bundle = bundles.get(locale);
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("messages", new Locale(locale));
			bundles.put(locale, bundle);
		}
		return bundle;
	}
	
	public static String getMessage(String key) {
		return getBundle().getString(key);
	}
	
	public static String getMessage(String locale, String key) {
		return getBundle(locale).getString(key);
	}
	
	public static String getFormattedMessage(String key, Object... args) {
		String message = getBundle().getString(key);
		return StringUtils.formatMessage(message, args);
	}
	
	public static String getFormattedMessage(String locale, String key, Object... args) {
		String message = getBundle(locale).getString(key);
		return StringUtils.formatMessage(message, args);
	}

}
