package com.techelevator;
/***************************************************************************************************
 * Class to Simulate an PokerHand using and American PlayingCard Hand
 ***************************************************************************************************/

public class PokerHand  extends CardHand { // subclass of CardHand
	                                       // all it needs to do is whatever is different from a CardHard    
	
/***************************************************************************************************
 * Member Data
 ***************************************************************************************************/
	
/***************************************************************************************************
* Constants
***************************************************************************************************/
	public static final int NUMBERCARDSINHAND = 5;    // Number of cards in PokerJand

	public static enum PokerHandRanking {      // Hand rankings from low to high
		NOTRANKED, HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH, ROYALSTRAIGHTFLUSH
	};

/***************************************************************************************************
* Instance data
***************************************************************************************************/
	private PokerHandRanking handRank;         // Current rank of hand

	
/***************************************************************************************************
 * Member methods
 ***************************************************************************************************/
	
	/***************************************************************************************************
	 * Constructor
	 ***************************************************************************************************/
	public PokerHand() {   // Default constructor
		handRank = PokerHandRanking.NOTRANKED;
	}
	/***************************************************************************************************
	 * getters
	 ***************************************************************************************************/
	public PokerHandRanking gethandRank() {
		return handRank;
	}
	/***************************************************************************************************
	 * Miscellaneous Member Methods
	 ***************************************************************************************************/
	public void clearHand() {   // Remove all cards from Hand
		super.clearHand();
	}
// this defines the abstract method dealHand() specified in the superclass
// the subclass must define any abstract class that's in the superclass
	public void dealHand(CardDeck aDeck) {             // Deal a Hand
		for (int i=0; i < NUMBERCARDSINHAND; i++ ) {   // For the number of cards in hand
			super.aHand.add(aDeck.dealCard());         //     Add a card from deck passed to hand
		}			
	}
		
	/***************************************************************************************************
	 * Overrides
	 ***************************************************************************************************/

	@Override
	public void show() { // The subclass method - not required because we're not changing 
		                 // the behavior of the superclass method, just executing
		                 // it's included to show how a subclass would call a superclass method
		super.show();
	}

	/****************************************************************************************************
	 * Determine Poker Rank of Hand
	 *  
	 *  Note this is V0 version of the method - it could possibly be refactored to be better, but it works
	 ****************************************************************************************************/
	public PokerHandRanking rankHand() {
		this.sortByValue();
		
		handRank = PokerHandRanking.HIGHCARD;
		
		if (hasFourOfAKind()) {
				handRank = PokerHandRanking.FOUROFAKIND;
		}
		else if (hasThreeOfAKind()) {
				if (hasOnePair()) {
					handRank = PokerHandRanking.FULLHOUSE;
				}
				else handRank = PokerHandRanking.THREEOFAKIND;
				}			
			else if (hasTwoPair()) {
				handRank = PokerHandRanking.TWOPAIR;
		}
				else if (hasOnePair()) {
					if (hasThreeOfAKind()) {
			    	handRank = PokerHandRanking.FULLHOUSE;
					}
					else handRank = PokerHandRanking.PAIR;
		}
		
		if (hasFlush()) {
			handRank = PokerHandRanking.FLUSH;
			if (hasStraight()) {
				handRank = PokerHandRanking.STRAIGHTFLUSH;
			}
		}
		if (hasStraight()) {
			handRank = PokerHandRanking.STRAIGHT;
			if (hasFlush()) {
				handRank = PokerHandRanking.STRAIGHTFLUSH;
			}
		}
		if (((this.aHand.get(0).getValue() == PlayingCard.CardValue.ONE)
		   &&(this.aHand.get(4).getValue() == PlayingCard.CardValue.KING)
		   &&(this.aHand.get(3).getValue() == PlayingCard.CardValue.QUEEN)
		   &&(this.aHand.get(2).getValue() == PlayingCard.CardValue.JACK)
		   &&(this.aHand.get(1).getValue() == PlayingCard.CardValue.TEN))
		   && hasFlush())
		  {	
			handRank = PokerHandRanking.ROYALSTRAIGHTFLUSH;
		}	
		return handRank;
	}
	/***************************************************************************************************
	 * Methods to rank a hand
	 * 
	 * Note these are V0 versions of the methods - they could possibly be refactored to be better,
	 *      but they work
	 ***************************************************************************************************/

	private boolean hasOnePair() {
		for (int i = 0; i < NUMBERCARDSINHAND - 1; i++) {
			PlayingCard currentCard = new PlayingCard();
			currentCard = getHand().get(i);
			if (currentCard.getValue() == getHand().get(i + 1).getValue()) {
				return true;
			}
		}
		return false;
	}

	private boolean hasTwoPair() {
		for (int i = 0; i < NUMBERCARDSINHAND - 1; i++) {  // loop through the hand one card at a time
			PlayingCard currentCard = new PlayingCard();   // define a PlayingCard
			currentCard = getHand().get(i);                // assign it the current card in the hand
			if ((currentCard.getValue() == getHand().get(i + 1).getValue())) {
				for (int j = i + 2; j < NUMBERCARDSINHAND - 1; j++) { // loop through the rest of the hand, comparing the card
					currentCard = getHand().get(j);                   //      we picked to the rest of the cards in the hand
					if ((currentCard.getValue() == getHand().get(j + 1).getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean hasThreeOfAKind() {
		for (int i = 0; i < NUMBERCARDSINHAND - 2; i++) {
			PlayingCard currentCard = new PlayingCard();
			currentCard = getHand().get(i);
			if ((currentCard.getValue() == getHand().get(i + 1).getValue())
					&& (currentCard.getValue() == getHand().get(i + 2).getValue())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasFourOfAKind() {
		for (int i = 0; i < NUMBERCARDSINHAND - 3; i++) {
			PlayingCard currentCard = new PlayingCard();
			currentCard = getHand().get(i);
			if ((currentCard.getValue() == getHand().get(i + 1).getValue())
					&& (currentCard.getValue() == getHand().get(i + 2).getValue())
					&& (currentCard.getValue() == getHand().get(i + 3).getValue())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasFlush() {
		if ((getHand().get(0).getSuit() == getHand().get(1).getSuit())
				&& (getHand().get(0).getSuit() == getHand().get(2).getSuit())
				&& (getHand().get(0).getSuit() == getHand().get(3).getSuit())
				&& (getHand().get(0).getSuit() == getHand().get(4).getSuit())) {
			return true;
		}
		else
			return false;
	}

	private boolean hasStraight() {
		if ((getHand().get(1).getIntValue()) == ((getHand().get(0).getIntValue())+1)
		 && (getHand().get(2).getIntValue()  == ((getHand().get(1).getIntValue())+1)
		 && (getHand().get(3).getIntValue()  == ((getHand().get(2).getIntValue())+1)
		 && (getHand().get(4).getIntValue()  == ((getHand().get(3).getIntValue())+1))))) {
			return true;
		}
		else 		
			return false;
	}
	

}
