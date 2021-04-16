package com.techelevator;

public class FranksArrayExample {
	
	public static void main(String[] args) {  
		
		System.out.println("Welcome to Frank's Array Example Program"); 

		int score1 = 10;
		int score2 = 20;
		int score3 = 30;
		int score4 = 50; // this was added after the code was written and tested
		int score5 = 90; // this was added after the code was written and tested
		
		int sum = 0;      // this will hold the sum of the scores
		double avg = 0.0; // this will hold the average of the scores
		
		sum = score1 + score2 + score3 + score4 + score5; // a change in data required a change in code
		avg = sum / 5;                           		  // a change in data required a change in code
		
		// this code is tightly couple because changes to the data require changes to the code
		// tightly coupled code is considered a bad practice because one might forget to change the code in one or more places
		
		System.out.println("Sum of scores: " + sum);
		System.out.println("Avg of scores: " + avg);
	
		//---------------------------------------------------------------------------------------------------------------------
		// Use an array to hold and process the scores to make the code loosely coupled
		//
		// loosely coupled code means the code is not directly bound to the data - sign of a professional programmer
		// changes to the data do not require changes to the code
	
// Define an empty array and add elements to it later
		
		int[] scores = new int[5]; // define a new int array with 5 elements after it had originally been 3
		
		scores[0] = 10; // set first element in array to the value 10
		scores[1] = 20; // set second element in array to the value 20
		scores[2] = 30; // set third element in array to the value 30
		scores[3] = 80;  // added after code was written and tested 
		scores[4] = 120; // added after code was written and tested
		
// Define and initialize the array to known values - do not specify the number of elements
		
		// Java will allocate the number of elements base on the values in the array
		// 		int[] scores = {10, 20, 30, 40, 35, 25, 65, 75};  define and initialize at the same time
												
		
// Define a variable to hold the number of elements we want in the array and use that variable to define
		
		// 	 int numberElements = 5; the value for this variable can come from anywhere
		//						      it MUST have a value when it is used to create the array
		//   int[] scores = new int[numberElements]; // Java will use the value in numberElements to set the size of the array
		
		
		// changing the data did NOT require any changes to the code - loosely coupled
		// loosely coupled means code is independent of data
		
		sum = 0;
		
	  /* use a for-loop to process each element in the array to determine the sum
		
		 for loop will iterate through a block until a condition is false
		 a loop variable is defined in the for-loop and incremented each time through the loop
		 a condition in the for-loop is tested BEFORE each loop to see if it should loop
		 you will loop as long as the condition is true
		
	   	 for(int =0; i < scores.length; i++)    - the value in i will start at 0 go up the length of the scores
			 1. i will be set to 0
			 2. check to see if i is less than the number of elements in the array (i<3) if i=0,1,2 = loop; if i=3 = exit loop
		     3. if step 2 is true - continue to step 4; if step 2 is false - exit the loop
		     4. execute the statement in the block
		     5. increment i (add 1 to i)
		     6. go back to step 2
		*/
// 		      initial    condition	  increment
		for(int i=0; i < scores.length; i++) { // loop while i is a valid index for the scores array
			sum = sum + scores[i]; // add the element in scores to sum; scores[i] is called the current element in the array
		}
		// at the end of the loop, sum will contain the total of all of the elements
		
		avg = sum / scores.length; // divide sum by the number of elements in the array to get the avg
	
		System.out.println("Sum of scores: " + sum);
		System.out.println("Avg of scores: " + avg);
	}   
   
}