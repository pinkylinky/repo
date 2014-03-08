package com.tm.utils.datatypes;

public class BooleanUtils {
	
	/*
	 * Boolean <-> String
	 */
	
	public static String booleanToString(boolean val) {
		return String.valueOf(val);
	}
	
	public static boolean stringToBoolean(String str) {
		return Boolean.getBoolean(str);
	}

}
