package com.tm.auth.strategy;

import com.tm.ldap.config.LDAPConfiguration;

public class LDAPConfigurationHolder {
	
	private static LDAPConfiguration config;
	
	public static synchronized LDAPConfiguration getConfig() {
		if (config == null)
			throw new RuntimeException("Не найдена конфигурация LDAP");
		return config;
	}
	
	public static synchronized void initConfig(LDAPConfiguration config) {
		LDAPConfigurationHolder.config = config;
	}

}
