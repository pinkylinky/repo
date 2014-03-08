package com.tm.core;

import java.sql.Connection;
import java.sql.SQLException;

import com.tm.dao.ItemsDao;
import com.tm.jdbc.DBConfig;
import com.tm.jdbc.JDBCConnector;

public class ResourceManager {
	
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
