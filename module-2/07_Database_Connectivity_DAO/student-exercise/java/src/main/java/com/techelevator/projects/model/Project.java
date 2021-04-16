package com.techelevator.projects.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

	private long id;            // serial
	private String name;        // varchar
	private LocalDate fromDate; // date
	private LocalDate toDate;   // date

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
