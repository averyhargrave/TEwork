package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestMaxEnd3 {

	MaxEnd3 testCode = new MaxEnd3();
	
	@Test
	public void returns_new_array_made_of_only_larger_of_first_and_last_indexes() {
		assertArrayEquals(new int[] {9, 9, 9}, testCode.makeArray(new int[] {3, 6, 9}));
		assertArrayEquals(new int[] {13, 13, 13}, testCode.makeArray(new int[] {12, 24, 13}));
		assertArrayEquals(new int[] {32, 32, 32}, testCode.makeArray(new int[] {32, 31, 30}));
	}

}
