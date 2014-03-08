package com.tm.config;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import com.tm.utils.common.PropertiesUtils;
import com.tm.utils.datatypes.StringUtils;

public class Configurator {
	
	private static Configuration configuration;
	
	public static Configuration getConfiguration() {
		if (configuration == null)
			throw new RuntimeException("Конфигурация не найдена");
		return configuration;
	}
	
	public static Configuration configure(Properties properties, ConfigMetadata metadata) throws ConfigurationException {
		configuration = new Configuration();
		for (ConfigParamMetadata metadataParam : metadata.getParams()) {
			String name = metadataParam.getName();
			boolean isRequired = metadataParam.isRequired();
			String value = properties.getProperty(name);
			if (StringUtils.isNullOrEmpty(value) && isRequired) {
				throw new ConfigurationException("Не задан параметр " + name);
			}
			Class<?> dataType = metadataParam.getDataType();
			try {
				Object val = getTypedValue(properties, name, dataType);
				ConfigParam param = new ConfigParam(metadataParam, val);
				configuration.setProperty(name, param);
			} catch (Exception e) {
				throw new ConfigurationException("Неверный формат параметра " + name + "=" + value + " (должен быть " + dataType.getSimpleName() + ")", e);
			}
			
		}
		
		return configuration;		
	}
	
	private static Object getTypedValue(Properties properties, String name, Class<?> dataType) 
			throws ConfigurationException, ParseException {
		Object val = null;
		if (dataType == String.class) {
			val = PropertiesUtils.getStringProperty(properties, name);;					
		} else if (dataType == Integer.class) {
			val = PropertiesUtils.getIntegerProperty(properties, name);					
		} else if (dataType == Long.class) {
			val = PropertiesUtils.getLongProperty(properties, name);
		} else if (dataType == Boolean.class) {
			val = PropertiesUtils.getBooleanProperty(properties, name);
		} else if (dataType == Date.class) {
			val = PropertiesUtils.getDateProperty(properties, name);
		} else {
			throw new ConfigurationException("Не найден тип " + name + ": " + dataType.getSimpleName());
		}
		return val;
	}

}
