package com.techelevator.city;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCityDAO implements CityDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCityDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(City newCity) {
		// Since all the table columns are required, we need to be sure that all variables in the City object
		// passed to us are not null before we try to INSERT the data into the data base
		// If any of the values in the City object passed to us are null - throw an exception with a message
		// Since an int can't be null, we don't check the population value in the City object
		if  (newCity.getName() == null 
		  || newCity.getCountryCode() == null 
		  || newCity.getDistrict() == null) {
			                                  throw new RuntimeException("at least on value in the City object is null");
			                                  
		}
			
		String sqlInsertCity = "INSERT INTO city(id, name, countrycode, district, population) "
				+ "VALUES(?, ?, ?, ?, ?)";
		newCity.setId(getNextCityId());
		jdbcTemplate.update(sqlInsertCity, newCity.getId(), newCity.getName(), newCity.getCountryCode(),
				newCity.getDistrict(), newCity.getPopulation());
	}

	@Override
	public City findCityById(long id) {
		City theCity = null;
		String sqlFindCityById = "SELECT id, name, countrycode, district, population " + "FROM city " + "WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityById, id);
		if (results.next()) {
			theCity = mapRowToCity(results);
		}
		return theCity;
	}

	@Override
	public List<City> findCityByCountryCode(String countryCode) {
		ArrayList<City> cities = new ArrayList<>();
		String sqlFindCityByCountryCode = "SELECT id, name, countrycode, district, population " + "FROM city "
				+ "WHERE countrycode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityByCountryCode, countryCode);
		while (results.next()) {
			City theCity = mapRowToCity(results);
			cities.add(theCity);
		}
		return cities;
	}

	@Override
	public List<City> findCityByDistrict(String district) {
		ArrayList<City> cities = new ArrayList<>();
		String sqlFindCityByCountryCode = "SELECT id, name, countrycode, district, population " + "FROM city "
				+ "WHERE district = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityByCountryCode, district);
		while (results.next()) {
			City theCity = mapRowToCity(results);
			cities.add(theCity);
		}
		return cities;
	}

	@Override
	public void update(City city) {
		String sql = "UPDATE city set name = ?, countrycode = ?, district = ?, population = ? WHERE id = ?";
		jdbcTemplate.update(sql,city.getName(),city.getCountryCode(),city.getDistrict(),city.getPopulation(),city.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM city WHERE id = ?",id);
	}

	private long getNextCityId() {
		// Get the next value for city_id from the data base manager
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_city_id')");
	
		if (nextIdResult.next()) {            // if a value was returned from the data base managet
			return nextIdResult.getLong(1);   // retrieve from SQLRowSet result and return to caller
		} else {      // if no value was returned - we have not value for the city id - that's not good for the application
			throw new RuntimeException("Something went wrong while getting an id for the new city");
			// Whenever there is an application error condition we can either (1) ignore it, (2) display an error message
			//                                  and continue execution, (3) display an error message and terminate the
			//                                  program or (4) throw an exception which will terminate the program with
			//                                  the stack trace and error messages generated the system
			// We chose to throw an exception this time because we want the stack trace displayed so we can how got to the error
			//
			// RuntimeException is the super class of all exceptions - a generic exception
			//
			// Throw a RuntimeException if you don't have a custom exceptions or system exception for what is wrong
			//
			// When you throw an exception you may specify a message to be included with system output displayed for the exception
			//      to indicate what application error and why you threw the exceptions
		
		
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
