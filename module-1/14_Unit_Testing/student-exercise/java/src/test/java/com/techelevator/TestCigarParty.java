package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCigarParty {

	CigarParty testCode = new CigarParty();
	
	@Test
	public void test_if_party_successful_based_on_cigar_count() {
		
		assertFalse("Cigar count out of range", testCode.haveParty(25, false));
		assertFalse("Cigar count out of range", testCode.haveParty(70, false));
		assertFalse("Cigar count out of range", testCode.haveParty(20, true));
		assertTrue("Success!", testCode.haveParty(70, true));
		assertTrue("Success!", testCode.haveParty(50, false));
		
	}
	
}
