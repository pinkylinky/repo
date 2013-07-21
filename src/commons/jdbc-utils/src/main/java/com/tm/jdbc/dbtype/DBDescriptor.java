package com.tm.jdbc.dbtype;

import java.util.Collection;

public abstract class DBDescriptor {
	
	public abstract String getConnectionString(String host, String port, String dbName, 
			String username, String password);
	
	public abstract String getDriverClass();
	
	public abstract String getCreateTableSQLCommand(TableDescriptor table);
	
	public abstract String getAddColumnSQLCommand(String tableName, ColumnDescriptor column);
	
	public abstract String getDropColumnSQLCommand(String tableName, String columnName);
	
	public String getDropTableSQLCommand(String tableName) {
		return "DROP TABLE " + tableName;
	}
	
	public String getInsertSQLCommand(String tableName, String... columnNames) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName).append(" (");
		for (int i = 0; i < columnNames.length; i++) {
			sb.append(columnNames[i]);
			if (i != columnNames.length - 1)
				sb.append(", ");
		}
		sb.append(") VALUES (");
		for (int i = 0; i < columnNames.length; i++) {
			sb.append("?");
			if (i != columnNames.length - 1)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String getUpdateSQLCommand(String tableName, String searchCondition, String... columnNames) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName).append(" SET ");
		for (int i = 0; i < columnNames.length; i++) {
			sb.append(columnNames[i]).append(" = ?");
			if (i != columnNames.length - 1)
				sb.append(", ");
		}
		if (searchCondition != null)
			sb.append(" WHERE ").append(searchCondition);
		return sb.toString();
	}
	
	public abstract String getDataType(DataType datatype);

}
