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
	 * static - means there is only copy - shared by all objects
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
	/*************************************************************************************************
	 * members of a class are assigned access modifiers, which indicate who/what has access to member
	 * 
	 *  public - anyone can access the member (data or method)
	 *  private - only members of the class can access the member
	 *  
	 *  Usually data members are private and methods are public
	 *  
	 *  Encapsulation says we protect the data from outside the class access
	 *  We provided methods for those outside to access our data - we control access to our data
	 * 
	 *************************************************************************************************/
	/**************************************************************************************************
	 * final tells Java the value in the variable cannot be changed once it is set
	 * 
	 * Use final to define constants in a programs
	 * 
	 * Constants are data values that don't change
	 * 
	 * Constants are typically used if place of literals to make code easier to read or maintain
	 * 
	 * Constant de-couple the code from the data - loosely couple - data change does not require code change
	 * 
	 * Java naming conventions wants constant names to be in ALL UPPERCASE
	 *************************************************************************************************/
	/**************************************************************************************************
	 * The keyword this represents the current object - the object used to invoke the method
	 * 
	 * this is the object to left of the . when a method is invoked:  object.method(parameters)
	 * 
	 * In a ctor or other methods, it is ONLY REQUIRED when a PARAMETER NAME is the SAME NAME AS DATA MEMBER
	 * 
	 **************************************************************************************************/
	//******************************************************************************************************
	// Class member constants = use the constant names in place of literals to make easier to read/maintain
	//******************************************************************************************************
	private static final int MINVALUE    = 0;             // Minimum valid card value 
	static private final int MAXVALUE    = 13;            // Maximum valid card value
	private final static int JOKER_VALUE = 0;             // Joker value - Not considered valid
	static private final int DEFAULT_VALUE = JOKER_VALUE; // Used if no value provided
		
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
// Constructors are special method in a class that initializes new objects
//
// Constructors have the same name as the class have no return type - NOT EVEN void	
//
// Constructors are frequently overloaded (same name, same behavior,  different parameters)
//              overloading ctors allows users flexibility in defining objects of the class
//
// Provide constructors for each scenario you envision users fill instantiate objects of the class
//
//*********************************************************************************************	
	/**
	 * default constructor - has no parameters
	 */
	public PlayingCard() {     // Used when the user wants an object with default values
		value   = DEFAULT_VALUE;
		suit    = "Clubs";
		showing = false;
		determineColor();         // Use class member method to set the color based on the suit
		shape="Rectangle";
	}
	/**
	 * 4-arg constructor
	 */
	// Used when the user wants to create an object with specific attributes - value, suit, color, showing
	//       All non-specified attributes are set to default values
	public PlayingCard(int value, String suit, String color, boolean showing) {
		setValue(value);          // Use member method to set value in case value passed is invalid
		this.suit    = suit;      // this. is required because the parameter name is the same as data member
		this.showing = showing;   // this. is required because the parameter name is the same as data member
		determineColor();         // Use class member method to set the color based on the suit
		                          //     ignore color sent is as a parameter by user
		shape="Rectangle";        // this. is not required because we are not using a variable with the same name
	}
	/**
	 * 2-arg constructor
	 */
	// Used when the user wants to create an object with specific attributes - value, suit
	//      All non-specified attributes are set to default values
	public PlayingCard(int value, String suit) {
	   	setValue(value);          // Use member method to set value in case value passed is invalid
		this.suit       = suit;
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
	// a method signature - identifies the basic characteristics of a method:
	//
	//                     return-type of what it returns - void means it returns nothing
	//                     name - what is used to invoke/run the method
	//                     parameters it receives (datatype name)
//  access return
//   type  type   name   (parameters)
	public void   setSuit(String suit) {  // Hi!, I'm setSuit and I return nothing(void) and receive a String I call suit
		this.suit = suit;
	}  // no return statement is required because this is void method
	
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
	}                                   //    ignore the color passed by the user
	/**
	 * @param showing is the the value to set PlayingCard showing attribute
	 * @return void
	 */
	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	
//********************************************************************************************
// Additional member methods - to allow more than basic instantiate, get, set for a class
//********************************************************************************************
	/**
	 * Return whether PlayingCard value is what one would consider a face card (value greater than 11)
	 * @return whether card is a face card or not
	 */
	
	public boolean isFaceCard() {
		return (getValue() >= 11);  // use class method to retrieve class data instead of just the name of data
	}                               // in case the class does something to the data we don't want to miss
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
	//
	// Usually the default behavior of the Object class toString() is not desirable
	// So we override the Object toString() with our own for this class
	
	@Override             // Ask the compiler to verify this is a proper override
	public String toString() {
		return "PlayingCard [suit=" + suit + ", value=" + value + ", color=" + color + ", showing=" + showing
				            + "shape=" + shape +"]";
	}
	
	// We need to override the Object class .equals method since the Object class has no idea what it means
	//    for one PlayingCard to be equal to another PlayingCard since WE defined PlayingCard class not Java
	
	//                    data-type   name
	public boolean equals(PlayingCard otherCard) {
	// Since this is our class, we decide what makes a PlayingCard equal to another PlayingCard
	// A PlayingCard is equal to another playing card if value and suit are the same
	//
	//  To use this method one would aPlayingCardObject.equals(aPlayingCardObject)
	//  we coded card4.equals(card5)
	//           this         otherCard (parameter to the method)
	//
	// this always references/means the object to left of the . that invokes the method
	// this means the current object - object used to invoke the method (to left of  the .)
		
		if ((this.value == otherCard.value) && (this.suit.equals(otherCard.suit))) {
		   return true;
		}
		else {
			return false;
		}
	}
		
	}


	
	
	
	
	
	
	
