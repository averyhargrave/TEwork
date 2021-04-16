package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// This is the application program for the assessment

public class Module1CodingAssessment {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Module 1 Coding Assessment\n"); 
		
		FlowerShop order  = new FlowerShop("Basic", 0);
		FlowerShop order2 = new FlowerShop("Elite", 12); 
		FlowerShop order3 = new FlowerShop("Elegant", 24);
		FlowerShop order4 = new FlowerShop("Apology", 48);
		FlowerShop order5 = new FlowerShop("Nupital", 18);
		FlowerShop order6 = new FlowerShop("Bereavment", 12);
		System.out.println(order.toString());
		System.out.println(order2.toString());
		System.out.println(order3.toString());
		System.out.println(order4.toString());
		System.out.println(order5.toString());
		System.out.println(order6.toString());
		
		
	}

}
