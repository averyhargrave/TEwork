// slash-slash indicates a comment in your Java code
// a comment is information for a human who might read your code
// a comment line is ignored by the compiled
/*  start of a block comment
 *      slash-asterisk starts a block comment
 *      all lines between the slash-asterisk and asterisk-slash
 *      are treated as comment and ignored by the compiler.
*/  // end of block comment

package com.techelevator;   // the package that holds the classes for this application

public class Lecture {      // A class within  the application

	public static void main(String[] args) {  // a function in the application class
		                                      // every Java application has at least a main() function
		
		// variables are used to hold data needed for the program 
		// Data and understanding the data is fundamental to writing a successful program
		
		// in Java a variable is defined:    datatype name;
		//                                   datatype name = initial-value;
		//
		// If an initial value is not specified when you define the variable
		//    it's content is unknown which may lead to errors or unpredictable results.
		//
		// It's a good idea to ALWAYS initialize variables
		//
		// variable names must be unique within a block of code (between {})
		//
		// Common Java Data Types Based on What they represent:
		//
		//   If the value is numeric, without a decimal point:   int
		//   If the value is numeric, with a decimal point:      double
		//   If the value is a single character:                 char
		//   If the value is multiple characters or words:       String
		//   If the value is true or false:                      boolean
		//  
		
		// String is a "special" data type which we will discuss later
		
		
		/*
		1. Create a variable to hold an int and call it numberOfExercises.
			Then set it to 26.
		*/
		int numberOfExercises = 26;
		
		// If you want to indicate a String value put it inside " "
		// display          these words                        and the value in this variable
		System.out.println("The value in numberOfExercises is " +  numberOfExercises);  // Display whatever is between ()

		/*
		2. Create a variable to hold a double and call it half.
			Set it to 0.5.
		*/
		double half = .5;   // 0.5 is OK too
		
		System.out.println("half contains: " + half);

		/*
		3. Create a variable to hold a String and call it name.
			Set it to "TechElevator".
		*/
		String name = "Tech Elevator";
		System.out.println("name is: " + name);

		/*
		4. Create a variable called seasonsOfFirefly and set it to 1.
		*/
		int seasonsOfFirefly = 1;
		System.out.println(seasonsOfFirefly);

		/*
		5. Create a variable called myFavoriteLanguage and set it to "Java".
		*/
		String myFavoriteLanguage = "Java";
		System.out.println("Fav Lang: " + myFavoriteLanguage);

		/*
		6. Create a variable called pi and set it to 3.1416.
		*/
		double pi = 3.1416;
		System.out.println("Value of pi is: " + pi);

		/*
		7. Create and set a variable that holds your name.
		*/
		String myName = "your name here";
		System.out.println("my name is " + myName);
		/*;
		8. Create and set a variable that holds the number of buttons on your mouse.
		*/
		int numMouseButtons = 2;
		/*
		9. Create and set a variable that holds the percentage of battery left on
		your phone.
		*/
		double pctBatteryLife = 0;  // Initialized to 0 as it needs to have a value for Eclipse to let us run
		System.out.println("Battery life: " + pctBatteryLife);
		/*
		10. Create an int variable that holds the difference between 121 and 27.
		*/
		int diff = 121 - 27;
		System.out.println("diff=" + diff);
		/*
		11. Create a double that holds the addition of 12.3 and 32.1.
		*/
		double sum = 12.3 + 32.1;
		System.out.println("sum: " + sum);  // sum is displayed as 44.4000000000006
		                                    //     due to converting the internal format of a double
		                                    //     to a display format
		                                    // the value stored is correct, it just displays oddly
		/*
		12. Create a String that holds your full name.
		*/
		String fullName = "Rocky P. Squirrel";
		System.out.println("fullname: " + fullName);
		/*
		13. Create a String that holds the word "Hello, " concatenated onto your
		name from above.
		*/
		String joEl = fullName + "Hello";
		// + when used with String means concatenation (stick the strings together)
		// + when used with numerics means additions
		//
		// Often data types used determine the operation performed
		// 
		// In English: . at then end of a string of words means end of sentence (period)
		//             . between two numbers means decimal point

		/*
		14. Add a " Esquire" onto the end of your full name and save it back to
		the same variable.
		*/
		// DO NOT CODE THE DATATYPE when assigning a value to existing variable
		fullName = fullName + " Esquire";
		/*
		15. Now do the same as exercise 14, but use the += operator.
		*/
		fullName += " Esquire"; // now fullName has two " Esquires" at the end
		System.out.println("fullName is now: " + fullName);
		/*
		16. Create a variable to hold "Saw" and add a 2 onto the end of it.
		*/
		String movie = "Saw";
		movie += "2"; // movie = movie + "2" is OK too as is movie = movie + 2
		/*
		17. Add a 0 onto the end of the variable from exercise 16.
		*/
		movie = movie + 0;
		System.out.println(movie);
		/*
		 18. What is 4.4 divided by 2.2?
		*/
		System.out.println("4.4 / 2.2 is: " + 4.4 / 2.2);
		/*
		19. What is 5.4 divided by 2?
		*/
		System.out.println("5.4 / 2 is: " + 5.4 / 2);
		/*
		20. What is 5 divided by 2?
		*/
		System.out.println("5 / 2 is: " + 5 / 2);  // the result =2 NOT 2.2
		                                           //   5   /   2
		                                           //  int  /  int - Java does integer divide
		                                           //      int     - result is an int
		                                           // integer divide results in a quotient and remainder
		                                           // 5 / 2  - quotient=2, remainder=1
		/*
		21. What is 5.0 divided by 2?
		*/
		System.out.println("5.0 / 2 is: " + 5.0 / 2);  // result is 2.5
													   //  5.0    /   2
                                                       // double  /  int - different data types
		                                               // double  / double - Java will convert the int to a double
		                                               //                    it does double divide
                                                       //      double    - result is an double
		
        // When presented with different data types Java will promote the lesser type to higher type
		// Hierarchy of data types: double, float, long, int, byte/char
		
		System.out.println("5F / 2 is: " + 5F / 2);  // result is 2.5
		// F (or f) is a literal data type specifier that tells Java treat value as a float
												     //  5F    /   2
                                                     // float  /  int - different data types
                                                     // float  / float- Java will convert the int to a float
		                                             //                    it does float divide
                                                     //     float   - result is an float

		
		System.out.println("(double) 5 / 2 is: " + (double) 5 / 2);  // result is 2.5
		// (double) is casting the value to a type - tells Java treat value as a float
												     //  (double) 5    /   2
                                                     //        double  /  int - different data types
                                                     //         double / double- Java will convert the int to a double
		                                             //                            it does double divide
                                                     //     double   - result is an double
		/*
		22. What is 66.6 divided by 100? Is the answer you get right or wrong?
		*/
		System.out.println("66.6 / 100 is " + 66.6 / 100);  // answer is display oddly due to the issue
		                                                    //     of converting double to display value
		                                                    // We learn how to round and display nicely later
		/*
		23. If I divide 5 by 2, what's my remainder?
		*/
		System.out.println("5 % 2 is: " + 5 % 2); // % is modulus operator which returns the remainder of int divide
												  // 5 / 2 - quotient is 2 the remainder is 1
		
		/*
		24. What is 1,000,000,000 * 3?   - expecting the value 3,000,000,000
		*/
		System.out.println("1000000000 * 3 is " + 1000000000*3);  // result is not correct because 3000000000 doesn't fit in an int
		//                                              int * int - result is an int
		
		System.out.println("1000000000 * 3L is " + 1000000000*3L);  // by using the 'L' as a modified the result will be a long
		System.out.println("1000000000 * (long) 3 is " + 1000000000* (long) 3);  // by casting to long the result will be a long
		
		/*
		25. Create a variable that holds a boolean called doneWithExercises and
		set it to false.
		*/
		boolean doneWithExercises = false;
		System.out.println("doneWithExercises=" + doneWithExercises);
		/*
		26. Now set doneWithExercise to true.
		*/
		doneWithExercises = true;
		System.out.println("doneWithExercises=" + doneWithExercises);
	}

}
