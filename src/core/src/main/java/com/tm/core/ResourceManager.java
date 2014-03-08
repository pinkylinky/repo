package com.tm.core;

import java.sql.Connection;
import java.sql.SQLException;

import com.tm.dao.ItemsDao;
import com.tm.jdbc.DBConfig;
import com.tm.jdbc.JDBCConnector;

public class ResourceManager {
	
	public ItemsDao getItemsDao() 
			throws SQLException, InstantiationException, IllegalAccessException {
		DBConfig config = AppManager.getInstance().getConfig().getDbConfig();
		Class<? extends ItemsDao> itemsDaoClass = AppManager.getInstance().getConfig().getItemsDaoClass();
		return getItemsDao(config, itemsDaoClass);
	}
	
	public ItemsDao getItemsDao(DBConfig config, Class<? extends ItemsDao> itemsDaoClass) 
			throws SQLException, InstantiationException, IllegalAccessException {
		JDBCConnector connector = new JDBCConnector(config);
		Connection connection = connector.getConnection();		
		ItemsDao dao = itemsDaoClass.newInstance();
		dao.setConnection(connection);
		dao.setJDBCConnector(connector);
		return dao;
	}

}
