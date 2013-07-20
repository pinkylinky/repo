package com.tm.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
	
	private Map<String, ConfigParam> properties = new HashMap<String,  ConfigParam>();
	
	public boolean isPropertyExists(String name) {
		return properties.containsKey(name);
	}
	
	public String getStringProperty(String name) {
		return getParam(name).getValue(String.class);
	}
	
	public Integer getIntProperty(String name) {
		return getParam(name).getValue(Integer.class);
	}
	
	public Long getLongProperty(String name) {
		return getParam(name).getValue(Long.class);
	}
	
	public Boolean getBooleanProperty(String name) {
		return getParam(name).getValue(Boolean.class);
	}
	
	public Date getDateProperty(String name) {
		return getParam(name).getValue(Date.class);
	}
	
	public void setProperty(String name, ConfigParam param) {
		properties.put(name, param);
	}
	
	private ConfigParam getParam(String name) {
		return properties.get(name);
	}

}
