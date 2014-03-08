package com.tm.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tm.jdbc.dbtype.DBDescriptor;
import com.tm.jdbc.dbtype.DBDescriptorFactory;
import com.tm.jdbc.dbtype.DBType;
import com.tm.utils.common.Logger;
import com.tm.utils.common.ReflectionUtils;
import com.tm.utils.common.ReflectionUtils.FieldDescriptor;

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
	
	public static PreparedStatement buildPreparedStatement(Connection con, String query, 
			boolean autoGenerateKeys, Object... params) throws SQLException {
		
		PreparedStatement ps = autoGenerateKeys ? 
				con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
				: con.prepareStatement(query);
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
	
	public static void close(PreparedStatement ps, ResultSet rs) {
		closeResultSet(rs);
		closePreparedStatement(ps);
    }
	
	public static void close(PreparedStatement ps) {
		closePreparedStatement(ps);
    }
	
	public static ResultSet search(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}
	
	public static ResultSet search(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = buildPreparedStatement(con, query, false, params);
		return search(ps);
	}
	
	public static int update(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = buildPreparedStatement(con, query, false, params);
		return ps.executeUpdate();
	}
	
	public static long insert(Connection con, String query, 
			Object... params) throws SQLException {
		PreparedStatement ps = buildPreparedStatement(con, query, true, params);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getLong(1);
	}
	
	public static Object getObject(ResultSet rs, Class<?> clazz) throws SQLException {
		try {
			List<FieldDescriptor> itemFields = ReflectionUtils.getFields(clazz);
			Object obj = clazz.newInstance();
			for (FieldDescriptor field : itemFields) {
				Object value = rs.getObject(field.getName());
				if (value != null) {
					value = ReflectionUtils.convertValue(field, value);
					ReflectionUtils.setField(field.getName(), value, obj);
				}
			}
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long insert(Connection con, DBDescriptor dbDescriptor, Object obj) throws SQLException {
		List<FieldDescriptor> itemFields = ReflectionUtils.getFieldValues(obj);
		Map<String, Object> data = new HashMap<String, Object>();
		for (FieldDescriptor field : itemFields) {
			if (!field.getName().toUpperCase().equals("ID")) {
				data.put(field.getName(), field.getValue());
			}
			
		}
		String tableName = obj.getClass().getSimpleName();
		String command = dbDescriptor.getInsertSQLCommand(tableName, data.keySet().toArray(new String[]{}));
		return insert(con, command, data.values().toArray(new Object[]{}));
	}

}
