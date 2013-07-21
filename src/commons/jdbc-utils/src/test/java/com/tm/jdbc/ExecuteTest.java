package com.tm.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tm.jdbc.dbtype.ColumnDescriptor;
import com.tm.jdbc.dbtype.DBType;
import com.tm.jdbc.dbtype.DataType;
import com.tm.jdbc.dbtype.TableDescriptor;

public class ExecuteTest {
	
	@Test
	public void createDropTable() throws Exception {
		JDBCConnector connector = new JDBCConnector(DBType.Derby, 
				null, null, "DerbyTest", null, null);
		JDBCManager manager = new JDBCManager(connector);
		
		
		TableDescriptor table = new TableDescriptor("SERVICE");
		List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();
		columns.add(new ColumnDescriptor("ID", DataType.LONG, true, true));
		columns.add(new ColumnDescriptor("NAME", DataType.STRING, true));
		columns.add(new ColumnDescriptor("DESCRIPTION", DataType.STRING));
		table.setColumns(columns);
		
		manager.createTable(table);
		manager.addColumn(table.getName(), new ColumnDescriptor("IS_ACTIVE", DataType.BOOLEAN));
		manager.dropColumn(table.getName(), columns.get(2).getName());
		manager.dropTable(table.getName());
	}

}
