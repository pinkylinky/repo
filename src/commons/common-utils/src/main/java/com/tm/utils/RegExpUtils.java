package com.tm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtils {
	
	public static boolean matches(String pattern, String str) {
		return Pattern.matches(pattern, str);	
	}
	
	public static List<String> getTokens(String pattern, String str) {
		Pattern p = getPattern(pattern);
		Matcher m = p.matcher(str);
		List<String> tokens = new ArrayList<String>();
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
	
	private static final Map<String, Pattern> patternMap = new HashMap<String, Pattern>();
	private static final boolean patternHash = true;
	
	private static Pattern getPattern(String pattern) {
		Pattern p = null;
		if (patternHash) {
			p = patternMap.get(pattern);
			if (p == null) {
				p = Pattern.compile(pattern);
				patternMap.put(pattern, p);
			}
		} else {
			p = Pattern.compile(pattern);
		}
		return p;
	}
	

}
