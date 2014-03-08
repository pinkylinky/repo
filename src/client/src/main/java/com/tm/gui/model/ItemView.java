package com.tm.gui.model;

import javafx.beans.property.SimpleStringProperty;

public class ItemView {
	
	private final SimpleStringProperty id;
	private final SimpleStringProperty name;
	
	public ItemView(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }
	
	public String getId() {
        return id.get();
    }
	
    public void setId(String id) {
    	this.id.set(id);
    }
	
	public String getName() {
        return name.get();
    }
	
    public void setName(String name) {
    	this.name.set(name);
    }

}
