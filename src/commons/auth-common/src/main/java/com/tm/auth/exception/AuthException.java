package com.tm.auth.exception;

public class AuthException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public AuthException() {
		super();
	}

	public AuthException(String message) {
		super(message);
	}
	
	public AuthException(Exception e) {
		super(e);
	}
	
	public AuthException(String message, Exception e) {
		super(message, e);
	}

}
