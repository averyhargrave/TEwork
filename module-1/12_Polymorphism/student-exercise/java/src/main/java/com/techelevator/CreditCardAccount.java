package com.techelevator;

/**
 * CreditCardAccount
 * 
 * CreditCardAccount is a type of Accountable class
 */
public class CreditCardAccount implements Accountable {
	
/*******************************************************************
 * Place the code for  your CreditCard class here - data members
 *******************************************************************/
	private String accountHolder;
	private String accountNumber;
	private int debt;

    public String getAccountHolder() {
		return accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public int getDebt() {
		return debt;
	}

	public CreditCardAccount (String accountHolder, String accountNumber) {
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
	}
	
	public int pay(int amtToPay) {
		debt -= amtToPay;
		return debt;
	}
	
	public int charge(int amtToCharge) {
		debt += amtToCharge;
		return debt;
	}
	
	public int getBalance() {
    	if(debt >= 0) {
    		debt = 0 - debt;
    	}
        return debt;
    }

 

}