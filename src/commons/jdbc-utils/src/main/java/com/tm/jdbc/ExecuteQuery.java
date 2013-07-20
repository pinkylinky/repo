package com.tm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ExecuteQuery {
	
	int execute(Connection con) throws SQLException;

}
