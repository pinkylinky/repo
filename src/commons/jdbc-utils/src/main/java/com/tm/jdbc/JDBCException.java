package com.tm.jdbc;

public class JDBCException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public JDBCException() {
		super();
	}
	
	public JDBCException(String message) {
		super(message);
	}
	
	public JDBCException(String message, Throwable t) {
		super(message, t);
	}

}
