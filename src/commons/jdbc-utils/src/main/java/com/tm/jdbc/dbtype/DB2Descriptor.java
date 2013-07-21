package com.tm.jdbc.dbtype;


public class DB2Descriptor extends DBDescriptor {

	@Override
	public String getConnectionString(String host, String port, String dbName,
			String username, String password) {
		
		return String.format("jdbc:db2://%s:%s/%s:user=%s;password=%s;",
				host, port, dbName, username, password);
	}

	@Override
	public String getDriverClass() {
		return "com.ibm.db2.jcc.DB2Driver";
	}

	@Override
	public String getCreateTableSQLCommand(TableDescriptor table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataType(DataType datatype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddColumnSQLCommand(String tableName,
			ColumnDescriptor column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDropColumnSQLCommand(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

}
