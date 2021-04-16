package com.techelevator;

import java.util.ArrayList;

// Define the behaviors that must be implemented to be consider a CardHand

public interface CardHand {   // if you want to be considered a CardHand you MUST define these methods(behaviors)
	
	public ArrayList<PlayingCard> getHand();   // returns the cards in the hand as an ArrayList

	public void addCard(PlayingCard aCard);    // receive a PlayingCard and add to the hand
	
	public void emptyHand();                   // remove all PlayingCards from the hand

	public void show();                        // display all the cards in the hand

}
