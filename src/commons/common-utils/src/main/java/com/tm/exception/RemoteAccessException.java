package com.tm.exception;

public class RemoteAccessException extends BaseException {
	
	public RemoteAccessException() {
		
	}
	
	public RemoteAccessException(String message) {
		super(message);
	}
	
	public RemoteAccessException(Throwable e) {
		super(e);
	}

}
