package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class FlowerShopTest {

	FlowerShop testCode = new FlowerShop(null, 0);

	@SuppressWarnings("deprecation")
	@Test
	public void check_if_delivery_fee_correct() {
	assertEquals("Incorrect delivery fee calculation.", 5.99, testCode.sameDayDelivery(true, "10001"));
	assertEquals("Incorrect delivery fee calculation.", 0.0, testCode.sameDayDelivery(false, "10001"));
	assertEquals("Incorrect delivery fee calculation.", (3.99 + 5.99), testCode.sameDayDelivery(true, "20050"));
	assertEquals("Incorrect delivery fee calculation.", 3.99, testCode.sameDayDelivery(false, "20050"));
	assertEquals("Incorrect delivery fee calculation.", (6.99 + 5.99), testCode.sameDayDelivery(true, "34020"));
	assertEquals("Incorrect delivery fee calculation.", 6.99, testCode.sameDayDelivery(false, "34020"));
	assertEquals("Incorrect delivery fee calculation.", 19.99, testCode.sameDayDelivery(true, "54020"));
	assertEquals("Incorrect delivery fee calculation.", 19.99, testCode.sameDayDelivery(false, "54020"));
	
	}
}
