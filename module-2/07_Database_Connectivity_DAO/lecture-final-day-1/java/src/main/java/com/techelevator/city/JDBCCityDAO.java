package com.techelevator.city;

// This is the DAO concrete class which implements the methods required by the interface

// The name of the class should be JDBCtable-nameDAO

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCityDAO implements CityDAO {  // implement the interface for the DAO
	                                           //     this class MUST implement all methods in the interface

	// JdbcTemplate class contains all the necessary code to interact with a database using Spring DAOs
	
	private JdbcTemplate jdbcTemplate;            // Define an reference variable an JdbcTemplate object
	
	public JDBCCityDAO(DataSource dataSource) {           // Constructor for the class that takes a datasource as a parameter
		this.jdbcTemplate = new JdbcTemplate(dataSource); // Instantiate a jdbcTemplate object using the datasource passed
		                                                  //      and assign it to the reference defined above
	}

	// Create/Add a row to the table using an object of the class passed in from the user which contains values for all it's variables
	//       except the city id which will be assigned by the database manager because the user doesn't know what it should be
	
	@Override  // ask the compiler to be sure we overriding the method required by the DAO interface
	public void save(City newCity) {
		
		// Define a String to hold the SQL statement we want to run
		String sqlInsertCity = "INSERT INTO city(id, name, countrycode, district, population) " +
							   "VALUES(?, ?, ?, ?, ?)";  // the ? are place holders for values specified when the statement is run
                                                         // in this example the values are coming from the object passed to method
		                                                 // we don't know the values until run time and this method is called
		
		// When we add a row to the City table the data base will automatically assign a city id because it is define as serial in the table
		// Since the database will assign the city id, we don't know what it will be
		// So we will ask the data base manager what the next city id is and assign it ourselves
		//    that way the City object the user passes will have the city id that is in the data base
		
		newCity.setId(getNextCityId());  // call a class method to get the next city id from the database manager and assign to the object passed

		// Run the SQL statement to access the database using JdbcTemplate object
		// Use the .update method because we are executing and INSERT which changes (updates) the data base
		// .update() wants the String with the SQL statement to be executed, followed by any values for the ? placeholders
		//                  SQL statement, values-for-the-?'s - one value for each ? in the SQL statement String
		jdbcTemplate.update(sqlInsertCity, newCity.getId(),          // id from object passed as the value or the first ? in the statement
										   newCity.getName(),        // name from the object passed as the value for the second ? in the statement
										   newCity.getCountryCode(), // countrCode from the object passed as value for the third  ? in the statement
										   newCity.getDistrict(),    // district from the object passed as value for the fourth ? in the statement
										   newCity.getPopulation()); // population from the object passed as value for the fifth ? in the statement
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

	// this method will get the next value for city id from the base manager and return it to the caller
	
	private long getNextCityId() {
		// nextval() SQL function will return the next sequential value from a sequence object
		// a sequence object is used by postgres to generate serial values to a column
		// A sequence object may be defined by a user and used by postgres or postgres will automatically define one for a serial type
		
		// Use the jdbcTemplate object to run a SELECT using queryForRowSet to get the next city_id value
		// Result is stores in the nextIdResult variable as a SQLRowSet
		//                                                   retrieve the next value for seq_city_id sequence object
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_city_id')");
	
		// We need to get the next city id out of the SQLRowSet and into a Java variable
		
		if(nextIdResult.next()) {               // if the SQLRowSet called nextIDResult has any data, move to the next line of data
			return nextIdResult.getLong(1);     // get the long value in column 1 of the line and return it
		} else {                                // if there is no data in nextIDResult, throw a RuntimeException
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
