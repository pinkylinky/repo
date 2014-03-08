package com.tm.core;

import com.tm.dao.ItemsDao;
import com.tm.jdbc.DBConfig;

public class AppConfig {
	
	private DBConfig dbConfig;
	private Class<? extends ItemsDao> itemsDaoClass;
	
	public DBConfig getDbConfig() {
		return dbConfig;
	}
	
	public void setDbConfig(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}
	
	public Class<? extends ItemsDao> getItemsDaoClass() {
		return itemsDaoClass;
	}
	
	public void setItemsDaoClass(Class<? extends ItemsDao> itemsDaoClass) {
		this.itemsDaoClass = itemsDaoClass;
	}
	
	
	

}
