package com.tm.auth.utils;

import junit.framework.Assert;

import org.junit.Test;

import com.tm.ldap.config.LDAPConfiguration;
import com.tm.ldap.core.LDAPManager;
import com.tm.ldap.entity.LDAPUser;

public class LDAPUtilsTest {
	
	private static final String ldapUrl = "ldap://10.31.5.42:389";
	private static final String baseDN = "DC=DOMAIN,DC=rbc";
	private static final String bindName = "root";
	private static final String bindPassword = "q1";
	private static final String userFilter = "";
	private static final String userIdMap = "person:cn";
	
	private static final String login = "rbc-operator";
	private static final String password = "q1q1q1!";
	
	private static final String b2LoginAttr = "b2Login";
	
	@Test
	public void authenticate() throws Exception {		
		
		LDAPConfiguration config = new LDAPConfiguration(ldapUrl, baseDN, bindName, bindPassword, userFilter, userIdMap);		
		LDAPManager manager = new LDAPManager(config);
		
		LDAPUser user = manager.authenticate(login, password);
		Assert.assertEquals(login, user.getLogin());
	}
	
	@Test
	public void getUserAttributes() throws Exception {		
		
		LDAPConfiguration config = new LDAPConfiguration(ldapUrl, baseDN, bindName, bindPassword, userFilter, userIdMap);		
		LDAPManager manager = new LDAPManager(config);
		
		LDAPUser user = manager.getUserInfo(login, b2LoginAttr);
		Assert.assertEquals(login, user.getLogin());
		Assert.assertEquals("JET", user.getAttributeValue(b2LoginAttr));
	}

}
