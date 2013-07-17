package com.tm.ldap.exception;

public class LDAPIncorrectLoginPassword extends LDAPException {

	private static final long serialVersionUID = 1L;
	
	public LDAPIncorrectLoginPassword() {
		super();
	}

	public LDAPIncorrectLoginPassword(String message) {
		super(message);
	}
	
	public LDAPIncorrectLoginPassword(String message, Throwable t) {
		super(message, t);
	}

}
