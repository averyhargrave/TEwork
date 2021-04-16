package com.techelevator.city;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

// This is the DAO concrete class which implements the methods required by the interface

// The name of the class should be: JDBCtable-nameDAO

public class JDBCCityDAO implements CityDAO { // Implements the interface for the DAO

	// The JDBC template class contains all the necessary code to interact with a Database using Spring DAOs
	private JdbcTemplate jdbcTemplate; // Define a reference variable for the JDBC template
	
	public JDBCCityDAO(DataSource dataSource) { // Provide a ctor for the class that takes a datasource as a parameter
		this.jdbcTemplate = new JdbcTemplate(dataSource); // Instantiate a JDBC template object using the data source
	}													  //     and assign it to the reference defined above

	// Create/Add a new row to the table using an object of the class passed in from the user which contains values for all its variables
	//      except the city id which will be assigned by the DBM
	@Override // ask the compiler to be sure we're overriding the method required by the DAO Interface
	public void save(City newCity) {
		
		// Define a String to hold the SQL statement we want to run
		String sqlInsertCity = "INSERT INTO city(id, name, countrycode, district, population) " +
							   "VALUES(?, ?, ?, ?, ?)"; // the ? are place-holders for values specified when the statement is executed
														// in this example the values are coming from the object passed to the method
														//    which means we don't know what they are until run time and this method is called
		
		// When we add a row to the City table, the database will automatically assign a city id because it was defined as serial in the database
		// Since the DBM will assign the city id, we don't know what it will be
		// We will ask the DBM what the next city id is, and assign it ourselves
		//       that way the City object the user passes will have the city id that is in the Database
		
		newCity.setId(getNextCityId()); // call a class method to get the next city id from the DBM and assign it to the object passed
		
		// Run the SQL statement to access the database using JdbcTemplate object
		// Use the .update method because we are executing an INSERT which changes (updates) the database
		// .update() wants the String with the SQL statement to be executed, followed by any values for the ? place-holders
		//                  SQL statement  values-for-the-?
		jdbcTemplate.update(sqlInsertCity, newCity.getId(),          // get the id from the object passed as the value for the first ?
										   newCity.getName(),        // get the name from the object passed as the value for the second ?
										   newCity.getCountryCode(), // get the country code from the object passed as the value for the third ?
										   newCity.getDistrict(),    // get the district from the object passed as the value for the fourth ?
										   newCity.getPopulation()); // get the population from the object passed as the value for the fifth ?
	}
	
	@Override
	public City findCityById(long id) {
		City theCity = null;
		String sqlFindCityById = "SELECT id, name, countrycode, district, population "+
							   "FROM city "+
							   "WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityById, id);
		if(results.next()) {
			theCity = mapRowToCity(results);
		}
		return theCity;
	}

	@Override
	public List<City> findCityByCountryCode(String countryCode) {
		ArrayList<City> cities = new ArrayList<>();
		String sqlFindCityByCountryCode = "SELECT id, name, countrycode, district, population "+
										   "FROM city "+
										   "WHERE countrycode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityByCountryCode, countryCode);
		while(results.next()) {
			City theCity = mapRowToCity(results);
			cities.add(theCity);
		}
		return cities;
	}

	@Override
	public List<City> findCityByDistrict(String district) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	// this method will get the next value for city id from the DBM and return it to the caller
	
	private long getNextCityId() {
		// nextval() SQL function will return the next sequential value from a sequence object
		// a sequence object is used by postgres to generate serial values for a column
		// a sequence object may be defined by a user and used by postgres or postgres will automatically define one for a serial type
		
		// Use the jdbcTemplate object to run a SELECT using queryForRowSet to get the next city_id value
		// Result is stored in the nextIdResult variable as a SQLRowSet
		//                                                  retrieve the next value for seq_city_id sequence object
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_city_id')");
		
		// We need to get the next city_id out of the SQLRowSet and into a Java variable
		
		if(nextIdResult.next()) {		    // If the SQLRowSet called nextIdResult has any data, move to the next line in the data
			return nextIdResult.getLong(1); // get the int in column 1 of the line and return it as a long
		} else { 							// if there is no data in nextIdResult, throw an exception
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	private City mapRowToCity(SqlRowSet results) {
		City theCity;
		theCity = new City();
		theCity.setId(results.getLong("id"));
		theCity.setName(results.getString("name"));
		theCity.setCountryCode(results.getString("countrycode"));
		theCity.setDistrict(results.getString("district"));
		theCity.setPopulation(results.getInt("population"));
		return theCity;
	}
}
