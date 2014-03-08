package com.tm.utils.common;

public abstract class Logger {
	
	protected Logger(Class clazz) {
	}
	
	public static Logger getLogger(Class clazz) {
		return Log4jLogger.getLogger(clazz);
	}
	
	public abstract void error(String message, String... args);
	
	public abstract void error(String message, Throwable t, String... args);
	
	public abstract void error(Throwable t);
	
	public abstract void info(String message, String... args);
	
	public abstract void debug(String message, String... args);

}
