package com.tm.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemGroup {
	
	private Long id;
	private String name;
	private Long parentGroupId;
	private ItemGroup parentGroup;
	
	private Map<String, Item> items = new HashMap<String, Item>();
	private List<ItemGroup> childGroups = new ArrayList<ItemGroup>();
	
	public ItemGroup() {
		
	}
	
	public ItemGroup(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ItemGroup(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(Long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public ItemGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(ItemGroup parentGroup) {
		this.parentGroup = parentGroup;
		this.parentGroupId = parentGroup.getId();
	}

	public Map<String, Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.put(item.getName(), item);
	}

	public List<ItemGroup> getChildGroups() {
		return childGroups;
	}
	
	public void addChildGroup(ItemGroup group) {
		childGroups.add(group);
		group.setParentGroup(this);
	}
	


}
