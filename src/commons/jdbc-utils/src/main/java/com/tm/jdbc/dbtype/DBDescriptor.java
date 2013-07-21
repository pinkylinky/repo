package com.tm.jdbc.dbtype;

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
	
	
	
	public abstract String getDataType(DataType datatype);

}
