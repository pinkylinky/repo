package com.tm.config;

public class ConfigurationException extends Exception {
	
	private static final long serialVersionUID = -3312146603561768393L;
	
	public ConfigurationException() {
	}

	public ConfigurationException(String message) {
		super(message);
	}
	
	public ConfigurationException(String message, Throwable t) {
		super(message, t);
	}

}
