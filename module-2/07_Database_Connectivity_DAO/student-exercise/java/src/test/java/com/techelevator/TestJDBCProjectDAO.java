package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJDBCProjectDAO {

/**************************************************************************************************************
 *  Setup
 **************************************************************************************************************/
		
	private long id = 69666420L;
	private String name = "Creating The World's Finest Meatballs";
	private LocalDate fromDate = LocalDate.of(1984, 04, 20);
	private LocalDate toDate = LocalDate.of(2020, 06, 9);   

	private static SingleConnectionDataSource dataSource;     // define a reference to the data source
	private JDBCProjectDAO projectDAO;
		
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projectsDAO");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	    public static void closeDataSource() throws SQLException {
	     dataSource.destroy();
	
	}
	
	@Before
	public void setup() {
		String sqlInsertProject = "INSERT INTO project "
								+ " (name, from_date, to_date ) "
								+ " VALUES(?,?,?)";
		JdbcTemplate database = new JdbcTemplate(dataSource);
		database.update(sqlInsertProject, name, fromDate, toDate);
	
		projectDAO = new JDBCProjectDAO(dataSource); 
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
/*************************************************************************************************************************************************************************
 * 	Testing
 *************************************************************************************************************************************************************************/
		
	@Test

	
	
	
/*************************************************************************************************************************************************************************
 * Helper Methods
 *************************************************************************************************************************************************************************/
		
	private Project testProject() {
			
		Project aProject = new Project();
		
		aProject.setName(name);
		aProject.setFromDate(fromDate);
		aProject.setToDate(toDate);
			
		return aProject;
	}
		
	private void assertProjectsAreEqual(Project expected, Project actual) {			
		assertEquals(expected.getName(),     actual.getName());
		assertEquals(expected.getId(),       actual.getId());
		assertEquals(expected.getFromDate(), actual.getFromDate());
		assertEquals(expected.getToDate(),   actual.getToDate());
	}
	
}


