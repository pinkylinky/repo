package com.tm.config;

public class ConfigParam {
	
	private ConfigParamMetadata metadata;
	private Object value;
	
	public ConfigParam(ConfigParamMetadata metadata, Object value) {
		this.metadata = metadata;
		this.value = value;
	}

	public ConfigParamMetadata getMetadata() {
		return metadata;
	}

	public Object getValue() {
		return value;
	}
	
	public <T> T getValue(Class<T> clazz) {
		if (metadata.getDataType() == clazz) {
			return (T) value;
		} else {
			throw new RuntimeException("Свойство " + metadata.getName() + " не является типом " + clazz.getSimpleName());
		}
	}
}
