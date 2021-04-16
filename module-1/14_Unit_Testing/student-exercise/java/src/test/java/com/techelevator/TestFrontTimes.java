package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFrontTimes {

	FrontTimes testCode = new FrontTimes();
	
	@Test
	public void test_if_returns_three_chars_at_start_correct_amount_of_times() {
		
		assertEquals("Should equal ChoChoCho", "ChoChoCho", testCode.generateString("Chocolate", 3));
		assertEquals("Should equal DenDenDen", "DenDenDen", testCode.generateString("Denver", 3));
		assertEquals("Should equal MtnMtn", "MtnMtn", testCode.generateString("MtnDew", 2));
		assertEquals("Should equal FraFraFraFraFra", "FraFraFraFraFra", testCode.generateString("Frank", 5));
		
	}

}
