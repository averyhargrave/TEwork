package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestStringBits {

	StringBits testCode = new StringBits();
	
	@Test
	public void test_String_returned_is_every_other_char_starting_at_first() {
		assertEquals("Not every other char", "MutiDw", testCode.getBits("MountainDew"));
		assertEquals("Not every other char", "Fakel", testCode.getBits("FrankFella"));
		assertEquals("Not every other char", "Jsnetal", testCode.getBits("JasonMeatballs"));
		
	}

}
