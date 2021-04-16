package com.techelevator.projects.model;

import java.util.List;

public class Department {

	private long id;               // serial
	private String departmentName; // varchar
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return departmentName;
	}
	
	public void setName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return departmentName;
	}
	
}