package com.techelevator;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Employee;

import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJDBCEmployeeDAO {
	
/**************************************************************************************************************
 *  Setup
 **************************************************************************************************************/
	
	private String firstName = "FranKey";
	private String lastName = "MtnDewKing";
	private LocalDate birthDate = LocalDate.of(1984, 04, 20);
	private String gender = "M";
	private LocalDate hireDate = LocalDate.of(2020, 06, 9);
	private Long departmentId = 1L;
	private Long employeeId = 54321L;
	
	private static SingleConnectionDataSource dataSource;     // define a reference to the data source
	private JDBCEmployeeDAO employeeDAO;
	
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
		String sqlInsertEmployee = "INSERT INTO employee "
								+ " (department_id, first_name, last_name, birth_date, gender, hire_date) "
								+ " VALUES(?,?,?,?,?,?)";
		JdbcTemplate database = new JdbcTemplate(dataSource);
		database.update(sqlInsertEmployee, departmentId, firstName, lastName, birthDate, gender, hireDate);
	
		employeeDAO = new JDBCEmployeeDAO(dataSource); 
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
/*************************************************************************************************************************************************************************
 * 	Testing
 *************************************************************************************************************************************************************************/
	
	@Test
	public void test_search_Employees_By_Name() {
		
		Employee anEmployee = testEmployee();
		
		List<Employee> results = employeeDAO.searchEmployeesByName(anEmployee.getFirstName(), anEmployee.getLastName());
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals(results.get(0).getFirstName(), anEmployee.getFirstName());
		assertEquals(results.get(0).getLastName(),  anEmployee.getLastName());
	}
	
	
	
	
	
	
/**************************************************************************************************************************************************************************
 *  Helper Methods
 **************************************************************************************************************************************************************************/
	
	private Employee testEmployee() {
		
		Employee anEmployee = new Employee();
		
		anEmployee.setFirstName(firstName);
		anEmployee.setLastName(lastName);
		anEmployee.setBirthDate(birthDate);
		anEmployee.setGender(gender);
		anEmployee.setHireDate(hireDate);
		anEmployee.setDepartmentId(departmentId);
		
		return anEmployee;
	}
	
	private void assertEmployeesAreEqual(Employee expected, Employee actual) {
		assertEquals(expected.getFirstName(),    actual.getFirstName());
		assertEquals(expected.getLastName(),     actual.getLastName());
		assertEquals(expected.getBirthDate(),    actual.getBirthDate());
		assertEquals(expected.getGender(),       actual.getGender());
		assertEquals(expected.getHireDate(),     actual.getHireDate());
		assertEquals(expected.getDepartmentId(), actual.getDepartmentId());
		assertEquals(expected.getEmployeeId(),   actual.getEmployeeId());
	}
	
}