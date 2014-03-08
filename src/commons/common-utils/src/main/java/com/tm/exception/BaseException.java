package com.tm.exception;

import java.io.Serializable;

public class BaseException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public BaseException() {
		
	}
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(Throwable e) {
		super(e);
	}

}
