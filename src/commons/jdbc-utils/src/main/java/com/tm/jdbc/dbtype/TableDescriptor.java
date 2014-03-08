package com.tm.jdbc.dbtype;

import java.util.List;

public class TableDescriptor {
	
	private String name;
	
	private List<ColumnDescriptor> columns;
	
	public TableDescriptor(String name) {
		this.name = name;
	}
	
	public TableDescriptor(String name, List<ColumnDescriptor> columns) {
		this.name = name;
		this.columns = columns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ColumnDescriptor> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnDescriptor> columns) {
		this.columns = columns;
	}
	
	public String getFK() {
		return name + "_ID";
	}
}
