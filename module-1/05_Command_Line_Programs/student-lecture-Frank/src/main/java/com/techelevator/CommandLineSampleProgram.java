package com.techelevator;
// If we use something that is not part of the standard Java language, we need to import the code that supports it
import java.util.Scanner; // Give me access to Scanner stuff provided by Java

public class CommandLineSampleProgram {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Frank's Command Line Sample Program");
		
		// This code will read input from the keyboard and process it
		
		// Define an object to represent the keyboard in this program
		// Scanner is the class that Java provides for us to define keyboard objects
		// Since Java provides the System.in to represent the actual keyboard
		//       we need to associate our program keyboard object with System.in
		
		Scanner userInput = new Scanner(System.in); // Create a scanner object for the keyboard, calling it userInput
		
		String aLine = ""; // Will hold the line of input from the keyboard
		
		
		System.out.println("Please enter a line: "); // Send a prompt to the user so they know they need to type something
		aLine = userInput.nextLine(); // go to the userInput object and get the nextLine
		
		System.out.println("You entered the line: " + aLine); // display the line entered by the user
		
		System.out.println("Please enter a whole number: ");
		
	 // aLine = userInput.nextLine(); 
		int aNumber = userInput.nextInt();  // Get a number from the keyboard
		userInput.nextLine(); // remove the Enter that's left in the keyboard buffer by nextInt
		                      //    so your subsequent keyboard requests allow the user to type input
		System.out.println("You entered the number: " + aNumber);  // Show them what we got
		
		// We want to divide what the user entered by 2 to get half the value
		// We stored what they entered as a String which cannot be used in arithmetic
		// We need to convert the value in the String to a numeric value so we can use arithmetic
		// We can use the Integer.parseInt() or Double.parseDouble() to convert a String to a numeric
		
	 // No longer need to convert the user input to an int from a String because we are getting an int
		//int theNumber = Integer.parseInt(aLine); // parsing is used to convert one data type to another
		System.out.println("Half of that number is: " + aNumber / 2); // Display the int version of what was typed divided by 2
		
		
		// Get a series of values from a user and determine the sum and the average
		// 1. Ask the user to enter a value or indicate they are done ("N" = done)
		// 2. If they are not done:
		//    a. convert the value entered into a numeric so we can do arithmetic
		//    b. add the converted value to a sum
		//    c. count that they entered a number so we can calculate the average 
		// 3. Once they are done - display the sum and the average
		
		String theValue = ""; // Hold the value entered by the user
		double sum = 0;       // Hold the sum of the values they enter - double because we are allowing decimal points
		int numNums = 0;      // Hold the number of values they enter
		
		// We are using a while-loop because we don't know how many values the user will enter
		// The while-loop will loop while a condition is true - end when the condition is false
		
		// In this case we will specify a never-ending loop by looping while true
		// We will use break to end the loop when we decide it needs to end
		
		while(true) { // loop until the end of time or we break out of the loop
			
			System.out.println("Enter a number, type 'N' when you're done."); // prompting the user for input
			theValue = userInput.nextLine(); // get input from the user
			
			// to test a String for equals we CANNOT use the normal ==, We learn why on Monday
			// use the .equals() method to check a String for a value
			if(theValue.equals("N") || theValue.equals("n")) { // if they enter an "N":
				break; 	                                       // end the loop
			}
			double valueEntered = Double.parseDouble(theValue); // Convert what they entered from a String to a double
			sum += valueEntered;                                // add the value entered as a number to sum
			numNums++;                                          // add 1 to the count of numbers they have entered
		}
		// This is the end of the loop - get here when we exit the loop
		System.out.println("The sum of the numbers is: " + sum);
		System.out.println("You entered " + numNums + " numbers");
		System.out.println("The average of the numbers is: " + (sum / numNums));
	
		
		
	}

}
