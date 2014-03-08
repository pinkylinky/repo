package com.tm.exception;

public class BusinessLogicException extends BaseException {
	
	public BusinessLogicException() {
		
	}
	
	public BusinessLogicException(String message) {
		super(message);
	}
	
	public BusinessLogicException(Throwable e) {
		super(e);
	}

}
