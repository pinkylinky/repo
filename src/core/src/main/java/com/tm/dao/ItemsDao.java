package com.tm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tm.entity.Item;
import com.tm.jdbc.JDBCConnector;
import com.tm.jdbc.JDBCManager;
import com.tm.jdbc.dbtype.DBType;
import com.tm.jdbc.utils.JDBCUtils;
import com.tm.utils.common.ReflectionUtils;
import com.tm.utils.common.ReflectionUtils.FieldDescriptor;

public abstract class ItemsDao {
	
	protected Connection connection;
	protected JDBCConnector connector;
	
	public ItemsDao() {
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void setJDBCConnector(JDBCConnector connector) {
		this.connector = connector;
	}
	
	
	public List<Item> getItemsByGroupId(long groupId) throws SQLException {
		String sql = "select * from item where id in " +
				"(select item_id from item_vs_item_group where item_group_id = ?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, groupId);
			rs = ps.executeQuery();
			List<Item> items = new ArrayList<Item>();
			while (rs.next()) {
				Item item = (Item) JDBCUtils.getObject(rs, Item.class);
				items.add(item);
			}
			return items;
		} finally {
			JDBCUtils.close(ps, rs);
		}
			
	}
	
	public long insertItem(Item item) throws SQLException {
		long id = JDBCUtils.insert(connection, connector.getDBDescriptor(), item);
		if (!item.getGroupIds().isEmpty()) {
			for (long groupId : item.getGroupIds()) {
				addItemToGroup(id, groupId);
			}
		}
		return id;
	}
	
	public void addItemToGroup(long itemId, long groupId) throws SQLException {
		String sql = "insert into item_vs_item_group (item_id, item_group_id) values (?, ?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, itemId);
			ps.setLong(2, groupId);
			ps.executeUpdate();
		} finally {
			JDBCUtils.close(ps);
		}
			
	}

}
