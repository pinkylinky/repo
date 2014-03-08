package com.tm.entity;

public class Image extends Item {
	
	private String path;
	private byte[] image;
	
	public Image(String path) {
		super();
		this.path = path;
	}

	public Image(byte[] image) {
		super();
		this.image = image;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
