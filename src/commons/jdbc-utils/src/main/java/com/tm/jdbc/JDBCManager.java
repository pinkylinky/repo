package com.tm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.tm.jdbc.dbtype.ColumnDescriptor;
import com.tm.jdbc.dbtype.DBDescriptor;
import com.tm.jdbc.dbtype.TableDescriptor;
import com.tm.jdbc.utils.JDBCUtils;

public class JDBCManager {
	
	private JDBCConnector connector;
	
	public JDBCManager(JDBCConnector connector) {
		this.connector = connector;
	}
	
	public void createTable(TableDescriptor table) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getCreateTableSQLCommand(table);
			JDBCUtils.execute(con, command);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public void dropTable(String tableName) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getDropTableSQLCommand(tableName);
			JDBCUtils.execute(con, command);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public void addColumn(String tableName, ColumnDescriptor column) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getAddColumnSQLCommand(tableName, column);
			JDBCUtils.execute(con, command);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public void dropColumn(String tableName, String columnName) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getDropColumnSQLCommand(tableName, columnName);
			JDBCUtils.execute(con, command);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public int insert(String tableName, Map<String, Object> data) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getInsertSQLCommand(tableName, data.keySet().toArray(new String[]{}));
			return JDBCUtils.execute(con, command, data.values().toArray(new Object[]{}));
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public int update(String tableName, String searchCondition, Map<String, Object> data) throws SQLException  {
		Connection con = null;
		try {
			con = connector.getConnection();
			DBDescriptor descriptor = connector.getDBDescriptor();
			String command = descriptor.getUpdateSQLCommand(tableName, searchCondition, data.keySet().toArray(new String[]{}));
			return JDBCUtils.execute(con, command, data.values().toArray(new Object[]{}));
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public void execute(QueryExecutor query) throws SQLException {
		Connection con = null;
		try {
			con = connector.getConnection();
			query.process(con);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public int execute(String query, Object... params) throws SQLException {
		Connection con = null;
		try {
			con = connector.getConnection();
			return JDBCUtils.execute(con, query, params);
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}

}
