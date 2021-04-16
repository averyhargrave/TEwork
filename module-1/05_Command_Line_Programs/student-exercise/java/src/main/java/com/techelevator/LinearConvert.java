package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

			// m = f * 0.3048
			// f = m * 3.2808399
		Scanner userInput = new Scanner(System.in);
		String length = "";
		
		System.out.println("Please enter the length: ");
		length = userInput.nextLine();
		
		double inputNum = Double.parseDouble(length);
		
		System.out.println("Is the measurement in (m)eter, or (f)eet? ");
		length = userInput.nextLine();
		
		if(length.equalsIgnoreCase("m")) {
			System.out.println(inputNum + "m  is " + (inputNum * 3.2808399) + "f");
		} if(length.equalsIgnoreCase("f")) {
			System.out.println(inputNum + "f is " + (inputNum * 0.3048) + "m");
		}
	}

}
