package com.tm.jdbc;

import com.tm.jdbc.dbtype.DBType;

public class DBConfig {
	
	private DBType dbType;
	private String host;
	private String port;
	private String dbName;
	private String username;
	private String password;

	public DBConfig(DBType dbType, String host, String port, String dbName,
			String username, String password) {
		super();
		this.dbType = dbType;
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.username = username;
		this.password = password;
	}

	public DBType getDbType() {
		return dbType;
	}

	public void setDbType(DBType dbType) {
		this.dbType = dbType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
