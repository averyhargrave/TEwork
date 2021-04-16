package com.techelevator;

/***************************************************************************************************
 * Class to Simulate an American Playing Card
 ***************************************************************************************************/

public class PlayingCard {
	/***************************************************************************************************
	 * Define constants to represent card attributes
	 * 
	 * public is OK since they are constants and cannot be changed
	 * 
	 * static so it can be referenced using the class name. ie. no object required
	 * 
	 * enum - define a set of constant values that may be referenced as a data type
	 *        make the code easier to read because you need to use enum instead of magic numbers
	 *        self validating - Java will not let an enum have an invalid value
	 *        since enums are constants, their values are always in UPPERCASE to follow conventions
	 *        enums are assigned a numeric value that is used internally by Java
	 *        	  enums values start at 0
	 ***************************************************************************************************/
//  access      type name
	public static enum CardColor { // Define a set of values that are valid for the enum type       
		BLACK, RED                 // Only values allowed in the enum type CardColor     
	};

	public static enum CardSuit {          // public is OK since they are constants and cannot be changed
		SPADE, CLUB, HEART, DIAMOND, JOKER // static so it can be referenced using the class name. ie. no object required
	};

	public static enum CardValue {
	//    0     1    2     3     4     5     6     7      8     9     10   11     12     13
		JOKER, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	};

	/***************************************************************************************************
	 * Define constants to represent defaults for card attributes
	 * 
	 * protected so subclasses may access is OK since they are constants and cannot be changed
	 * 
	 * static so it can be referenced using the class name. ie. no object required
	 ***************************************************************************************************/
	
	protected static final CardValue DEFAULTCARDVALUE = CardValue.JOKER; 
	protected static final CardColor DEFAULTCOLOR     = CardColor.BLACK;
	protected static final CardSuit  DEFAULTSUIT      = CardSuit.JOKER;
	/***************************************************************************************************
	 * Member data
	 * 
	 * private to protect as prescribed by encapsulation - method must be used to access the data 
	 ***************************************************************************************************/
	
	private CardValue value;
	private CardColor color;
	private CardSuit suit;

	/***************************************************************************************************
	 * Default constructor
	 ***************************************************************************************************/
	
	public PlayingCard() 
	{
		value = DEFAULTCARDVALUE;
		suit  = DEFAULTSUIT;
		setColor(suit);     // Set color in object based on suit - NOTE: suit must have a value before this
	} 
	/***************************************************************************************************
	 * 2-arg (CardValue and CardSuit) constructor
	 ***************************************************************************************************/
	public PlayingCard(CardValue value, CardSuit suit) {
		this.value = value;   // Set value in object to value passed as argument
		setColor(suit);       // Set color in object based on suit passed as argument
		this.suit = suit;     // Set suit in object to suit passed as argument
	}
	/***************************************************************************************************
	 * 2-arg (int and CardSuit) constructor
	 ***************************************************************************************************/
	public PlayingCard(int value, CardSuit suit) {
		this.value = setValue(value);  // Set value in object based on int value passed as argument
		setColor(suit);                // Set color in object based on suit passed as argument
		this.suit = suit;              // Set suit in object to suit passed as argument
	}
	/***************************************************************************************************
	 * getter methods
	 ***************************************************************************************************/
	public CardValue getValue() { // Return the CardValue stored in the object
		return value;
	}

	public int getIntValue() {    // Return integer value of CardValue stored in the object
		return value.ordinal();   // .ordinal returns the internal value assigned to an enum
	}
	
	public CardColor getColor() { // Return CardColor stored in the object
		return color;
	}
	
	public CardSuit getSuit() {   // Return the CardSuit stored in the object
		return suit;
	}
	/***************************************************************************************************
	 * setter methods
	 ***************************************************************************************************/
	public CardValue setValue(int ivalue) {  // Set the CardValue based on an int value
		// A switch is an alternative to a series of if/else statments
		// Each case statement is evaluated in the order coded one at a time until a case is true
		// When a case is true, the statment following the case, and ALL STATEMENTS UNTIL THE END OF THE SWITCH
		//     are executed unless the case contains a return - ends the method
		//                                         or a break - breaks out of the switch
		
		// The following code written as a series of if/else:
		
		// if(ivalue == 1) {
		//     return CardValue.ONE;
		//     }
		// else if(ivalue == 2) {
		//     return CardValue.TWO;
		//     }
		// else if(ivalue == 3) {
		//     return CardValue.THREE;
		//     }
		// else if(ivalue == 4) {
		//     return CardValue.FOUR;
		//     }
		switch (ivalue) { // if(ivalue)...
		case 1:           // equals 1 - case indicates what value needs to be in the variable to be true
			return CardValue.ONE;
		case 2:           // equals 2
			return CardValue.TWO;
		case 3:           // equals 3
			return CardValue.THREE;
		case 4:
			return CardValue.FOUR;
		case 5:
			return CardValue.FIVE;
		case 6:
			return CardValue.SIX;
		case 7:
			return CardValue.SEVEN;
		case 8:
			return CardValue.EIGHT;
		case 9:
			return CardValue.NINE;
		case 10:
			return CardValue.TEN;
		case 11:
			return CardValue.JACK;
		case 12:
			return CardValue.QUEEN;
		case 13:
			return CardValue.KING;
		default:
			return CardValue.JOKER;
		}
	}

	private void setColor(CardSuit suit) {  // Set the color based on the suit of the object
		switch (suit) {
		case SPADE:   // stack cases to simulate an ||
		case CLUB:    // spade and club are stacked
			this.color = CardColor.BLACK;
			break;    // required to skip the rest of the statements in the switch and resume at the end of the switch
		case DIAMOND:
		case HEART:
			this.color = CardColor.RED;
			break;
		default:      // if no cases are true
			this.color = DEFAULTCOLOR;
		}
	}

	public void setSuit(CardSuit suit) {  // Set the suit of the object 
		this.suit = suit;
	}

	/***************************************************************************************************
	 * Object super class overrides
	 ***************************************************************************************************/
@Override   // Ask compiler to assure this is a valid super class override

	public String toString() {                         // Return a String representation of the object
		//-------------------------------------------------------------------------------------------------
		// StringBuffer is a mutable version of String
		//-------------------------------------------------------------------------------------------------
		StringBuffer stringCard = new StringBuffer();  // Define an object to hold String version of object
// use .append() rather than + to concatenate with StringBuffer
		int firstColumnSize = 16;                      // position of first tab position of screen line 

		stringCard.append("Value: " + value);          // Add literal to StringBuffer
		stringCard.append(" (" + getIntValue() + ")"); // Add integer value of CardValue to StringBuffer
		if (stringCard.length() < firstColumnSize) {   // If current StringBuffer size less than first tab position
			stringCard.append("\t");                   //     tab to first tab position
		}
		stringCard.append("\tSuit: " + suit);          // Add tab and suit value
		stringCard.append("\tColor: " + color);        // Add tab and color value
		return stringCard.toString();                  // Convert StringBuffer to String and return
	}

@Override   // Ask compiler to assure this is a valid super class override

public boolean equals(Object otherObject) {   // Compare two PlayingCards for equality - note generic Object parameter
	
		if (otherObject == this) {   // if object being compared to itself
			return true;             // objects are equal
		}
		else {
			if ((otherObject instanceof PlayingCard)) {            // If the object being compared to is the same class as object 
				PlayingCard otherCard = (PlayingCard) otherObject; // Define a PlayingCard object from object being compared to
				return (this.value == otherCard.value && this.color == otherCard.color && this.suit == otherCard.suit);
			}
	    return false;    // Required so Eclipse realizes we are returning a boolean
		}
		
}
@Override   // Ask compiler to assure this is a valid super class override

	public int hashCode() {      // Generate hashCode for object if Java needs one e.g. for a HashMap
	                             // HashCode is a unique value representing an instance of an object
		int hashValue = 17;      // A prime number used in calculating the HashCode
		int primeMultipler = 59; // A prime numbet used in calculating the HashCode

		hashValue = hashValue * primeMultipler + value.ordinal();  // Same values used in equals() 
		hashValue = hashValue * primeMultipler + suit.ordinal();   //     should be used in the 
		hashValue = hashValue * primeMultipler + color.ordinal();  //        HashCode method
		return hashValue;
	}

	/***************************************************************************************************
	 * Miscellaneous class methods
	 ***************************************************************************************************/
	public void showCard() {                 // Display an instance of a PlayingCard
		System.out.println(this.toString());
	}

	public void showCardWithHash() {          // Display an instance of a PlayingCard wirh HashCode
		System.out.println(this.toString() + "\thashCode: " + hashCode());
	}
}
