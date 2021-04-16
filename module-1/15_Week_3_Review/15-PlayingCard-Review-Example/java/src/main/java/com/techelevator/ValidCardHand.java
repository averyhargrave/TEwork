package com.techelevator;
// An interface defines methods that must be coded if a class implements this interface

// If a class wants to be a type-of this interface, it must provide these behaviors
public interface ValidCardHand {
	
	public void clearHand();                // Remove all cards from a hand
	public void show();                     // Display all cards in a hand 
	public void dealHand(CardDeck aDeck);   // Fill a hand from a CardDeck object
	public void addCard(PlayingCard aCard); // Add card to a hand
}
