package com.tm.auth.strategy;

import java.util.List;

import com.tm.auth.entity.AuthPrincipal;
import com.tm.auth.exception.AuthException;
import com.tm.auth.exception.IncorrectLoginPasswordException;
import com.tm.ldap.config.LDAPConfiguration;
import com.tm.ldap.core.LDAPManager;
import com.tm.ldap.entity.LDAPUser;
import com.tm.ldap.exception.LDAPException;
import com.tm.ldap.exception.LDAPIncorrectLoginPassword;

public class LDAPAuthStrategy implements AuthStrategy {
	
	private LDAPManager manager;
	private List<String> attributesToGet;
	
	public LDAPAuthStrategy(LDAPConfiguration config) {
		this.manager = new LDAPManager(config);
	}

	public LDAPAuthStrategy(LDAPConfiguration config, List<String> attributesToGet) {
		this(config);
		this.attributesToGet = attributesToGet;
	}

	@Override
	public AuthPrincipal authenticate(String login, String password) throws AuthException {	
		try {
			LDAPUser user = manager.authenticate(login, password, 
					attributesToGet != null ? attributesToGet.toArray(new String[]{}) : null);
			return new AuthPrincipal(user.getLogin());//TODO
		} catch (LDAPIncorrectLoginPassword e) {
			throw new IncorrectLoginPasswordException();
		} catch (LDAPException e) {
			throw new AuthException(e);
		}
	}

}
