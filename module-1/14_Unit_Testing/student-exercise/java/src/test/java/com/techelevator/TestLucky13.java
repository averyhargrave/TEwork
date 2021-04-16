package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestLucky13 {

	Lucky13 testCode = new Lucky13();
	
	@Test
	public void fails_if_array_contains_one_or_three() {
		assertFalse("Has 1 or 3", testCode.getLucky(new int[] {1, 6, 9}));
		assertFalse("Has 1 or 3", testCode.getLucky(new int[] {3, 4, 2, 0}));
		assertTrue("Has no 1 or 3", testCode.getLucky(new int[] {4, 6, 9}));
		assertTrue("Has no 1 or 3", testCode.getLucky(new int[] {5, 8, 12}));
	}
	
}
