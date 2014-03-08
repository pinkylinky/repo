package com.tm.utils.common;

public class Log4jLogger extends Logger {
	
private org.apache.log4j.Logger logger;
	
	protected Log4jLogger(Class clazz) {
		super(clazz);
		logger = org.apache.log4j.Logger.getLogger(clazz.getName());
	}
	
	public static Log4jLogger getLogger(Class clazz) {
		return new Log4jLogger(clazz);
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
