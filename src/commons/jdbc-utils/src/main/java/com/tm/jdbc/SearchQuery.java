package com.tm.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchQuery {
	
	ResultSet search(Connection con) throws SQLException;

}
