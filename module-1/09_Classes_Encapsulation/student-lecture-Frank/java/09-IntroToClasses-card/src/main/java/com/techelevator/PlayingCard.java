package com.techelevator;


// public indicates that anyone may use this class - instantiate objects of this class
public class PlayingCard {

/*  This is a class to represent a playing card
	
    A class has data (variables) and behaviors (methods)
 
	We have identified the following characteristics for a playing card:
			value
			suit
			color
			shape
			face up / face down
	
	The characteristics or data for a class may also be called attributes
	
	The attributes for a class are defined as member variables
	A variable is a "member variable" if it's defined in a class
	
	To define a member variable: access - public - anyone can access the variable
	                                      private - only members of the class can access the variable
	                             datatype
	                             name
	                             
	*using private on variables implements encapsulation*                            
	encapsulation means we are protecting the data from access outside the class                           
*/
/*******************************************************************************************************
 * Data members of the classs 	
 *******************************************************************************************************/
	private int     cardValue; // Ace=1, Jack=11, Queen=12, King=13
	private String  cardSuit;
	private String  cardColor;
	private String  cardShape;
	private boolean faceUp;
		
/*******************************************************************************************************
 * Methods for the classs 	
 *******************************************************************************************************/
//------------------------------------------------------------------------------------------------------
//Constructors - initialize new objects - run automatically by Java when the new objects of the class are created
//------------------------------------------------------------------------------------------------------
	
	// Every class should provide constructor methods to initialize objects of the class
// If constructors are not provided, Java runs a default constructor (ctor) to initialize your object
// The Java default constructor sets objects=null, numerics=0, and booleans=false
	
// Constructors are special methods in a class: no return type (not even void), name is Same as class
	
// method overload is method with the same name as another method, but with different parameters	
// constructors are always overloaded methods
	
	
	PlayingCard() { // default constructor - takes no arguments and assigns default values to the data members
		
		cardValue = 13;
		cardSuit  = "Diamonds";
		cardColor = "red";
		cardShape = "circle";
		faceUp    = true;
	}
	
// to allow a user to define any PlayingCard we need to create a
// constructor that takes parameters for the data values
	PlayingCard(int value, String suit, String color) { // 3 arg constructor - it takes 3 parameters
		cardValue = value;  // set the cardValue to the parameter passed
		cardSuit  = suit;  // set the cardSuit to the parameter passed
		cardColor = color;  // set the cardColor to the parameter passed
		cardShape = "circle";  // cardShape will always be "circle"
		faceUp    = true;  // faceUp will always be true
	}
	
//------------------------------------------------------------------------------------------------------
// Getters/Setters = methods that allow the user to retrieve value from the object
//   				 and change values in the object
	
// Due to encapsulation (making data members private) there is no access to data from outside the class

// Getters/Setters allow us to control access from outside the class through methods
	
// Not all data members need to have getters/setters - you can limit access if you don't provide them
//------------------------------------------------------------------------------------------------------	
	

	
	
//------------------------------------------------------------------------------------------------------
// Additional class methods to support the class
//------------------------------------------------------------------------------------------------------
	
// Getters/Setters generated by Eclipse follow the convention for getter/setter names
//       getVariableName         setVariableName         isVariableName (boolean getter)
public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

	public String getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(String cardSuit) {
		this.cardSuit = cardSuit;
	}

	public String getCardColor() {
		return cardColor;
	}

	public void setCardColor(String cardColor) {
		this.cardColor = cardColor;
	}

	public String getCardShape() {
		return cardShape;
	}

	public void setCardShape(String cardShape) {
		this.cardShape = cardShape;
	}

	public boolean isFaceUp() { // conventional name for a boolean getter
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	// toString() which will return a PlayingCard object as a String
	public String toString() {
		return "PlayingCard [cardValue=" + cardValue + ", cardSuit=" + cardSuit + ", cardColor=" + cardColor
				+ ", cardShape=" + cardShape + ", faceUp=" + faceUp + "]";
	
		
	
	}
		
}
