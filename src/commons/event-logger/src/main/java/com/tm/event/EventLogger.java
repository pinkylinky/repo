package com.tm.event;

import com.tm.utils.common.Logger;

public class EventLogger extends Logger {
	
	protected EventLogger(Class clazz) {
		super(clazz);
	}
	
	public static EventLogger getLogger(Class clazz) {
		return new EventLogger(clazz);
	}

	@Override
	public void error(String message, String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message, Throwable t, String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message, String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message, String... args) {
		// TODO Auto-generated method stub
		
	}
	

}
