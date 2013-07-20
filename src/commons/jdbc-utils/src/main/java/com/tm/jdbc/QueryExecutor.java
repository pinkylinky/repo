package com.tm.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tm.utils.Logger;

public class QueryExecutor {
	
	private ConnectionPool pool;
	
	public QueryExecutor(ConnectionPool pool) {
		this.pool = pool;
	}
	
	private static Logger logger = Logger.getLogger(QueryExecutor.class);
	
	public int execute(ExecuteQuery query) throws JDBCException {
		Connection con = null;
		try {
			con = pool.getConnection();
			return query.execute(con);
		} catch (SQLException e) {
			throw new JDBCException();
		} finally {
			closeConnection(con);
		}
	}
	
	public ResultSet search(SearchQuery query) throws JDBCException {
		Connection con = null;
		try {
			con = pool.getConnection();
			return query.search(con);
		} catch (SQLException e) {
			throw new JDBCException();
		} finally {
			closeConnection(con);
		}
	}
	
    private void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.debug("Ошибка с закрытием коннекшна: " + e.getMessage());
            }
        }
    }
}
