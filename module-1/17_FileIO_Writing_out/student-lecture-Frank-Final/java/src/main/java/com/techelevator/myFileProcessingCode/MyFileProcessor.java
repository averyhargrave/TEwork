package com.techelevator.myFileProcessingCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MyFileProcessor {

	/*********************************************************************************
	 * This program will read each line from the numbers.txt file
	 * and write each number in the line and the sum of those numbers to a file
	 * @throws IOException 
	 *********************************************************************************/
	
	public static void main(String[] args) throws IOException {  // IOExcpetion is super class of all I/O Exceptions
		
		System.out.println("Program has started...");
		
		// Define the input file and Scanner object to read it - file is in the data folder of the project folder
		File myFile = new File("./data/numbers.txt");  // Assign a File object to numbers.txt

		// Check to be sure the File Object is assigned an existing file - terminate if not
		if (!myFile.exists() || !myFile.isFile()) {
			System.out.println("path specified is not an existing file");
			System.exit(16);   // terminate program with a return code 16
			                   // When a program uses the exit() method an optional 
			                   //           return code may be sent back to the operating system
			                   // If the program was run from a shell script, the retunr can be checked
			                   //    so the script may take action based on the return code.
			                   // The meaning a return value depends on the script that is running the program
			                   // Return are usually a multiple of 4 - a tradition from ancient days of programming
		                       // In general a return code 0 means OK, not 0 means not OK
		}
		
		Scanner theFile = new Scanner(myFile);         // Assign the File Object to a Scanner
		
		// Define the output file we are writing the program to
		File outputFile = new File("program.out");  
		
		// Create the file on the disk - file must exist before we can write to it
		outputFile.createNewFile();  // Will destroy any existing copy of the file
		
		// Define a PrintWriter Object for the output file
		PrintWriter fileWriter = new PrintWriter(outputFile);  // If the file already exists, it will overylay teh existing data
		
		fileWriter.println("--------------------------------------------------");
		
		int lineTotal  = 0;  // hold the sum of the numbers in the line we read
		String theLine = ""; // hold the line with the numbers from the file
		
		// Loop through the file one line at a time while there are lines in the file
		while(theFile.hasNextLine()) {
			// Read a line from the file and store it in theLine
			theLine = theFile.nextLine();
			// Break the line up into separate values based on the , separating the value
			String[] theValues = theLine.split(",");
			//    For each value in the line...
			for(int i=0; i < theValues.length; i++) {
				// Convert the individual value from String to numeric so we can add them
				int aValue = Integer.parseInt(theValues[i]);
				// Add each value from the line to sum
				lineTotal += aValue;
				// Display the values in the line
				fileWriter.println("Input Line Value[" +i+"] is: " + aValue);
			}
		//    Display the sum of the values
			fileWriter.println("The sum of the values in the line is: " + lineTotal);
		//    Reset sum before looping again to be sure we only get the sum of the numbers in the line
		    lineTotal = 0;
		}
		// Close the file to avoid a resource leak
		theFile.close();       // release resources
		fileWriter.close();    // release resources AND write any data in the buffer to the file
		
		System.out.println("Program has ended...");
	}
}