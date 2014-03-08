package com.tm.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tm.core.AppConfigurator;
import com.tm.core.AppManager;
import com.tm.dao.ItemsDao;
import com.tm.entity.ItemGroup;
import com.tm.jdbc.JDBCConnector;
import com.tm.jdbc.JDBCManager;
import com.tm.jdbc.dbtype.ColumnDescriptor;
import com.tm.jdbc.dbtype.DataType;
import com.tm.jdbc.dbtype.TableDescriptor;

public class DBCreator {
	
	private static final List<TableDescriptor> tables = new ArrayList<TableDescriptor>();
	
	public static void main(String[] args) throws Exception {
		
		AppConfigurator.configureDefault();
		JDBCConnector connector = new JDBCConnector(AppManager.getInstance().getConfig().getDbConfig());
		JDBCManager manager = new JDBCManager(connector);
		TableDescriptor items = createItemsTableDescriptor();
		tables.add(items);
		TableDescriptor itemsGroup = createItemsGroupTableDescriptor();
		tables.add(itemsGroup);
		TableDescriptor items_itemsGroup = createAssociationTableDescriptor(items.getName(), itemsGroup.getName());
		tables.add(items_itemsGroup);
		
		
		for (TableDescriptor table : tables) {
			try {
				manager.dropTable(table.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			manager.createTable(table);
		}
		
		ItemsDao dao = AppManager.getInstance().getResourceManager().getItemsDao();
		
		ItemGroup itemGroup = new ItemGroup();
		itemGroup.setName("Root 1");
		dao.addGroup(itemGroup);
		
		itemGroup = new ItemGroup();
		itemGroup.setName("Root 2");
		long rootGroupId = dao.addGroup(itemGroup);
		
		itemGroup = new ItemGroup();
		itemGroup.setName("Child 1");
		itemGroup.setParentGroupId(rootGroupId);
		long groupId = dao.addGroup(itemGroup);
		
		itemGroup = new ItemGroup();
		itemGroup.setName("Child Child 1");
		itemGroup.setParentGroupId(groupId);
		groupId = dao.addGroup(itemGroup);
		
		itemGroup = new ItemGroup();
		itemGroup.setName("Child 2");
		itemGroup.setParentGroupId(rootGroupId);
		groupId = dao.addGroup(itemGroup);
		
		itemGroup = new ItemGroup();
		itemGroup.setName("Child Child 2");
		itemGroup.setParentGroupId(groupId);
		groupId = dao.addGroup(itemGroup);
		
		
//		long item1Id = manager.insert(items.getName(), createEntityData(1));
//		long item2Id = manager.insert(items.getName(), createEntityData(2));
//		long itemGroupId = manager.insert(itemsGroup.getName(), createEntityData(2));
//		manager.insert(items_itemsGroup.getName(), 
//				createAssociationData(items.getFK(), item1Id, itemsGroup.getFK(), itemGroupId));
//		manager.insert(items_itemsGroup.getName(), 
//				createAssociationData(items.getFK(), item2Id, itemsGroup.getFK(), itemGroupId));
		
	}
	
	private static Map<String, Object> createEntityData(int num) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("NAME", "name " + num);
		return data;
	}
	
	private static Map<String, Object> createAssociationData(String col1, long id1, String col2, long id2) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(col1, id1);
		data.put(col2, id2);
		return data;
	}
	
	private static TableDescriptor createItemsTableDescriptor() {
		TableDescriptor table = createCommonTableDescriptor("ITEM");
		return table;
	}
	
	private static TableDescriptor createItemsGroupTableDescriptor() {
		TableDescriptor table = createCommonTableDescriptor("ITEMGROUP");
		List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();
		columns.add(new ColumnDescriptor("ID", DataType.LONG, true, true));
		columns.add(new ColumnDescriptor("NAME", DataType.STRING, true));
		columns.add(new ColumnDescriptor("PARENTGROUPID", DataType.LONG));
		table.setColumns(columns);
		return table;
	}
	
	private static TableDescriptor createCommonTableDescriptor(String tableName) {
		TableDescriptor table = new TableDescriptor(tableName);
		List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();
		columns.add(new ColumnDescriptor("ID", DataType.LONG, true, true));
		columns.add(new ColumnDescriptor("ALIAS", DataType.STRING));
		columns.add(new ColumnDescriptor("NAME", DataType.STRING, true));
		table.setColumns(columns);
		return table;
	}
	
	private static TableDescriptor createAssociationTableDescriptor(
			String tableName1, String tableName2) {
		String tableName = tableName1 + "_VS_" + tableName2;
		TableDescriptor table = new TableDescriptor(tableName);
		List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();
		columns.add(new ColumnDescriptor("ID", DataType.LONG, true, true));
		columns.add(new ColumnDescriptor(tableName1 + "_ID", DataType.LONG, true));
		columns.add(new ColumnDescriptor(tableName2 + "_ID", DataType.LONG, true));
		table.setColumns(columns);
		return table;
	}

}
