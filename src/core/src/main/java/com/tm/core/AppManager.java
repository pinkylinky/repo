package com.tm.core;

public class AppManager {
	
	private static AppManager instance;
	private AppConfig config;
	private ResourceManager resourceManager = new ResourceManager();
	
	public static AppManager init(AppConfig config) {
		instance = new AppManager(config);
		return instance;
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

	public ResourceManager getResourceManager() {
		return resourceManager;
	}
	
}
