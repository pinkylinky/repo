package com.tm.ldap.utils;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import com.tm.ldap.config.LDAPConst;
import com.tm.ldap.entity.LDAPUser;
import com.tm.logger.utils.Logger;

public class AttributeUtils {
	
	private static final String NAMES_DELIMITER = ",";
	private static final String KEY_VALUE_DELIMITER = "=";
	private static final String AT = "@";
	private static final String POINT = ".";
	
	private static Logger logger = Logger.getLogger(AttributeUtils.class);
	
	public static String getAttributeValue(Attributes attrs, String attrName) {
		Attribute attr = attrs.get(attrName);
		if (attr != null){
			try {
				return (String) attr.get();
			} catch (NamingException e) {
				logger.error(e);
				return null;
			}			
		} else {
			return null;
		}
		
	}	
	
	public static List<String> getAttributeValues(Attributes attrs, String attrName) {
		Attribute attr = attrs.get(attrName);
		if (attr != null) {
			try {
				NamingEnumeration<?> result = attr.getAll();
				List<String> list = new ArrayList<String>();
				while (result.hasMoreElements()) {
					String value = (String) result.next();
					list.add(value);
				}
				return list;
			} catch (NamingException e) {
				logger.error(e);
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String buildFullLogin(Attributes attrs) {
		String distinguishedName = getAttributeValue(attrs, LDAPConst.DISTINGUISHED_NAME);
		return buildFullLogin(distinguishedName);
	}
	
	public static String buildFullLogin(String distinguishedName) {
		String[] names = distinguishedName.split(NAMES_DELIMITER);	
		StringBuilder sb = new StringBuilder();
		boolean loginSet = false;
		for (String name : names) {
			String[] keyValue = name.split(KEY_VALUE_DELIMITER);
			String key = keyValue[0];
			String value = keyValue[1];
			if (!loginSet && key.equalsIgnoreCase(LDAPConst.CN)) {
				loginSet = true;
				sb.append(value.toLowerCase()).append(AT);
			}
			if (key.equalsIgnoreCase(LDAPConst.DC)) {
				sb.append(value.toLowerCase()).append(POINT);
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}
	
	
	public static List<String> getUserGroups(Attributes attrs) {
		List<String> memberOfList = getAttributeValues(attrs, LDAPConst.MEMBER_OF);		
		return getUserGroups(memberOfList);
	}
	
	public static List<String> getUserGroups(List<String> memberOfList) {
		List<String> groups = new ArrayList<String>();
		if(memberOfList != null) for (String memberOf : memberOfList) {
			String[] tokens = memberOf.split(NAMES_DELIMITER);	
			String firstToken = tokens[0];
			String[] keyValue = firstToken.split(KEY_VALUE_DELIMITER);
			String value = keyValue[1];
			groups.add(value.toLowerCase());
		}		
		return groups;
	}
	
	public static LDAPUser buildLDAPUser(String login, Attributes attrs, String... attributesToGet) {
		
		LDAPUser ldapUser = new LDAPUser(login.toLowerCase());
		
		List<String> userGroups = getUserGroups(attrs);
		ldapUser.setGroups(userGroups);
		
		String firstName = getAttributeValue(attrs, LDAPConst.FIRST_NAME);
		ldapUser.setFirstName(firstName);
		
		String lastName = getAttributeValue(attrs, LDAPConst.LAST_NAME);
		ldapUser.setLastName(lastName);
		
		String fullLogin = buildFullLogin(attrs);
		ldapUser.setFullLogin(fullLogin);
		
		String whenChanged = getAttributeValue(attrs, LDAPConst.WHEN_CHANGED);
		ldapUser.setWhenChanged(whenChanged);
		
		if (attributesToGet != null) {
			for (String attrName : attributesToGet) {
				List<String> attrValues = getAttributeValues(attrs, attrName);
				if (attrValues != null) {
					ldapUser.getAttributes().put(attrName, attrValues);
				}
			}
		}
		
		return ldapUser;
	}


}
