package com.tm.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	
	public static Connection getConnection(String host, String port, String dbName, 
			String username, String password) throws SQLException {
		
		String connectionString = buildConnectionString(host, port, dbName, username, password);
		return getConnection(connectionString);
	}
	
	public static Connection getConnection(String connectionString) throws SQLException {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(connectionString); 
	}
	
	public static String buildConnectionString(String host, String port, String dbName, 
			String username, String password) {
		
		return String.format("jdbc:db2://%s:%s/%s:user=%s;password=%s;", 
				host, port, dbName, username, password);
	}

}
