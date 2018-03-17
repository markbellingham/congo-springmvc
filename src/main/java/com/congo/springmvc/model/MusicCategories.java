package com.congo.springmvc.model;

public class MusicCategories {
	
	private int id;
	private String name;
	
	public MusicCategories(String name) {
		super();
		this.name = name;
	}

	public MusicCategories() {
		super();
	}

	@Override
	public String toString() {
		return "MusicCategories [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
