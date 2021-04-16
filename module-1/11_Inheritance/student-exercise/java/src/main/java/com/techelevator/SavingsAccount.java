package com.techelevator;

public class SavingsAccount extends BankAccount {
	
	public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
	}
		
		public SavingsAccount (String accountHolderName, String accountNumber) {
			super(accountHolderName, accountNumber);	
	}
		
	public int withdraw (int amount) {
		if (getBalance() >= amount){
		super.withdraw(amount);
		if (getBalance() < 150 ) {	
		super.withdraw(2);
		}	
		if (amount >= getBalance()) {
			return getBalance();
		}
		}
		return getBalance();
	}
}

