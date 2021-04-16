package com.techelevator;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.city.City;
import com.techelevator.city.CityDAO;
import com.techelevator.city.JDBCCityDAO;

// This is the application program to use DAO to access the database

public class DAOExample {

	public static void main(String[] args) {
	
		BasicDataSource worldDataSource = new BasicDataSource();
		worldDataSource.setUrl("jdbc:postgresql://localhost:5432/world");
		worldDataSource.setUsername("postgres");
		worldDataSource.setPassword("postgres1");
		// pass the CityDAO ctor and the data-source we want it to use
		CityDAO dao = new JDBCCityDAO(worldDataSource); // Instantiate a CityDAO object so we can use its methods
		
		City smallville = new City();     // Instantiate a new, empty City object
		smallville.setCountryCode("USA"); // Assign values
		smallville.setDistrict("KS");     //     to the new City object
		smallville.setName("Smallville"); //     using its setters
		smallville.setPopulation(42080); 
		// NOTE: We did not assign a city_id because it comes from the database manager
		
		dao.save(smallville);
		
		City theCity = dao.findCityById(smallville.getId());
		
	}

}
