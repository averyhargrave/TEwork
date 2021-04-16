package com.techelevator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

public class TestWordCount {

	WordCount testCode = new WordCount(); // always have to define an object with the methods to be tested
	
	// Map<String, Integer> getCount(String[] words)
	
	@Test
	public void test_getCount_to_return_the_correct_map() {
	// Arrange - define a String array to be used in the test, there need to be duplicates
	String[] stringArray = {"Jeff", "Aiden", "Avery", "Sam", "Sam", "Avery"};
	
	// Arrange - Define a Map with my expected result
	Map<String, Integer> nameCount = new HashMap<>();
		nameCount.put("Jeff", 1);
		nameCount.put("Aiden", 1);
		nameCount.put("Avery", 2);
		nameCount.put("Sam", 2);
	
	// Run the method to be tested and check it returns what we expect
	assertEquals(nameCount, testCode.getCount(stringArray));
	
	}

}
