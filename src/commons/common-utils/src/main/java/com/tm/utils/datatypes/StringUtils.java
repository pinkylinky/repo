package com.tm.utils.datatypes;

import java.util.List;

public class StringUtils {
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static String getNonEmptyString(String str) {
		return str == null ? "" : str;
	}
	
	public static String listToString(List<?> list, boolean splitLines) {
		
		StringBuilder sb = new StringBuilder();
		
		for (Object obj : list) {
			sb.append(obj);
			if (splitLines)
				sb.append("\n");
		}
		
		if (splitLines) {
			int length = sb.length();
			if (length > 1)
				sb.delete(length - 1, length);
		}
		return sb.toString();
	}
	
	public static String formatMessage(String message, Object... args) {
		String formatted = message.replaceAll("\\{[0-9]+\\}", "%s");
		return String.format(formatted, args);
	}
	
	public static String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html
				.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

}
