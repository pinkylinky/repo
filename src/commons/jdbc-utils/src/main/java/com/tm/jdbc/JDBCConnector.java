package com.tm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.tm.jdbc.dbtype.DBDescriptor;
import com.tm.jdbc.dbtype.DBDescriptorFactory;
import com.tm.jdbc.dbtype.DBType;
import com.tm.jdbc.utils.JDBCUtils;

public class JDBCConnector {
	
	private DBDescriptor descriptor;
	private String connectionString;
	
	public JDBCConnector(DBType dbType, String host, String port, String dbName, 
			String username, String password) {
		descriptor = DBDescriptorFactory.getDBDescriptor(dbType);
		connectionString = descriptor.getConnectionString(host, port, dbName, username, password);
	}
	
	public Connection getConnection() throws SQLException {
		return JDBCUtils.getConnection(connectionString);
	}
	
	public DBDescriptor getDBDescriptor() {
		return descriptor;
	}

	
}
