package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		
		ArrayList<Department> listOfDepts = new ArrayList<>();
		
		String sqlGetAllDepts = "SELECT department_id, name " +
								"FROM department ";
		
		SqlRowSet allDepts = jdbcTemplate.queryForRowSet(sqlGetAllDepts);
		
		while(allDepts.next()) {
			Department theDept = mapRowToDept(allDepts);
			listOfDepts.add(theDept);
		}
		
		return listOfDepts;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		
		ArrayList<Department> listOfDepts = new ArrayList<>();
		
		String searchDeptNameSql = "SELECT department_id, name " +
								   "FROM department " +
								   "WHERE name ilike ?";
		
		SqlRowSet theDepts = jdbcTemplate.queryForRowSet(searchDeptNameSql, "%" + nameSearch + "%");
		
		if(theDepts.next()) {
			Department aDepartment = new Department();
			aDepartment = mapRowToDept(theDepts);
			listOfDepts.add(aDepartment);
		}
		
		return listOfDepts;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		String updateDept = "UPDATE department " +
							"SET name = ?" +
							"WHERE department_id = ?";
	
		jdbcTemplate.update(updateDept, updatedDepartment.getName(), updatedDepartment.getId());
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sqlInsertDepartment = "INSERT INTO department(department_id, name) " +
				   					 "VALUES(?, ?)"; 
                                        
		newDepartment.setId(getNextDeptId()); 

		jdbcTemplate.update(sqlInsertDepartment, newDepartment.getId(), 
												 newDepartment.getName());
		return newDepartment;
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department aDept = new Department();
		
		String sqlGetDeptById = "SELECT department_id, name " +
								"FROM department " +
								"WHERE department_id = ?";
		
		SqlRowSet DeptById = jdbcTemplate.queryForRowSet(sqlGetDeptById, id);
		
		aDept = mapRowToDept(DeptById);
		
		return aDept;
	}
	
	private Department mapRowToDept(SqlRowSet results) {
		Department theDept;
		theDept = new Department();
		theDept.setId(results.getLong("department_id"));
		theDept.setName(results.getString("name"));
		return theDept;
	}
	
	private long getNextDeptId() {
		
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_department_id')");
		
		if(nextIdResult.next()) {               
			return nextIdResult.getLong(1);     
		} else {                                
			throw new RuntimeException("Something went wrong while getting an id for the new department");
		}
	}
}
