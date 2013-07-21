package com.tm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryExecutor {
	
	void process(Connection con) throws SQLException;

}
