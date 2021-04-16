package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAnimalGroupName {
	
// First we need to define an object with the methods we want to test
	
	AnimalGroupName testCode = new AnimalGroupName(); // Object with the methods we want to test
	
	@Test // Here's the JUnit test
	public void test_correct_animal_group_name_returned_based_on_animal() { // JUnit test method, public void name(no parameters) {}
	// the method in the class we are testing are: 
	//     String getHerd(String animalName)
	// the method returns a String - assertEquals
	// test 1 - send it "Elephant", expect "Herd"
	// test 2 - send it "elephant", expect "Herd"
	// test 3 - send it "ELEPHANT", expect "Herd"
	// test 4 - send it "elEPhANt", expect "Herd"
	// test 5 - send it "Walrus", expect "unknown"
		
		assertEquals("Wrong animal group returned", "Herd", testCode.getHerd("Elephant"));
		assertEquals("Wrong animal group returned", "Herd", testCode.getHerd("elephant"));
		assertEquals("Wrong animal group returned", "Herd", testCode.getHerd("ELEPHANT"));
		assertEquals("Wrong animal group returned", "Herd", testCode.getHerd("elEPhANt"));
		assertEquals("Wrong animal group returned", "unknown", testCode.getHerd("Walrus"));
		
	}

}
