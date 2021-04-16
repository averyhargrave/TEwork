package com.techelevator.city;

/*
 * Integration testing - Testing to ensure your code interacts with external processes correctly
 * You want to be sure your code doesn't break when working with external code
 *
 * Unit Testing - ensures that your class methods work as intended within the context of the application program
 * 
 * When using DAOs, we are interacting with an external resource/process called a database manager
 * We need to verify that any INSERT, UPDATE, or DELETE we did was done correctly in the DBM
 * 
 * You can use the DAO interface to determine which methods you need to test
 * 
 * We will use JUnit with some additional techniques and methods
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCCityDAOIntegrationTest {

	// Define data with values you know will not and cannot be in the database for testing
	
	private static final String TEST_COUNTRY = "XYZ"; // We know that there is no countrycode 'XYZ' in the database
													  // So when we need a countrycode for a test, we'll use this constant

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction 
	 * 
	 * Previously we used a BasicDataSource
	 * 
	 * We use the SingleConnectionDataSource due to its efficiencies over the BasicDataSource
	 *        and to give you an example of another data source
	 *        
	 * Both data source classes have the same constructor format and methods
	 * 
	 */
	private static SingleConnectionDataSource dataSource; // Define a reference to the data source
														  //    the datasource object is instantiated and assigned in a setup method
	private JDBCCityDAO dao;							  // Define a reference to the DAO
														  //    the DAO is instantiated and assigned before use
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass // Tells JUnit to do this before once before it does anything else
				 // Put any setup that all tests need to be done once before they start
				 // When testing DAOs, instantiating and assigning the datasource is a common process in a @BeforeClass
	public static void setupDataSource() { 							 // name of the JUnit test method
		dataSource = new SingleConnectionDataSource(); 				 // Instantiating and assigning the datasource to our reference
		dataSource.setUrl("jdbc:postgresql://localhost:5432/world"); // Set the connection String for the datasource
		dataSource.setUsername("postgres"); 						 // Set the database owner of the database we want to use
		dataSource.setPassword("postgres1"); 						 // Set the password of the database we want to use
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false); // Tells the datasource NOT to automatically commit after each interaction
	}									 //       because we want to control the commit/rollback
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass // Tells JUnit to do this once at the end of all processing before you end the testing session
				// Usually this is where you disconnect from the DBM to avoid resource leaks by leaving a connection open
	public static void closeDataSource() throws SQLException {
		dataSource.destroy(); // Disconnect the datasource from the DBM
	}

	// Do not use "live" data in the database for your tests - it might change between tests - tests must be reliable
	// Add data to the database specifically for testing purposes so you KNOW the data is there and won't change
	// Add data with values you KNOW are not or cannot be in the database already
	// Rollback any test data you added when the test completes
	
	@Before // Tells JUnit to do this before every JUnit test - setup the database with data to be used in the test
	public void setup() {
		// Add a row to the country table with the TEST_COUNTRY value so we can use the row in our tests
		String sqlInsertCountry = "INSERT INTO country "
								+ " (code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2) "
								+ " VALUES (?, 'Afghanistan', 'Asia', 'Southern and Central Asia', 652090, 1919, 22720000, 45.9000015, 5976.00, NULL, 'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 1, 'AF') ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
		
		// Instantiate an object for the DAO containing the methods we want to test
		// We do this before each test so we get a clean, unused JDBC/DAO for each test
		// If we share the same JDBC/DAO with all tests, the values may differ due to values left in the JDBC/DAO after use
		dao = new JDBCCityDAO(dataSource); 
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After // Tells JUnit to do this after every JUnit test - rollback any data added for the tests
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
// This is the start of our testing
//-----------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Test // Test the save method in the DAO
	public void save_new_city_and_read_it_back() throws SQLException {
		// Arrange - setup data needed for the test
		City theCity = getCity("SQL Station", "South Dakota", "USA", 65535); // Instantiate a City object using a common method
																			 // 	defined near the end of the	testing class
		// Act - perform the process / call the method you are testing
		dao.save(theCity); // Calling the DAO save method with the new city object we created for the test
		
		// Assert - verify that the process or method did what it was supposed to do
		City savedCity = dao.findCityById(theCity.getId()); // Get the row just added to the database with
															// 	  the id in the object used to test the method
		
		assertNotEquals(null, theCity.getId());   // Be sure the id added was not null
		assertCitiesAreEqual(theCity, savedCity); // Be sure the data retrieved from the database matches the data we sent
												  // 	  using a helper method
	}

	@Test
	public void returns_cities_by_country_code() {
		City theCity = getCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535);

		dao.save(theCity);
		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);

		assertNotNull(results);
		assertEquals(1, results.size());
		City savedCity = results.get(0);
		assertCitiesAreEqual(theCity, savedCity);
	}

	@Test
	public void returns_multiple_cities_by_country_code() {
	

		dao.save(getCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535));
		dao.save(getCity("Postgres Point", "North Dakota", TEST_COUNTRY, 65535));

		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);

		assertNotNull(results);
		assertEquals(2, results.size());
	}

	@Test
	public void returns_cities_by_district() {
		String testDistrict = "Tech Elevator";
		City theCity = getCity("SQL Station", testDistrict, TEST_COUNTRY, 65535);
		dao.save(theCity);

		List<City> results = dao.findCityByDistrict(testDistrict);

		assertNotNull(results);
		assertEquals(1, results.size());
		City savedCity = results.get(0);
		assertCitiesAreEqual(theCity, savedCity);
	}

/**********************************************************************************************************************
 * Helper methods used to setup data for the tests - commonly needed processes
 * 
 * These methods aren't testing any DAO methods - they are helping the tests with needed processing
 **********************************************************************************************************************/
	
	// Helper method to create a new City object when a test needs one
	private City getCity(String name, String district, String countryCode, int population) {
		City theCity = new City();
		theCity.setName(name);
		theCity.setDistrict(district);
		theCity.setCountryCode(countryCode);
		theCity.setPopulation(population);
		return theCity;
	}

	// Helper method to test if two City objects are equal
	private void assertCitiesAreEqual(City expected, City actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDistrict(), actual.getDistrict());
		assertEquals(expected.getCountryCode(), actual.getCountryCode());
		assertEquals(expected.getPopulation(), actual.getPopulation());
	}
}
