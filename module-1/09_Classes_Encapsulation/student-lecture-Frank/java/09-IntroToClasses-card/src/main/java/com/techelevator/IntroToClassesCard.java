package com.techelevator;

public class IntroToClassesCard {
        //------------------------------------------------------------------------------------------------------------
        // Application program for the Intro To Classes App
        //------------------------------------------------------------------------------------------------------------
        //
		// Every Java application must have one method called main()
		// main() is where execution starts for any Java application
		// main() is the Application Program
		// Application Program instantiates and manipulates objects to get its work done
	
		public static void main(String[] args) {
        /*
        *  This program will demonstrate several concepts presented in the Java cohort
        *  in the topic Introduction to Classes 		
        */
// To instantiate an object:
//           ClassName objName = new ClassName();   the () after the class name causes a constructor for the class to be run
			PlayingCard card1  = new PlayingCard(); //instantiate a PlayingCard object
			
			System.out.println(card1); // display aCard
									   // System.out.println - displays Strings
			                           // whatever you give it inside () must be convertible to a String
									   // aCard is a PlayingCard type - not a String
									   // System.out.println looks for a method to convert aCard to a String
									   // a Class may provide a method called toString() to return a String
			 						   //                          representation of an object of the class
									   // if a toString() is not provided for the class, an error occurs when a String is needed
									   // System.out.println looks for a toString() for the PlayingCard class
									   // If a toString() is found for the class of an object, it is run to create the String
								       
			PlayingCard card2 = new PlayingCard(1, "Clubs", "black");
			System.out.println(card2);
			
			PlayingCard card3 = new PlayingCard(9, "Hearts", "red");
			System.out.println(card3);
						
//			System.out.println("The value in card3 is: " + card3.cardValue;  // error because cardValue is private in PlayingCard
			System.out.println("The value in card3 is: " + card3.getCardValue());
		
//          card3.cardValue=1;     // error because cardValue is private in PlayingCard			
			card3.setCardValue(1); // Change cardValue in card3 using the setter method
			System.out.println("The value in card3 is: " + card3.getCardValue());
		
		}
}