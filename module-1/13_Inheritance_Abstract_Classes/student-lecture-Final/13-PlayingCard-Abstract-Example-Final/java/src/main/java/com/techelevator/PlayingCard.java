package com.techelevator;
// abstract is an attribute applied to a class that can never e instantiated
// an abstract class can only be a super class
// It provided a generic description of an object, the subclass refined the description by adding/removing valriables and methods
public abstract class PlayingCard {
	
	// protected violates the strict interpretation of encapsulation
	
	protected int value;       // only members of this class and any subclass can access
	private String color;      // only members of this class can access
	private String suit;       // only members of this class can access 
	
	
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
	}
	// the this keyword always means the current object - object to left of . that invoked the method
	public void setValue(int value) {
//currentObject.value = parameter-value       anObject.setValue(4)
		this.value = value;       // the object used to invoke setValue value should be assigned the parameter value
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

	
	// Override the Object class equals because it doesn't like we want it to - it doesn't know how to compare PlayingCard
	@Override   // Ask compiler to verify this is a proper Override - same name, same parameters, same parameter order
	// since the Object class equals receives an Object and returns a boolean, we must do the same thing
	public boolean equals(Object obj) {  // Receive any type of Object
		if (this == obj) {  // If compare a PlayingCard to itself - this represents the object to left of .equals
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
	// an abstract method is defined when a super wants to insure any subclass defines/overrides the method
	// we don't know what the abstract method should do in the super class, we just know want the method
    public abstract void showCard(); // subclass MUST override this method
                                     // code a ; instead of {} when defining an abstract method
		
}
