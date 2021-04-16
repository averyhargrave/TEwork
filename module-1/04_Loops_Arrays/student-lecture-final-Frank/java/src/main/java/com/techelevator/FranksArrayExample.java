package com.techelevator;

public class FranksArrayExample {
	
	public static void main(String[] args) {  
		
		System.out.println("Welcome to Frank's Array Example Program"); 

		int score1 = 10;
		int score2 = 20;
		int score3 = 30;
		int score4 = 50;  // this was added after the code was written and tested
		int score5 = 90;  // this was added after the code was written and tested
		
		int sum = 0;      // this will hold the sum of the scores
		double avg = 0.0; // this will hold the average of the scores
		
		sum = score1 + score2 + score3 + score4 + score5;  // a change in data required a change in code
		avg = sum / 5;                                     // a change in data required a change in code

		// This code is tightly couple because changes to the data require changes to code.
		// Tightly coupled code is considered a bad practice because one might forget to change code in one or places
		
		System.out.println("Sum of scores: " + sum);
		System.out.println("Avg of scored: " + avg);
	
		//-----------------------------------------------------------------------------------------------------
		// Use an array to hold and process the scores to make the code loosley coupled
		//
		// loosely coupled means the code is not direct bound to the data - sign of a professional programmer
		// changes to data does not require changes to the code
		//
//  Define an empty array and add elements elements to it later 
// 
//		int[] scores = new int[5];  // define a new int array with 5 elements after it orginally being
//		
//		scores[0] = 10;  // set the first element in the array to value 10
//		scores[1] = 20;  // set the second element in the array to value 20
//		scores[2] = 30;  // set the third element in the array to value 30
//		scores[3] = 80;  // added this element AFTER the code was written and tested
//		scores[4] = 120; // added this element AFTER the code was written and tested

// Define and initialize the array to known values - do not specify the number of elements
// Java will allocate the number of elements based on the values in the list
		
//      int[] scores = {10,20,30,40,35, 25, 65, 75}; // define and initialize at the same time
		
// Define a variable to hold the number of elements we want in the array and use that variable to define
		int numberElements = 5;  // The value for this variable can come from anywhere - constant, file, screen, calculated
		                         //     It MUST have a value when it used to create the variable
		
		int[] scores= new int[numberElements];   // Java will use the value in numberElements to set the size of the array
		
		scores[0] = 10;  // set the first element in the array to value 10
		scores[1] = 20;  // set the second element in the array to value 20
		scores[2] = 30;  // set the third element in the array to value 30
		scores[3] = 80;  // added this element AFTER the code was written and tested
		scores[4] = 120; // added this element AFTER the code was written and tested

		// changing the data did NOT require any changes to the code - loosely coupled
		// loosely coupled means code is independent of data
		
		sum = 0;         // reset sum to 0 to remove previous value in sum
		
		// use a for-loop to process each element in the array to determine the sum
		//
		// for loop will iterate through a block until a condition is false.
		// a loop variable is defined in the for-loop and incremented each time through the loop
		// a condition in the for-loop is tested BEFORE each loop to see if it should loop
		// you will loop as long as the condition is true
		//
		// for(int i=0; i <scores.length; i++)   - the value in i will start at 0 and go up to the length of scores (0,1,2,3)
		//    1. i will be set to 0
		//    2. check to see if i is less then the number of elements in scores (i < 3) - i=0,1,2 we loop; i=3=exit loop
		//    3. if #2 is true - continue to step 4; if #2 is false - exit the loop
		//    4. execute the statement in the block
		//    5. increment i (add 1 to i)
		//    6. go back to step #2
		//
		
        //    init	     condition	   increment
		for(int i=0; i < scores.length; i++) {   // loop while i is a valid index for the scores array (i < scores.length)
			sum = sum + scores[i];   // add the element in scores[i] to sum; scores[i] is called the current element
		}
		// at the end of the loop sum will contain the total of all the elements
		
		avg = sum / scores.length;   // divide sum by the number of elements in the array to get the average
		
		System.out.println("Sum of scores: " + sum);
		System.out.println("Avg of scored: " + avg);
	}   
   
}