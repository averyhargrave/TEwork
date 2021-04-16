package com.techelevator.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Department;
import com.techelevator.model.DepartmentDAO;


/**
 * Controller to handle API requests for the Department resources
 */

@RestController // Tells the server this file contains controllers
public class DepartmentController {
	
	DepartmentDAO deptDAO; // Define a reference so we can access DepartmentDAO's methods
	
	// Constructor for this class - public, same name as class
	// Use Spring dependency injection (DI) to instantiate a DepartmentDAO object and pass it to the constructor
	// We need to include the @Component annotation in the DepartmentDAO so Spring knows it's a candidate for DI 
	
	public DepartmentController(DepartmentDAO deptDAO) { // receive the DepartmentDAO object as a parameter
		this.deptDAO = deptDAO; // assign to our reference the DepartmentDAO passed from the constructor
	}
	
	// This controller will handle the path "/departments"
	// to send back all the Departments
	
	@RequestMapping(path="/departments", method=RequestMethod.GET)
	public List<Department> getAllDepartments() {
		logAPICall("Called with the path: /departments"); // Log the API call to the server
		// Use the Department DAO to get all the Departments from the database and return them
		List<Department> theDepts;
		theDepts = deptDAO.getAllDepartments(); // Use the DAO method to retrieve all departments from the resource
		// In this method, we did everything one step at a time (I prefer the next method)
		return theDepts;
	}
	
	// {id} indicates that a value will be included in the path and we want to call it 'id'
	// We use the @PathVariable in the method parameter list to retrieve the value from the path
	// A user will call this to get department #3: /departments/3
	@RequestMapping(path="/departments/{id}", method=RequestMethod.GET)
	public Department getDeptById(@PathVariable Long id) {
		logAPICall("Called with the path: /departments/"+id);
		// In this method, we combined the return with the method call
		return deptDAO.getDepartmentById(id);
	}

	public void logAPICall(String message) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy HH:mm:ss.A");
		String timeNow = now.format(formatter);
		System.out.println(timeNow + "-" + message);
	}
}


