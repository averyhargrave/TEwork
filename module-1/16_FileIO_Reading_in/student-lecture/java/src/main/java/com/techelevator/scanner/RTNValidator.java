package com.techelevator.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RTNValidator {
	
	// This program will validate bank routing numbers stored in a file
	// The file used is called rtn.txt
	
	// constant of values used to determine if an rtn is valid - has nothing to do with File I/O
	private static final int[] CHECKSUM_WEIGHTS = new int[] { 3, 7, 1, 3, 7, 1, 3, 7, 1 };

	public static void main(String[] args) throws FileNotFoundException {

		printApplicationBanner(); // Display a polite hello message - sometimes called a banner
		
		// File inputFile = new File("rtn.txt"); // Identify the path as a String literal - hard coded value 
		
		// Instantiate a file object for the path returned from getInputFileFromUser() method
		File inputFile = getInputFileFromUser();		    // Define a file object for the file
		
	// Instantiating a Scanner object in a try() causes it to be automatically closed at the end of the program
	// If you define the Scanner object yourself, you are responsible for closing it at the end of the program
	// Leaving a file open at the end of the program you are causing a "resource leak"
	// A "resource leak" is when resources (memory) used by the program are not freed at the end of the program
	// "Resource leaks" can lead to slower processing, unexpected errors, or the computer stopping
		
		// We get the path from a method
		try(Scanner fileScanner = new Scanner(inputFile)) { // Define a Scanner object for the file
	
		// If you try to read more lines than in the file, an Exception is thrown - program terminates with a message
		
			while(fileScanner.hasNextLine()) {				// Loop through the file as long as it has a line to read
				String line = fileScanner.nextLine();		//     get the nextLine from the file
				// once the data is in the program, process it as necessary
				String rtn = line.substring(0, 9);			//     extract the first 9 characters as the rtn
				
				if(checksumIsValid(rtn) == false) {			// Pass the rtn to the validation method 
					System.out.println(line);				// If the rtn is not valid, we display it
				}
			}
			// before the program ends, close any files that were not instantiated in a try method
			fileScanner.close(); // Close the file to release resources used to process it
		
		}
	}

	private static void printApplicationBanner() {
		System.out.println("******************");
		System.out.println("RTN Validator 9000");
		System.out.println("******************");
		System.out.println();
	}

	@SuppressWarnings("resource")
	// This method will get a path from a user, if valid it will return a File object for the path
	private static File getInputFileFromUser() {
		Scanner userInput = new Scanner(System.in);  			  // Instantiate an object for the keyboard
		System.out.print("Please enter path to input file >>> "); // Display a user prompt
		String path = userInput.nextLine(); 					  // Get a line of input from the keyboard
		
		File inputFile = new File(path);  // Instantiate a File object for the path entered by the user
		
		// Check to be sure the user input is a valid existing path to a file
		
		if(inputFile.exists() == false) { 				// does the path given NOT exist
			System.out.println(path+" does not exist"); // If so, display message and end program
			System.exit(1); 							//    with a return code 1
		} else if(inputFile.isFile() == false) {		// is the path given NOT a file
			System.out.println(path+" is not a file");  // If so, display message and end program
			System.exit(1);								//    with a return code 1
		}
		// If we get here, we know the path given was a valid, existing file
		return inputFile; // so we return the file object for the path
	}

	private static boolean checksumIsValid(String routingNumber) {
		int checksum = 0;
		for(int i = 0; i < 9; i++) {
			int digit = Integer.parseInt(routingNumber.substring(i, i+1));
			checksum += digit * CHECKSUM_WEIGHTS[i];
		}
		return checksum % 10 == 0;
	}
}
