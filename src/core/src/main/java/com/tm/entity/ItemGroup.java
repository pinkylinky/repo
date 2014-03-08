package com.tm.entity;

import java.util.HashMap;
import java.util.Map;

public class ItemGroup extends BaseEntity {
	
	private Map<String, Item> items = new HashMap<String, Item>();

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}
	
	


}
