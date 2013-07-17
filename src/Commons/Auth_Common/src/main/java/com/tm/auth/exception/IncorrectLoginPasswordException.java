package com.tm.auth.exception;

public class IncorrectLoginPasswordException extends AuthException {
	
	private static final long serialVersionUID = 1L;
	
	public IncorrectLoginPasswordException() {
		super();
	}

	public IncorrectLoginPasswordException(String message) {
		super(message);
	}
	
	public IncorrectLoginPasswordException(String message, Exception e) {
		super(message, e);
	}

}
