package com.tm.core;

public class AppManager {
	
	private static AppManager instance;
	private AppConfig config;
	
	public static void init(AppConfig config) {
		instance = new AppManager(config);
	}
	
	public static AppManager getInstance() {
		return instance;
	}
	
	private AppManager(AppConfig config) {
		this.config = config;
	}

	public AppConfig getConfig() {
		return config;
	}
	
	
}
