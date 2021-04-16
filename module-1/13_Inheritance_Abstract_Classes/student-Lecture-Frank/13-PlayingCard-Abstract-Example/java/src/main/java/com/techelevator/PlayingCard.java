package com.techelevator;
// abstract is an attribute applied to a class that can never be instantiated
// an abstract class can only be a superclass, it provides a generic description of an object,
// the subclasses refine the description by adding/removing variables and methods
public abstract class PlayingCard {
	
	// protected violates the strict interpretation of encapsulation
	
	protected int value;   // protected - only members of this class and subclass can access
	private String color;  // private   - only members of this class can access
	private String suit;     
	
	
	public PlayingCard(int value, String suit, String color) {
		this.value = value;
		this.suit  = suit;
		this.color = color;
	}
	
	public int getValue() {
		return value;
	}
	public String getColor() {
		return color;
	}
	public String getSuit() {
		return suit;
	} // the 'this.' keyword always means the current object, the obj to the left of the . that invoked this method
	public void setValue(int value) {
// currentObj.value = parameter-value              anObj.setValue(4) (anObj = this.) , (4 = value)
		this.value = value; // the object used to invoke said value should be assigned the parameter value
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	@Override   // Ask compiler to verify this is a proper Override - same name, same parameters, same return-type
	public String toString() {
		return "PlayingCard [value=" + value + ", color=" + color + ", suit=" + suit + ", getValue()=" + getValue()
				+ ", getColor()=" + getColor() + ", getSuit()=" + getSuit() + "]";
	}
	
	// Override the Object class' .equals method because it doesn't know how to compare PlayingCard objects
	@Override   // Ask compiler to verify this is a proper Override - same name, same parameters, same return-type
	// since the Object class' .equals receives an Object and returns a boolean, we must do the same thing
	public boolean equals(Object obj) {  // Receive any type of Object
		if (this == obj) {  // If compare a PlayingCard to itself - this. represents the object to the left of .equals
			return true;
		}
		if (obj == null) {  // If compare to an undefined PlayingCard
			return false;
		}
		if (getClass() != obj.getClass()) {  // If compare to an object of a different class
			return false;                    // getClass() return the Class of an object
		}
		PlayingCard other = (PlayingCard) obj;  // Define a PlayingCard for the object that was passed
		if (color != other.color) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}
// an abstract method is defined when a superclass wants to ensure any subclass overrides the method
// we don't know what the abstract method should do in the superclass, we just know we want the method
	public abstract void showCard();  // subclasses MUST override this method
	// use a ; instead of {} when defining an abstract method
		
}
