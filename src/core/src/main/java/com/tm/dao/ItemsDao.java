package com.tm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tm.entity.Item;
import com.tm.entity.ItemGroup;
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
	
	public long addGroup(ItemGroup itemGroup) throws SQLException {
		long id = JDBCUtils.insert(connection, connector.getDBDescriptor(), itemGroup);
		return id;
	}
	
//	public List<ItemGroup> getItemInGroups() throws SQLException {
//		String sql = "select i.id, i.name, g.id, g.name " +
//				"from item i, item_group g, item_vs_item_group ig " +
//				"where i.id = ig.item_id and g.id = ig.item_group_id " +
//				"order by g.name";
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			ps = connection.prepareStatement(sql);
//			rs = ps.executeQuery();
//			
//			Map<Long, ItemGroup> groups = new LinkedHashMap<Long, ItemGroup>();
//			List<ItemGroup> groupsList = new ArrayList<ItemGroup>();
//			
//			while (rs.next()) {
//				long itemId = rs.getLong(1);
//				String itemName = rs.getString(2);
//				long groupId = rs.getLong(3);
//				String groupName = rs.getString(4);
//				
//				ItemGroup group = groups.get(groupId);
//				if (group == null) {
//					group = new ItemGroup();
//					group.setId(groupId);
//					group.setName(groupName);
//					group.addItem(new Item(itemId, itemName));
//					groups.put(group.getId(), group);
//					groupsList.add(group);
//				}
//			}
//			return groupsList;
//		} finally {
//			JDBCUtils.close(ps, rs);
//		}
//			
//	}
	
	public List<ItemGroup> getItemGroups() throws SQLException {
		String sql = "select * from itemgroup";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<ItemGroup> groups = new ArrayList<ItemGroup>();
			Map<Long, ItemGroup> map = new LinkedHashMap<Long, ItemGroup>();
			while (rs.next()) {
				ItemGroup group = (ItemGroup) JDBCUtils.getObject(rs, ItemGroup.class);
				groups.add(group);
				map.put(group.getId(), group);
			}
			
			List<ItemGroup> uplevelGroups = new ArrayList<ItemGroup>();
			for (ItemGroup group : groups) {
				if (group.getParentGroupId() != null) {
					ItemGroup parentGroup = map.get(group.getParentGroupId());
					//group.setParentGroup(parentGroup);
					parentGroup.addChildGroup(group);
				} else {
					uplevelGroups.add(group);
				}
			}
			return uplevelGroups;
		} finally {
			JDBCUtils.close(ps, rs);
		}
			
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
