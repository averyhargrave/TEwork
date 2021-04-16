package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestSameFirstLast {

	SameFirstLast testCode = new SameFirstLast();
	
	@Test
	public void return_true_if_first_and_last_elements_equal() {
		assertTrue("First and last elements not equal", testCode.isItTheSame(new int[] {3, 1 , 3}));
		assertTrue("First and last elements not equal", testCode.isItTheSame(new int[] {9, 5, 9}));
		assertFalse("First and last elements equal", testCode.isItTheSame(new int[] {4, 5, 6}));
		assertFalse("First and last elements equal", testCode.isItTheSame(new int[] {12, 60, 9}));
	}

}
