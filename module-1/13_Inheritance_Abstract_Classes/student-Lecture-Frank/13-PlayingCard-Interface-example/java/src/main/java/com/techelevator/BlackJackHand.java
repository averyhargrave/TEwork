package com.techelevator;

import java.util.ArrayList;

// a BlackJackHand is a type of CardHard - "type of" == interface

public class BlackJackHand	 implements CardHand {

	private int numCardsInHand = 2; // Starting number of cards in the hand
	
	private ArrayList<PlayingCard> aHand; // represent the hand as an ArrayList
	
	public BlackJackHand() {
		aHand = new ArrayList<PlayingCard>(numCardsInHand);
	}
	
	// We need to override the methods that are required by CardHard interface
	
	@Override
	public ArrayList<PlayingCard> getHand() {
		return aHand;
	}

	@Override
	public void addCard(PlayingCard aCard) {
		aHand.add(aCard); // Use the ArrayList .add method to add a card to the hand
		numCardsInHand++;
	}

	@Override
	public void emptyHand() {
		aHand.clear(); // Use the ArrayList .clear method to empty the hand

	}

	@Override
	public void show() {
		for (PlayingCard aCard : aHand)
		{
			System.out.println(aCard);
		}
			return;
	}
	
	// Methods to provide additional functionality beyond what's required by a CardHand
	// do not code @override to ask the compiler to ask that they are valid overrides
	// these are not overrides, these are methods specific to this BlackJackHand class
	public void dealHand(CardDeck aDeck) {
		for (int i=0; i < numCardsInHand; i++ ) {
			aHand.add(aDeck.dealCard());
		}	
	}
	
}
	
