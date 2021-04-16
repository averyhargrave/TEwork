package com.techelevator;

import java.util.ArrayList;

// This is application program to used DAO to access the database

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.city.City;
import com.techelevator.city.CityDAO;
import com.techelevator.city.JDBCCityDAO;

public class DAOExample {

	public static void main(String[] args) {
	
		BasicDataSource worldDataSource = new BasicDataSource();
		worldDataSource.setUrl("jdbc:postgresql://localhost:5432/world");
		worldDataSource.setUsername("postgres");
		worldDataSource.setPassword("postgres1");
		// pass the cityDAO constructor the data source we want it to use
		CityDAO dao = new JDBCCityDAO(worldDataSource);   // Instantiate a CityDAO object so we can use it's methods
		
		City smallville = new City();      // Instantiate a new, empty City object
		smallville.setCountryCode("USA");  //    Assign values
		smallville.setDistrict("Ohio");      //      to the new City object
		smallville.setName("Gotham");  //         using it's setters
		smallville.setPopulation(42080);
		// NOTE: we did not a city_id because it comes from the data base manager
		System.out.println("      City id send to the DAO is: " + smallville.getId());
		
		dao.save(smallville);  // Use the DAO save method to add the new City to the database
		
		System.out.println("      City id coming back DAO is: " + smallville.getId());
		
		City theCity = dao.findCityById(smallville.getId());   // Retrieve the City just added to data base for verification
		
		System.out.println("City id saved in the database is: " + theCity.getId());
		
		// Get a list of cities in the USA from the database
		// We need to cast the return object from the findCityByCountryCode() method 
		//    because it returns a List<City> and we need an ArrayList<City>
		
		ArrayList<City> listOfCities =  (ArrayList<City>) dao.findCityByCountryCode("USA"); 
		
		// Display the names for the cities returned from the database
		for (City aCity : listOfCities) {
			System.out.println("a USA city in the database is: " + aCity.getName());
		}
		
		listOfCities =  (ArrayList<City>) dao.findCityByDistrict("Ohio"); 
		
		// Display the names for the cities returned from the database
		for (City aCity : listOfCities) {
			System.out.println("a city in Ohio from the database is: " + aCity.getName());
		}
		
		// Delete the City added by this program from table (it's stored in theCity object
		dao.delete(theCity.getId());
		
		listOfCities =  (ArrayList<City>) dao.findCityByDistrict("Ohio"); 
		
		// Display the names for the cities returned from the database
		for (City aCity : listOfCities) {
			System.out.println("a city in Ohio from the database is: " + aCity.getName());
		}
	}

}
