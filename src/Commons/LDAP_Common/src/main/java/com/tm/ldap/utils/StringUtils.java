package com.tm.ldap.utils;

public class StringUtils {
	
	public static boolean isBlank(String str) {
		return str == null || str.isEmpty();
	}
	
	public static boolean isNotBlank(String str) {
		return str != null && !str.isEmpty();
	}

}
