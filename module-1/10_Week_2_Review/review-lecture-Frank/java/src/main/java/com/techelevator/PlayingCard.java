package com.techelevator;

	/** identifies a special type comment know as a JavaDoc (Javadoc) comment
 	* 
	* A JavaDoc can be used to generate standard formatted documentation
 	* and it shows when in intellisense of the IDE
 	* 
 	* May different annotations (@-word) to identify specific components of code
	* 
	* @param
 	* @returns
 	* @author student
 	*
 	* Refer to the Topic in Cohort Google Doc for a link to more than you want to about JavaDoc
 	*
	 */


public class PlayingCard {
	/*********************************************************************************************
	 * static - means there is only one copy - shared by all objects
	 * 
	 * No matter how many objects of the class exist there is only one copy of the static data
	 * 
	 * static data exists even if no object of the class have been instantiated
	 * 
	 * non-static variables are referred to as instance variables because they exist for each
	 *            instance of the class
	 *            
	 * static data members can only be changed by static methods.
	 * 
	 * static methods can only refer to static data members
	 * 
	 * static method is one that processes static data
	 * 
	 * static methods may be invoked with any object of the class or class name
	 * 
	 *      System.out.println()   -   System is the class name; out is the object; println() is the method
	 * 
	 *      Integer.parseInt()     -   Integer is the class name; parseInt() is the method
	 ********************************************************************************************/
	/****************************************************************************************************
	 * members of a class are assigned access modifiers, which indicate who/what has access to the member
	 * 
	 * public = anyone can access the member (data or method)
	 * private = only members of the class can access the member
	 * 
	 * ***     Usually data members are private and methods are public     ***
	 * 
	 * Encapsulation says we protect the data from outside-the-class access
	 * We provide methods for those outside the class to access our data
	 ****************************************************************************************************/
	/****************************************************************************************************
	 * final - tells Java the value in the variable cannot be changed once it is set
	 * 
	 * use final to define constants in a program
	 * 
	 * constants are data values that don't change
	 * 
	 * constants are typically used in place of literals to make code easier to read or maintain
	 * 
	 * constants to decouple the code from the data - loosely coupled code, data change doesn't require code change
	 * 
	 * Java naming convention wants constant names to be in all UPPERCASE with _ separating words
	 *****************************************************************************************************/
	/*****************************************************************************************************
	 * the keyword 'this' represents the current object - object used to invoke the method
	 * 
	 * 'this' is the object to the left of the . when a method is invoked object.method(parameters)
	 * 
	 * in a constructor it is ONLY REQUIRED when a parameter name is the SAME NAME as a data member
	 *******************************************************************************************************/
	//********************************************************************************************
	// Class member constants - use constant names in place of literals to make the code easier to read and maintain
	//********************************************************************************************
	private static final int MINVALUE    = 0;             // Minimum valid card value 
	private static final int MAXVALUE    = 13;            // Maximum valid card value
	private static final int JOKER_VALUE = 0;             // Joker value - Not considered valid
	private static final int DEFAULT_VALUE = JOKER_VALUE; // Used if no value provided
		
	//********************************************************************************************	
	// Class member data - attributes of an class object
	//********************************************************************************************
	private String  suit;
	private int     value;   // 0=Joker, 11=Jack, 12=Queen, 13=King
	private String  color;
	private boolean showing;
	private String  shape;   // read-only - no Setter or argument to a constructor
	
//********************************************************************************************
// Constructors for the class
//
// Constructors are special methods in a class that initializes new objects
//
// Constructors have the same name as the class have no return type - NOT EVEN void	
//
// Constructors are frequently overloaded (same name, same behavior,  different parameters)
//              overloading ctors allows users flexibility in defining objects of the class
//
// Provide constructors for each scenario you envision users will instantiate objects of your class
//*********************************************************************************************	
	/**
	 * default constructor - has no parameters
	 */
	public PlayingCard() {  // Used when the user wants an object with default values
		value   = DEFAULT_VALUE;
		suit    = "Clubs";
		showing = false;
		determineColor();         // Use class member method to set the color based on the suit
		shape="Rectangle";
	}
	/**
	 * 4-arg constructor
	 */
	// Used when the user wants to create an object with specific attritbutes (value, suit, color, showing)
	//      All non-specified attributes are set to default values
	public PlayingCard(int value, String suit, String color, boolean showing) {
		setValue(value);          // Use member method to set value in case value passed is invalid
		this.suit    = suit;      // this. is required because the parameter name is the same as the data member name
		this.showing = showing;   // this. is required because the parameter name is the same as the data member name
		determineColor();         // Use class member method to set the color based on the suit
		                          //     ignore color sent is as a parameter by user
		shape="Rectangle";        // this. is not required because we are not using a variable
	}
	/**
	 * 2-arg constructor
	 */
	// Used when the user wants to create an object with specifc attributes (value, suit)
	//      All non-specified attributes are set to default values
		public PlayingCard(int value, String suit) {
			setValue(value);          // Use member method to set value in case value passed is invalid
			this.suit       = suit;   // this. is required because the parameter name is the same as the data member name
			this.showing    = false;
			determineColor();         // Use class member method to set the color based on the suit
			shape="Rectangle";
		}
//********************************************************************************************
// Getters	
//********************************************************************************************
	/**
	 * @return the PlayingCard suit attribute
	 */
		
	public String getSuit() {
		return suit;
	}
	
	/**
	 * @return the PlayingCard value attribute
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @return the PlayingCard color attribute
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @return if the PlayingCard showing or not
	 */
	public boolean isShowing() {
		return showing;
	}
	
	/**
	 * @return the shape attribute of the PlayingCard
	 */
	public String getShape() {
		return shape;
	}

//********************************************************************************************
// Setters	
//********************************************************************************************
	/**
	 * @param suit is the value to set PlayingCard suit attribute
	 * @return void
	 */
	
	// a method signature - identifies the basic characteristics of a method
	//            return-type - type of what it returns / void means nothing is returned
	//            name - used to invoke (run) the method
	//            parameters - datatype and name of received parameters
	//
// access  return
// type    type   name  (parameters)
	public void setSuit(String suit) { // Hi! I'm setSuit and I return void and receive a String named suit
		this.suit = suit;
	} // no return is required because this is a void method 
	
	/**
	 * @param value is the the value to set PlayingCard value attribute
	 * <br><br>
	 * If value is outside the valid range of values for a PlayingCard
	 * a default value will be assigned
	 * <br><br>
	 * @return void
	 */
	public void setValue(int value) {
		if (value >= MINVALUE && value <= MAXVALUE) { // Is the value passed in the range for a valid card?
			this.value = value;                       //     Yes - assign it to the value data member
		}
		else {
			this.value = DEFAULT_VALUE;               //     No - assign DEFAULT_VALUE to value data member
			}
	}
	/**
	 * @param color is the the value to set PlayingCard color attribute
	 *        color passed is ignored and actually color is set based on suit
	 * @return void
	 */
	public void setColor(String color) {
		determineColor();               // Use class member method to set the color based on the suit
	}
	/**
	 * @param showing is the the value to set PlayingCard showing attribute
	 * @return void
	 */
	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	
//********************************************************************************************
// Additional member methods
//********************************************************************************************
	/**
	 * Return whether PlayingCard value is what one would consider a face card (value greater than 11)
	 * @return whether card is a face card or not
	 */
	
	public boolean isFaceCard() { 
		return (getValue() >= 11);  // use class method to retrieve class data instead of just the data name
	}                               // in case the class does something to the data that you don't want to miss
	/**
	 * Flip card from showing to not showing
	 * @return void
	 */
	
	public void flip() {
		showing = !showing;  // Reverse the current value of showing true to false; false to true 
	}
	// Determine card color based on suit
	// So we control what color is set and can be sure it is correct for the suit
	// private so only members of the class may use it
	
	private void determineColor() { // set the cardColor based on the cardSuit
		if (suit.equals("Spades") 
		 || getSuit().equals("Clubs")
		 || suit.equals("Joker")) {
			color = "Black";
		} else {
			color = "Red";
		}
	}	
	/**
	 * Display the attributes of a PlayingCard one at a time
	 * @return void
	 */
	// Display the attributes of a PlayingCard 
	public void showCard() {
		System.out.println("      Suit: " + getSuit());
		System.out.println("     Value: " + getValue());
		System.out.println("     Color: " + getColor());
		System.out.println("   Showing: " + isShowing());
		System.out.println("     Shape: " + getShape());
//		System.out.println("-----------------------------------------------------------------------------------");
	}

//********************************************************************************************
// Overrides - Define new behavior that is different than ancestor behavior
//             Overriding methods have same name, same parameters, but different behavior	
//********************************************************************************************

	/**
	 * Construct a String representation of the object
	 * @return String containing the attributes of the object
	 */
	
	// The default toString() is the Object class toString() for all classes
	
	// Usually the default behavior of the object class toString() is not what you want
	// So we Override the Object toString() with our own for this class
	
	@Override             // Ask the compiler to verify this is a proper override
	public String toString() {
		return "PlayingCard [suit=" + suit + ", value=" + value + ", color=" + color + ", showing=" + showing
				            + "shape=" + shape +"]";
	}
	// We need to override the Object classes .equals method since the Object class has no idea what it means
	//    for one PlayingCard to be equal to another PlayingCard since WE defined PlayingCard, not Java
	
	//                    datatype    name
	public boolean equals(PlayingCard otherCard) {
	// Since this is our class, we decide what makes a PlayingCard equal to another PlayingCard
	// A PlayingCard is equal to another PlayingCard if the value and suit are the same
		
	// To use this method one would aPlayingCard.equals(aPlayingCardObject)
	// We coded card4.equals(card5)
	//           this        otherCard - this is the parameter
	// 'this' always references/means the object to left of the . that invokes the method	
	// 'this' means the current object - object used to invoke the method
		
		if((this.value == otherCard.value) && (this.suit.equals(otherCard.suit))) {
			return true;
		} else {
			return false;
		}
	}
}


