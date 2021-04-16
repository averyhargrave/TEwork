package com.techelevator;

import java.util.ArrayList;

// Define the behaviors that must be implemented to be considered a CardHand

public interface CardHand {  // if you want to be considered a CardHand, you must define these methods/behaviors
	
	public ArrayList<PlayingCard> getHand(); // returns the cards in the hand as an ArrayList

	public void addCard(PlayingCard aCard);  // receive a PlayingCard and add it to the hand
	
	public void emptyHand();                 // empty the hand of all PlayingCards

	public void show();                      // show all PlayingCards in the hand

}
