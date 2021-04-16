package com.techelevator.city;

import java.util.List;

// This is the DAO Interface for the city table in the World database
// The name should be: table-nameDAO

// It identifies methods that must be implemented in order to use this DAO
// Typically CRUD processes are required methods for a Spring DAO
// Other methods may also be required at the DAO designer's discretion

// This DAO Interface gives examples of common processing requirements for a Spring DAO

// Note that objects of the class are used to insert and update, rather than individual values

// Not included in this DAO, but common is a method that returns all rows in the table as a List

public interface CityDAO {
	
	public void save(City newCity); // Create/Add a new row to the table using an object of the class
	
	
	public City 	  findCityById(long id);					 // One or more search methods
	public List<City> findCityByCountryCode(String countryCode); //   to retrieve data
	public List<City> findCityByDistrict(String district);		 //   in the table
	

	public void update(City city); // Update a row in the table using an object of the class
	public void delete(long id);   // Delete a row in the table using the id passed as the parameter
								   //     The parameter is usually the column(s) in the primary key
}
