package com.tm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.tm.jdbc.utils.JDBCUtils;

public class ConnectionPool {
	
	private String connectionString;
	
	public ConnectionPool(int poolSize, String host, String port, String dbName, String username, String password) {
		connectionString = JDBCUtils.buildConnectionString(host, port, dbName, username, password);
	}
	
	public Connection getConnection() throws SQLException {
		return JDBCUtils.getConnection(connectionString); 
	}

}
