package com.tm.auth.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthPrincipal {
	
	private String login;
	private List<String> roles = new ArrayList<String>();
	private Map<String, String> attributes = new HashMap<String, String>();
	
	public AuthPrincipal(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
}

