package com.techelevator.city;

// POJO for accessing the City table data in the World database

// class should have the same name as the table

// Although not required, it is recommended the variable names in POJO match the column names in the table

public class City {
	private Long   id;            // serial in the table - serial may be outside the range of an int - use Long to be safe
	private String name;          // varchar in the table
	private String countryCode;   // bpchar in the table
	private String district;      // varchar in the table
	private int    population;    // int4 (integer) in the table

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
