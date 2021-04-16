package com.techelevator;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
	}
	public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
	}

	@Override
    public int withdraw(int amountToWithdraw) {
        if (getBalance() < 0 && getBalance() > - 100) {
            amountToWithdraw += 10;
        } 
        else if (getBalance() - amountToWithdraw <= - 100) {
            amountToWithdraw = 0;
            System.out.println("Withdraw has failed");
        }
        return super.withdraw(amountToWithdraw);
    }

	
	
}
