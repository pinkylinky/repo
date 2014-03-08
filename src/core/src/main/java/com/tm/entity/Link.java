package com.tm.entity;

public class Link {
	
	private BaseEntity parentEntity;
	private BaseEntity childEntity;
	
	public BaseEntity getParentEntity() {
		return parentEntity;
	}
	
	public void setParentEntity(BaseEntity parentEntity) {
		this.parentEntity = parentEntity;
	}
	
	public BaseEntity getChildEntity() {
		return childEntity;
	}
	
	public void setChildEntity(BaseEntity childEntity) {
		this.childEntity = childEntity;
	}
	
	

}
