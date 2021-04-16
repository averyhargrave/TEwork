// slash-slash indicates comment in java code
// a comment is information for a human who might be reading your code
// a comment line is ignored by the compiler
/* start of a block comment
 * 	  a slash-asterisk starts a block comment
 * 	  all lines between the slash-asterisk and the asterisk-slash
 * 	  are treated as comments and ignored by the compiler
 */ // end of block comment

package com.techelevator;   // the package that holds the classes for the application
					 // double clicking a brace will highlight all contents within the braces
public class Lecture {      // a class within the application

	public static void main(String[] args) {   /* a function in the application
											      every Java application has at least a main() function
		
		   variables are used to hold data needed for the program
		   data and understanding the data is fundamental to writing a successful program
		
		   in Java, a variable is defined: datatype name;
		 								   datatype name = initial-value;
           if an initial value is not specified when you define the variable
           	 it's content is unknown which may lead to errors or unpredictable results
          
           it is a good idea to ALWAYS initialize variables
           
           variable names must be unique within a block of code (between {})
           
           Common Java Data types based on what they represent:
             if the value is numeric with*out* a decimal point: int
             if the value is numeric with a decimal point:      double
             if the value is a single character:         		char
             if the value is multiple characters or words:      string
             if the value is true or false:						boolean
             
           String is a "special" data type which we will discuss later (String starts with capital letter)
		*/
		
		
		/*
		1. Create a variable to hold an int and call it numberOfExercises.
			Then set it to 26.
		*/
		int numberOfExercises = 26;
		// display                     these words           and the value of this variable
		System.out.println("The value of numberOfExcercises is " + numberOfExercises); // display whatever is between ()

		/*
		2. Create a variable to hold a double and call it half.
			Set it to 0.5.
		*/
		double half = .5;  // 0.5 is also acceptable, but not necessary
		System.out.println(half);

		/*
		3. Create a variable to hold a String and call it name.
			Set it to "TechElevator".
		*/
		String name = "TechElevator";
		System.out.println(name);

		/*
		4. Create a variable called seasonsOfFirefly and set it to 1.
		*/
		int seasonsOfFirefly = 1;
		System.out.println("There is " + seasonsOfFirefly + " season of Firefly.");

		/*
		5. Create a variable called myFavoriteLanguage and set it to "Java".
		*/
		String myFavoriteLanguage = "Java";
		System.out.println(myFavoriteLanguage);

		/*
		6. Create a variable called pi and set it to 3.1416.
		*/
		double pi = 3.1416;
		System.out.println(pi);

		/*
		7. Create and set a variable that holds your name.
		*/
		String myName = "Avery";
		System.out.println(myName);
		/*
		8. Create and set a variable that holds the number of buttons on your mouse.
		*/
		int mouseButtons = 6;
		System.out.println(mouseButtons);
		/*
		9. Create and set a variable that holds the percentage of battery left on
		your phone.
		*/
		double pctBatteryLife = 0; // Initalized to - as it needs to have a value for Eclipse let us run
		System.out.println(pctBatteryLife);
		/*
		10. Create an int variable that holds the difference between 121 and 27.
		*/
		int difference = 121 - 27;
		System.out.println(difference);
		/*
		11. Create a double that holds the addition of 12.3 and 32.1.
		*/
		double sum = 12.3 + 32.1;
		System.out.println(sum);
	         /* sum is displayed as 44.4000000000006 due to converting the
			    internal format of a double to a display format
		        the value stored in sum is correct, it just displays oddly */
		/*
		12. Create a String that holds your full name.
		*/
		String fullName = "Avery Hargrave";
		System.out.println(fullName);
		/*
		13. Create a String that holds the word "Hello, " concatenated onto your
		name from above.
		*/                 /* + used with a string means concatenation (stick strings together)
		                   // + used with numerics means addition
		                   // another example of a character with multiple meanings is:
		                   // . at the end of a string of words means end of sentence (period)
		                      . between two numbers means decimal point */
		String greeting = "Hello, " + fullName;
		System.out.println(greeting);
		/*
		14. Add a " Esquire" onto the end of your full name and save it back to
		the same variable.
		*/
		fullName = fullName + " Esquire";
		System.out.println(fullName);
			// DO NOT CODE A DATA TYPE WHEN ASSIGNING A VALUE TO AN EXISTING VARIABLE
		/*
		15. Now do the same as exercise 14, but use the += operator.
		*/
        fullName += " Esquire";
        System.out.println(fullName);
        	// fullName will now have two " Esquires" after it because step 15 and 16
		/*
		16. Create a variable to hold "Saw" and add a 2 onto the end of it.
		*/
        String movie = "Saw";
        movie += " 2"; // movie = movie + " 2" is also acceptable"
        System.out.println(movie);
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
        System.out.println(5.4 / 2);
		/*
		20. What is 5 divided by 2?
		*/
        System.out.println(5/2);
        	/* the result is 2 instead of 2.5 because
        	    5  /  2
               int / int - Java does Integer Divide and the results remains an int
        	   Integer Divide results in a quotient and a remainder
        	   5 / 2 - quotient=2, remainder=1 */
		/*
		21. What is 5.0 divided by 2?
		*/
        System.out.println(5.0/2);
        	/* the result is 2.5 because
        	    5.0   /  2
        	   double / int - different data types, Java will convert the int to a double
        	   Double Divide results in a double, rather than a quotient and a remainder
        	   when presented two different data types, Java will promote the lesser type to a higher type
        	   Hierarchy of Data Types: double, float, long, int, byte/char
        
        	  Literal data type specifier is using a letter to change a number.
        	  F = make the number a float. (5F / 2); = 2.5 
        	  D = make the number a double. (5D / 2); = 2.5
        	  L = make the number a long. (5L / 2); = 2.5
        	  
        	  casting is used more often:
        	  (double) is called casting, it tells Java to pretend an int is a double
        	  ex. ((double) 5 / 2); = 2.5
        	  */
		/*
		22. What is 66.6 divided by 100? Is the answer you get right or wrong?
		*/
        System.out.println(66.6 / 100);
        	// answer is correct but displayed oddly due to the issue
        	// of converting double to display value
        	// we will learn how to round and display nicely later
		/*
		23. If I divide 5 by 2, what's my remainder?
		*/
        System.out.println(5 % 2);
        	// % is a modulus operator that returns the remainder of int divide
        	// if you divide a number by two and the result is 0, the number is even
        	// if you divide a number by two and the result is 1, the number is odd
		/*
		24. What is 1,000,000,000 * 3?
		*/
        System.out.println(1000000000F * 3);
        // (1000000000 * 3L); or (1000000000 * (long) 3); would also work
        	// 3 billion doesn't fit in an int, so you must convert the number to a float or a long
		/*
		25. Create a variable that holds a boolean called doneWithExercises and
		set it to false.
		*/
        boolean doneWithExercises = false;
        System.out.println(doneWithExercises);
		/*
		26. Now set doneWithExercise to true.
		*/
		doneWithExercises = true;
		System.out.println(doneWithExercises);
	}

}
