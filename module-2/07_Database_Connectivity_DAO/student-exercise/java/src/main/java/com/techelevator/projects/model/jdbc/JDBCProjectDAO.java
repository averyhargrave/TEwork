package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
			
		ArrayList<Project> listOfProjects = new ArrayList<>();
		
		String getAllProjects = "SELECT project_id, name, from_date, to_date " +
								"FROM project ";
		
		SqlRowSet allProjects = jdbcTemplate.queryForRowSet(getAllProjects);
		
		while(allProjects.next()) {
			Project aProject = mapRowToProject(allProjects);
			listOfProjects.add(aProject);
		}
		return listOfProjects;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		
		String removeEmployee = "DELETE " +
								"FROM project_employee " +
							    "WHERE project_id = ? " +
							    "AND employee_id = ? ";

		jdbcTemplate.update(removeEmployee, projectId, employeeId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		
		String addEmployeeToProject = "INSERT INTO project_employee(project_id, employee_id) " +
									  "VALUES(?,?) ";
		
		jdbcTemplate.update(addEmployeeToProject, projectId, employeeId);
	}

	private Project mapRowToProject(SqlRowSet results) {
		Project theProject;
		theProject = new Project();
		
		theProject.setId(results.getLong("project_id"));
		theProject.setName(results.getString("name"));
		
		if(results.getDate("from_date") != null) {
		theProject.setFromDate(results.getDate("from_date").toLocalDate());
		}
		if(results.getDate("to_date") != null) {
		theProject.setToDate(results.getDate("to_date").toLocalDate());
		}
		return theProject;
	}
	
}
