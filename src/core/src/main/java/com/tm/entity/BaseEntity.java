package com.tm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseEntity implements Serializable {
	
	protected Long id;
	protected String alias;
	protected String name;
	
	protected Map<String, ItemAttribute> attributes = new HashMap<String, ItemAttribute>();
	protected Map<String, ItemGroup> groups = new HashMap<String, ItemGroup>();
	protected Map<String, Link> links = new HashMap<String, Link>();
	
	protected List<Long> groupIds = new ArrayList<Long>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, ItemAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Map<String, ItemAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public Map<String, ItemGroup> getGroups() {
		return groups;
	}
	
	public Map<String, Link> getLinks() {
		return links;
	}
	
	public void addToGroup(long groupId) {
		groupIds.add(groupId);
	}
	
	public List<Long> getGroupIds() {
		return groupIds;
	}
	
	public String toString() {
		return id + " " + alias + " " + name;
	}


}
