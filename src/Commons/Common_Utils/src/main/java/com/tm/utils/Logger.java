package com.tm.utils;

public class Logger {
	
	private org.apache.log4j.Logger logger;
	
	private Logger(Class clazz) {
		logger = org.apache.log4j.Logger.getLogger(clazz.getName());
	}
	
	public static Logger getLogger(Class clazz) {
		return new Logger(clazz);
	}
	
	public void error(String message, String... args) {
		logger.error(String.format(message, args));
	}
	
	public void error(String message, Throwable t, String... args) {
		logger.error(String.format(message, args), t);
	}
	
	public void error(Throwable t) {
		logger.error(t.getMessage(), t);
	}
	
	public void info(String message, String... args) {
		logger.info(String.format(message, args));
	}
	
	public void debug(String message, String... args) {
		logger.debug(String.format(message, args));
	}

}
