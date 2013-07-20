package com.tm.config;

public class ConfigParamMetadata {
	
	private String name;
	private Class<?> dataType;
	private boolean required;
	
	public ConfigParamMetadata(String name, Class<?> dataType, boolean required) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.required = required;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Class<?> getDataType() {
		return dataType;
	}
	
	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	

}
