package com.tm.core;

import com.tm.dao.ItemsDao;
import com.tm.dao.ItemsDaoStub;
import com.tm.jdbc.DBConfig;
import com.tm.jdbc.dbtype.DBType;

public class AppConfigurator {
	
	public static void configureDefault() {
		DBConfig dbConfig = new DBConfig(DBType.Derby, null, null, "DerbyTest", null, null);
		Class<? extends ItemsDao> itemsDaoClass = ItemsDaoStub.class;
		
		AppConfig config = new AppConfig();
		config.setDbConfig(dbConfig);
		config.setItemsDaoClass(itemsDaoClass);
		
		AppManager.init(config);
	}
	
	public static void configure(AppConfig config) {
		AppManager.init(config);
	}

}
