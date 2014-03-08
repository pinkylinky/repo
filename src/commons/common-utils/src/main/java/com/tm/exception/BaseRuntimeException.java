package com.tm.exception;

public class BaseRuntimeException extends RuntimeException {
	
	public BaseRuntimeException() {
		
	}
	
	public BaseRuntimeException(String message) {
		super(message);
	}
	
	public BaseRuntimeException(Throwable e) {
		super(e);
	}

}
