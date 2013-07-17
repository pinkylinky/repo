package com.tm.ldap.exception;

public class LDAPException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LDAPException() {
		super();
	}
	
	public LDAPException(String message) {
		super(message);
	}
	
	public LDAPException(String message, Throwable t) {
		super(message, t);
	}

}
