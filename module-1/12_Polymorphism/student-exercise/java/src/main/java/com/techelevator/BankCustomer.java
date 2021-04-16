package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer {

	private String name;
	private String address;
	private String phoneNumber;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	private List<Accountable> accounts = new ArrayList<>();   // List of Accountable objects
    
    public Accountable[] getAccounts() {
    	return accounts.toArray(new Accountable[accounts.size()]); // replace null with the normal array containing the accounts
    }
    
    public void addAccount(Accountable newAcct) {
    	accounts.add(newAcct);
    }
   
    public boolean isVip() {
    	int total = 0;
    	for (Accountable acct : accounts) {
    		total += acct.getBalance();
    	}
    	if(total >= 25000) {
    		return true;
    	}
    	return false; 
    }
    	
}





