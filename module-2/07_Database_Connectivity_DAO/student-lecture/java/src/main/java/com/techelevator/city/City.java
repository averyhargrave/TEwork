package com.techelevator.city;

// This is the POJO for accessing the city table data in the World database
// The class should have the same name as the table

// Although not required, strongly recommended that variable names match the table column names 

public class City {     // in the table, these are:
	private Long id;			// serial - may be outside the range of an int, use long to be safe
	private String name;		// varchar
	private String countryCode; // bpchar
	private String district;    // varchar
	private int population;		// int4 (integer)

	public Long getId() { 
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
