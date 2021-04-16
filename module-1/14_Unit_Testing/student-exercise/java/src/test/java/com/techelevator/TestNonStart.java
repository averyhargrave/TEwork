package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestNonStart {
	
	NonStart testCode = new NonStart();
	
	@Test
	public void test_combined_Strings_lacking_first_char() {
		assertEquals("Didn't omit first chars", "ellohere", testCode.getPartialString("Hello", "There"));
		assertEquals("Didn't omit first chars", "ountainew", testCode.getPartialString("Mountain", "Dew"));
		assertEquals("Didn't omit first chars", "echlevator", testCode.getPartialString("Tech", "Elevator"));
		assertEquals("Didn't omit first chars", "rank'slass", testCode.getPartialString("Frank's", "Class"));
	}
	
	@After
	public void tearDown() throws Exception {
	}


}
