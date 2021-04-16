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
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJDBCDepartmentDAO {

/************************************************************************************************************************************************************************
 *  Setup
 ************************************************************************************************************************************************************************/	
	
	private long id = 123L;            
	private String departmentName = "some sorta dept";
	
	private static SingleConnectionDataSource dataSource;
	private JDBCDepartmentDAO deptDAO;
	
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
		String sqlInsertDept = "INSERT INTO department "
								+ " (name) "
								+ " VALUES(?)";
		JdbcTemplate database = new JdbcTemplate(dataSource);
		database.update(sqlInsertDept, departmentName);
	
		deptDAO = new JDBCDepartmentDAO(dataSource); 
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
/*************************************************************************************************************************************************************************
 * 	Testing
 *************************************************************************************************************************************************************************/	
	
	@Test
	public void test_create_department() {
		
		Department testDept = new Department();
		testDept.setName("Paradise");
		
		Department returnedDept = deptDAO.createDepartment(testDept);
		
		assertNotNull(returnedDept);
		assertDepartmentsAreEqual(testDept, returnedDept);
	}
	
	@Test
	public void test_saveDepartment() {
		
		Department theDept = testDepartment();
		
		String newDepartmentName = "New Department Name";
		
		theDept.setName(newDepartmentName);
		
		Department updatedDepartment = theDept;
		
		deptDAO.saveDepartment(updatedDepartment);
		
		assertEquals(updatedDepartment.getName(), deptDAO.searchDepartmentsByName(newDepartmentName).get(0).getName());
	}

	
	
	
/*************************************************************************************************************************************************************************
 * Helper Methods
 *************************************************************************************************************************************************************************/
	
	private Department testDepartment() {
		
		Department aDept = new Department();
		
		aDept.setName(departmentName);
		
		return aDept;
	}
	
	public void assertDepartmentsAreEqual(Department expected, Department actual) {
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getId(),   actual.getId());
	}
	
}
