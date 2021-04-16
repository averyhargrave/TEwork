package com.techelevator;

public class IntroToClassesCard {
        //------------------------------------------------------------------------------------------------------------
        // Application program for the Intro To Classes App
        //------------------------------------------------------------------------------------------------------------
        //
		// Every Java application must have one method called main()
		//
		// main() is where execution starts for any Java application
		//
		// main() is the Application Program
		//
		// Application program instantiates and manipulates object to get it's work done
		//
		public static void main(String[] args) {
        /*
        *  This program will demonstrate several concepts presented in the Java cohort
        *  in the topic Introduction to Classes 		
        */
// to instantiate an object:
//          ClassName   objName = new ClassName();  the () after the class name causes a constructor for the class be run
//                                                         if no constructor for the class is found a default constructor
			PlayingCard aCard   = new PlayingCard();  // Instantiate a PlayingCard object

			System.out.println(aCard);  // display aCard 
			                            // System.out.println - displays Strings
			                            // Whatever you give it inside () must be convertible to a String
										// aCard is a PlayingCard type - not a String
			                            // System.out.println look to a method to convert aCard to a String
										// A class may provide a method called toString() to return a String
			                            //                  String representation of an object of the class
										// If a toString() is not provided for the class an error occurs when a String is needed
			                            // System.out.println looks for a toString() for the PlayingCard class
										// If a toString() is found for the class of an object it is run to create the String
		
			PlayingCard anotherCard = new PlayingCard(1, "Black", "Clubs");
			System.out.println("anotherCard is: " + anotherCard);
			
			PlayingCard card3 = new PlayingCard(9, "Red", "Hearts");
			System.out.println("card3 is: " + card3);
			
//		    System.out.println("The value in card3 is: " + card3.cardValue);  // error because cardValue is private in PlayingCard
			System.out.println("The value is card3 is: " + card3.getCardValue()); // Use the getter to see the value in the object
		
//			card3.cardValue=1;      // error because cardValue is private in PlayingCard
			card3.setCardValue(1);  // Change the cardValue in card3 to 1 using the setter method
			System.out.println("The value is card3 is: " + card3.getCardValue()); // Use the getter to see the value in the object
			
		}
		
}
		 

