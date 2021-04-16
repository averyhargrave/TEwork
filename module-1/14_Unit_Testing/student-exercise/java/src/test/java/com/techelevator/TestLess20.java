package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestLess20 {

	Less20 testCode = new Less20();
	
	@Test
	public void return_true_when_one_or_two_less_than_multiple_of_twenty() {
		assertFalse("not 1 or 2 less than a multiple of 20", testCode.isLessThanMultipleOf20(25));
		assertFalse("not 1 or 2 less than a multiple of 20", testCode.isLessThanMultipleOf20(69));
		assertFalse("not 1 or 2 less than a multiple of 20", testCode.isLessThanMultipleOf20(80));
		assertTrue("1 or 2 less than a multiple of 20", testCode.isLessThanMultipleOf20(79));
		assertTrue("1 or 2 less than a multiple of 20", testCode.isLessThanMultipleOf20(38));
	}
	
	@After
	public void tearDown() throws Exception {
	}

	

}
