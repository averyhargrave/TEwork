package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDateFashion {

	DateFashion testCode = new DateFashion();
	
	@Test 
	public void test_table_reservation_based_on_fashion() {
		assertEquals("You got a table!", 2, testCode.getATable(8, 4));
		assertEquals("You got a table!", 2, testCode.getATable(3, 9));
		assertEquals("You might get a table.", 1, testCode.getATable(5, 4));
		assertEquals("You might get a table.", 1, testCode.getATable(7, 3));
		assertEquals("In your dreams.", 0, testCode.getATable(10, 1));
		assertEquals("In your dreams.", 0, testCode.getATable(2, 6));
		
		
	}
	

}
