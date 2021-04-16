package com.techelevator.myFileReadingCode;

import java.io.File; // include the code to support Java File I/O
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFileReader {

	// throws - tells the compiler you know your code might throw an exception
	public static void main(String[] args) throws FileNotFoundException {
		
		// Read the data from a file containing lines of numbers separated by commas
		// Display the line we read from the file
		// Sum the numbers
		// Average the numbers
		// Print the sum and the average
		
		int    sum = 0;       // Holding the sum of the numbers from a line in the file
		double average = 0; // Holding the average of the numbers from a line in the file
		
		// Instantiate a File object for the file containing our data
		File theFile = new File("./data/numbers.txt");
		
		// Instantiate a Scanner object for the file
		Scanner fileReader = new Scanner(theFile);
		
		// Loop through the file one line at a time
		while(fileReader.hasNext()) { // As long as the file has a next line to read
			String aLine = fileReader.nextLine();              // Get the next line from the file
			System.out.println("The line read: " + aLine);     // Display the line from the file (not necessary, just used to verify)
			String[] theNumbers = aLine.split(",");            // split the line based on a comma
			
			sum = 0;                                           // reset sum to clear any old value that is in it
			
			for(int i=0; i < theNumbers.length; i++) {         // Loop through the String number values one at a time
				// adding .trim will remove spaces in the data
				int aNumber = Integer.parseInt(theNumbers[i].trim()); // convert the current element in theNumbers to an int
				sum += aNumber;								   // add the number to the sum	
			}
			average = (double)sum / theNumbers.length;         // calculate the average by dividing the sum by the number of numbers
			System.out.println("Sum: " + sum + " | Average: " + average);
		} // end of while loop
		fileReader.close(); // close the Scanner to avoid a resource leak
} // end of main
}