package com.tm.ldap.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LDAPUser {
	
	private String login;
	private String fullLogin;
	private String firstName;
	private String lastName;
	private String whenChanged;
	private List<String> groups = new ArrayList<String>();
	private Map<String, List<String>> attributes = new HashMap<String, List<String>>();
	
	public LDAPUser(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getFullLogin() {
		return fullLogin;
	}

	public void setFullLogin(String fullLogin) {
		this.fullLogin = fullLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWhenChanged() {
		return whenChanged;
	}

	public void setWhenChanged(String whenChanged) {
		this.whenChanged = whenChanged;
	}

	public Map<String, List<String>> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Map<String, List<String>> attributes) {
		this.attributes = attributes;
	}
	
	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public boolean isAttrMultiple(String attrName) {
		List<String> attrValue = attributes.get(attrName);
		return attrValue != null && attrValue.size() > 1;
	}
	
	public boolean isAttrSingle(String attrName) {
		List<String> attrValue = attributes.get(attrName);
		return attrValue != null && attrValue.size() == 1;
	}
	
	public boolean isAttrExists(String attrName) {
		List<String> attrValue = attributes.get(attrName);
		return attrValue != null;
	}
	
	public String getAttributeValue(String attrName) {
		List<String> attrValue = attributes.get(attrName);
		return attrValue != null ? attrValue.get(0) : null;
	}
	
	public List<String> getAttributeValues(String attrName) {
		return attributes.get(attrName);
	}
	

}
