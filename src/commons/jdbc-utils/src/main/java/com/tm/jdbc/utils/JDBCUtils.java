package com.tm.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tm.jdbc.dbtype.DBDescriptor;
import com.tm.jdbc.dbtype.DBDescriptorFactory;
import com.tm.jdbc.dbtype.DBType;
import com.tm.utils.Logger;

public class JDBCUtils {
	
	private static final DBType DEFAULT_DB_TYPE = DBType.Derby;
	
	private static Logger logger = Logger.getLogger(JDBCUtils.class);
	
	public static Connection getConnection(DBType dbType, String host, String port, String dbName, 
			String username, String password) throws SQLException {
		
		DBDescriptor desc = DBDescriptorFactory.getDBDescriptor(dbType);
		String connectionString = desc.getConnectionString(host, port, dbName, username, password);
		return getConnection(dbType, connectionString);
	}
	
	public static Connection getConnection(DBType dbType, String connectionString) throws SQLException {		
		DBDescriptor desc = DBDescriptorFactory.getDBDescriptor(dbType);
		String driverClass = desc.getDriverClass();
		return getConnection(driverClass, connectionString);
	}
	
	public static Connection getConnection(String driverClass, String connectionString) throws SQLException {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(connectionString); 
	}
	
	public static Connection getConnection(String connectionString) throws SQLException {
		DBDescriptor desc = DBDescriptorFactory.getDBDescriptor(DEFAULT_DB_TYPE);
		String driverClass = desc.getDriverClass();
		return getConnection(driverClass, connectionString);
	}
	
	public static Connection getConnection(String host, String port, String dbName, 
			String username, String password) throws SQLException {
		
		DBDescriptor desc = DBDescriptorFactory.getDBDescriptor(DEFAULT_DB_TYPE);
		String connectionString = desc.getConnectionString(host, port, dbName, username, password);
		return getConnection(connectionString);
	}
	
	public static PreparedStatement buildPrepareStatement(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = con.prepareStatement(query);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
		return ps; 
	}
	
	public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.debug("Ошибка с закрытием Connection: " + e.getMessage());
            }
        }
    }
	
	public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
            	rs.close();
            } catch (SQLException e) {
                logger.debug("Ошибка с закрытием ResultSet: " + e.getMessage());
            }
        }
    }
	
	public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
            	ps.close();
            } catch (SQLException e) {
                logger.debug("Ошибка с закрытием PreparedStatement: " + e.getMessage());
            }
        }
    }
	
	public static ResultSet search(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}
	
	public static ResultSet search(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = buildPrepareStatement(con, query, params);
		return search(ps);
	}
	
	public static int execute(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();
	}
	
	public static int execute(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = buildPrepareStatement(con, query, params);
		return execute(ps);
	}

}
