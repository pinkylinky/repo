package com.tm.ldap.core;

import javax.naming.directory.Attributes;

import com.tm.ldap.config.LDAPConfiguration;
import com.tm.ldap.entity.LDAPUser;
import com.tm.ldap.exception.LDAPException;
import com.tm.ldap.utils.AttributeUtils;
import com.tm.ldap.utils.ConnectorUtils;

public class LDAPManager {
	
	private LDAPConfiguration config;
	
	public LDAPManager(LDAPConfiguration config) {
		this.config = config;
	}
	
	public LDAPUser authenticate(String login, String password, String... attributesToGet) throws LDAPException {
		Attributes attrs = ConnectorUtils.authenticate(login, password, config);
		return AttributeUtils.buildLDAPUser(login, attrs, attributesToGet);
	}
	
	public LDAPUser getUserInfo(String login, String... attributesToGet) throws LDAPException {
		Attributes attrs = ConnectorUtils.getUserAttributes(login, config);
		return AttributeUtils.buildLDAPUser(login, attrs, attributesToGet);
	}

}
