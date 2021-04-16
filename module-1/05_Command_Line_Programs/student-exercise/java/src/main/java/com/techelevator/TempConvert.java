package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		String temp = "";
		
		System.out.println("Please enter the temperature: ");
		temp = userInput.nextLine();
		
		double inputNum = Double.parseDouble(temp);
		
		System.out.println("Is the temperature in (C)elcius, or (F)ahrenheit? ");
		temp = userInput.nextLine();
		
		if(temp.equalsIgnoreCase("C")) {
			System.out.println(inputNum + "C  is " + ((inputNum * 9 / 5) + 32) + "F.");
		} if(temp.equalsIgnoreCase("F")) {
			System.out.println(inputNum + "F is " + ((inputNum - 32) * 5 / 9) + "C.");
		}
	}

}
