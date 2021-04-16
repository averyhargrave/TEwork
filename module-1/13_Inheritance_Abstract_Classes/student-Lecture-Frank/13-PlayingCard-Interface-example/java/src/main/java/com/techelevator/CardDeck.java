package com.techelevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

// Simulate a deck of PlayingCards

public class CardDeck {
	// LinkedList is a Queue - first in, first out structure
	LinkedList<PlayingCard> deckOfCards = new LinkedList<PlayingCard>();
	private int numCardsInDeck = 54;

	public CardDeck() {
		resetDeck(true); // reset deck with jokers
	}

	public void showDeck() {
		System.out.println("Number of Cards in Deck: " + deckOfCards.size());
		for (PlayingCard aCard : deckOfCards) {
			System.out.println(aCard);
		}
	}

	public boolean anyCardsInDeck() {
		if (deckOfCards.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public PlayingCard dealCard() {
		if (deckOfCards.size() > 0) {    // as long as there are cards in the deck,
			return deckOfCards.remove(); // return the top/first card, remove it from the queue
		}
		else {
			 return null; // if no cards left in deckOfCards, return null
		}
	}

	public void resetDeck(boolean withJoker) {
		deckOfCards.clear();  // remove all cards in the deck

		for (int i = 1; i < 14; i++) {
			deckOfCards.add(new PlayingCard(i, "CLUBS")); // add all CLUBS to the deck
		}
		for (int i = 1; i < 14; i++) {
			deckOfCards.add(new PlayingCard(i, "HEARTS")); // add all HEARTS to the deck
		}
		for (int i = 1; i < 14; i++) {
			deckOfCards.add(new PlayingCard(i, "Spades")); // add all SPADES to the deck
		}
		for (int i = 1; i < 14; i++) {
			deckOfCards.add(new PlayingCard(i, "DIAMONDS")); // add all DIAMONDS to the deck
		}

		if (withJoker) { // if they want Jokers in the deck, add them
			deckOfCards.add(new PlayingCard(0, "JOKER"));
			deckOfCards.add(new PlayingCard(0, "JOKER"));
		}
	}

	public void shuffleDeck() { // shuffle the cards 
		resetDeck(false);       // give me a new deck of cards without jokers
		Collections.shuffle(deckOfCards); // use the Collections class .shuffle method to shuffle the cards
	}

}