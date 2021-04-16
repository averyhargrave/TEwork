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
		
		// When we add a row to the City table the data base will automatically assign a city id because it is defined as serial in the table
		// Since the database manager will assign the city id, we don't know what it will be
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
	public City findCityById(long id) {   // return a City object for the city id passed in by the user

		City theCity = null;     // Define the object returned - initially it is null

		// Define a String to hold the SQL statement we want to run
		String sqlFindCityById = "SELECT id, name, countrycode, district, population " +
							     "  FROM city " +
							     " WHERE id = ?";   // A placeholder (?) is used because we don't know what id the user is sending us

		// Execute the SQL statement in the String including values/variables for the placeholders (?)
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityById, id);  // Send the id passed by the used as the placeholder value

		// Since we are SELECTing by the primary key, we know only one row can possibly be returned
		// So we don't need a loop to process the data in the the SQLRowSet
		if(results.next()) {                  // If a row was returned from the SELECT, position to the row in the cursor
			theCity = mapRowToCity(results);  // Store it's values in the City object to be returned using a class method to do so
		                                      // Since Java doesn't know how to handle SQLRowSet data we need to copy the data into an object
			                                  //       so we can use the data in a Java program - typically a method is used to do this
			                                  //       since we most likely need to create an object from SQLRowSet data more than once
		}
		
		return theCity;     // return the City object created from the row in the data base
	}

	@Override
	public List<City> findCityByCountryCode(String countryCode) {   // Return a List of City object for country code passed in by user

		ArrayList<City> cities = new ArrayList<>();   // Define and instantiate a ArrayList of city objects to return

		// Define a String to hold the SQL SELECT we want to run with placeholders fro value not until this method is called
		String sqlFindCityByCountryCode = "SELECT id, name, countrycode, district, population "+
										   "FROM city "+
										   "WHERE countrycode = ?";   // country code is passed in by this, so we code a placeholder for it
		
		// Execute the SQL statement with value(s)/variable(s) for any placeholders in the statement
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityByCountryCode, countryCode);  // include the countryCode passed bu user

		// Since multiple rows may be returned by the SELECT, we use a loop to process the SQLRowSet	
		while(results.next()) {                   // Loop while there are rows in the SQLRowSet and position at each row one at a time
			City theCity = mapRowToCity(results); //      Assign the City object containing the data from the current row
			cities.add(theCity);                  //      Add the City object to our ArrayList of City objects
		}
		return cities;       // return the list of City object
	}

	@Override
	public List<City> findCityByDistrict(String districtWanted) {
		
		ArrayList<City> listOfCities = new ArrayList<City>();  // Define and instantiate the object to be returned
		// We only need the name column from the table, but if we want to use the existing MapRowToCity method 
		//    we need to include all the columns its expects
		//    or write another method to only expects the name column in the result
		//
		// Returning an incomplete object to a user is consider a bad practice
		// So it's better to get all the columns all the time and have one map method that creates a complete object
		
		String SQLFindCitiesByDistrict = "SELECT id, name, countrycode, district, population "    +
				                         "   from city "             +
				                         "  where district = ?";
		
		// Execute the SQL statement providing the parameter passed in by teh user for placeholder in the statement
		SqlRowSet citiesFromTable = jdbcTemplate.queryForRowSet(SQLFindCitiesByDistrict, districtWanted);
		
		while(citiesFromTable.next()) {                   // Loop while there are rows in the SQLRowSet and position at each row one at a time
			City theCity = mapRowToCity(citiesFromTable); //      Assign the City object containing the data from the current row
			listOfCities.add(theCity);                    //      Add the City object to our ArrayList of City objects
		}
		
		return listOfCities;
	}

	@Override
	public void update(City city) {
		String sql = "UPDATE city set name = ?, countrycode = ?, district = ?, population = ? WHERE id = ?";
		jdbcTemplate.update(sql,city.getName(),city.getCountryCode(),city.getDistrict(),city.getPopulation(),city.getId());
	}

	@Override
	public void delete(long id) {
		
		String deleteACity = "delete from city where id = ?";
		
		// Execute the DELETE SQL statement using the .upadte() method
		// Since update doesn't return anything, no need for SQLRowSet object or to process results
		
		jdbcTemplate.update(deleteACity, id);
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
		// Since we know only one row will be returned by the SELECT, we don't need a loop to process the SQLRowSet
		
		if(nextIdResult.next()) {               // if the SQLRowSet called nextIDResult has any data, move to the next line of data
			return nextIdResult.getLong(1);     // get the long value in column 1 of the line and return it
		} else {                                // if there is no data in nextIDResult, throw a RuntimeException
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}
	
	// Since Java doesn't know how to handle SQLRowSet data we need to copy the data into an object
    //       so we can use the data in a Java program - typically a method is used to do this
    //       since we most likely need to create an object from SQLRowSet data more than once
	
	// This method will copy data from SQLRowSet passed in as a parameter to a City object and returns the City object
	// This method is expecting to have the id, name, countrycode, district, population columns in the result
	//             if any of those columns are missing, this method will get an "invalid column" error
	private City mapRowToCity(SqlRowSet results) {

		City theCity;         // Define a reference to City object to return

		theCity = new City(); // Instantiate a City object assigning it to the reference earlier
		
		// Copy the data from the SQLRowSet using it's getXXXXX methods into the City object using it's setters
		// Note:  You must know the data type of the column data in order to code the correct get method for the SQLRowSet
		theCity.setId(results.getLong("id"));                    // set the id in the City object to the id column in the SQLRowSet
		theCity.setName(results.getString("name"));              // set the name in the City object to the name column in the SQLRowSet
		theCity.setCountryCode(results.getString("countrycode"));// set the countrycode in the City object to the countrycode column in the SQLRowSet
		theCity.setDistrict(results.getString("district"));      // set the district in the City object to the district column in the SQLRowSet
		theCity.setPopulation(results.getInt("population"));     // set the population in the City object to the population column in the SQLRowSet
		
		return theCity;     // return the City object created in this method
	}
}
