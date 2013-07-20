package com.tm.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConfigMetadata {
	
	private Map<String, ConfigParamMetadata> params = new HashMap<String, ConfigParamMetadata>();
	
	public void addParam(ConfigParamMetadata param) {
		params.put(param.getName(), param);
	}
	
	public ConfigParamMetadata getParam(String name) {
		return params.get(name);
	}
	
	public Collection<ConfigParamMetadata> getParams() {
		return params.values();
	}

}
