package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlowerShop {

	private String bouquetType;
	private int numberOfRoses;
	private double subtotal;
	
	public FlowerShop(String bouquetType, int numberOfRoses) {
		this.bouquetType   = bouquetType;
		this.numberOfRoses = numberOfRoses;
		
		if(numberOfRoses == 0) {
			subtotal = 19.99;
		}
		if(numberOfRoses >= 1) {
			subtotal = 19.99 + (2.99 * numberOfRoses);
		}
	}
	
	public double sameDayDelivery(boolean sameDayDelivery, String zipCode) {
		double deliveryFee = 19.99;
		double sameDayFee = 5.99;
		int zip = Integer.parseInt(zipCode);

		if(zip >= 20000 && zip <= 29999) {
			deliveryFee = 3.99;
			if(sameDayDelivery) {
				return deliveryFee + sameDayFee;
			} else {
				return deliveryFee; 
			}
		}
		if(zip >= 30000 && zip <= 39999) {
			deliveryFee = 6.99;
			if(sameDayDelivery) {
				return deliveryFee + sameDayFee;
			} else {
				return deliveryFee; 
			}
		}
		if(zip >= 10000 && zip <= 19999) {
			deliveryFee = 0.0;
			if(sameDayDelivery) {
				return deliveryFee + sameDayFee;
			} else {
				return deliveryFee; 
			}
		}
		else {
			return deliveryFee;
		}
	}
	
	private void loadFile() throws FileNotFoundException {
	File flowerFile = new File("./FlowerInput.csv"); 	 
	Scanner inputDataFile = new Scanner(flowerFile);
	
	String bouquetType = "";						
	int numberOfRoses  = 0;								
	
	while(inputDataFile.hasNext()) {				
		String flowerOrder = inputDataFile.nextLine();			
		String[] flowerAttributes = flowerOrder.split(",");	
	
		bouquetType   = flowerAttributes[0];						
		numberOfRoses = Integer.parseInt(flowerAttributes[1]);					
	
		FlowerShop anOrder = new FlowerShop(bouquetType, numberOfRoses);	
		
		ArrayList<FlowerShop> listOfOrders = new ArrayList<>();
		listOfOrders.add(anOrder);
		}
	inputDataFile.close();				
	}
	
	@Override
	public String toString() {
		return "ORDER - " + bouquetType + " - " + numberOfRoses + " roses - $" + String.format("%.2f", subtotal);
	}
}
