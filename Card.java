/**
 * Card.java
 *
 * <code>Card</code> represents a playing card.
 */
public class Card {

	/**
	 * String value that holds the suit of the card
	 */
	private String suit;

	/**
	 * String value that holds the rank of the card
	 */
	private String rank;

	/**
	 * int value that holds the point value.
	 */
	private int pointValue;


   /**
	 * Creates a new <code>Card</code> instance.
	 *
	 * @param cardRank  a <code>String</code> value
	 *                  containing the rank of the card
	 * @param cardSuit  a <code>String</code> value
	 *                  containing the suit of the card
	 * @param cardPointValue an <code>int</code> value
	 *                  containing the point value of the card
	 */
	public Card(String cardRank, String cardSuit, int cardPointValue) {
		suit = cardSuit;
		rank = cardRank;
		pointValue = cardPointValue;
	}



	/**
	 * Accesses this <code>Card's</code> suit.
	 * @return this <code>Card's</code> suit.
	 */
	public String suit() {
		return suit;
   }

	/**
	 * Accesses this <code>Card's</code> rank.
	 * @return this <code>Card's</code> rank.
	 */
	public String rank() {
		return rank;
	}

   /**
	 * Accesses this <code>Card's</code> point value.
	 * @return this <code>Card's</code> point value.
	 */
	public int pointValue() {
		return pointValue;
	}

	public void setPointValue(int newPointVal) {
		pointValue = newPointVal;
	}

	/** Compare this card with the argument.
	 * @param otherCard the other card to compare to this
	 * @return true if the rank, suit, and point value of this card
	 *              are equal to those of the argument;
	 *         false otherwise.
	 */
	public boolean matches(Card otherCard) {
		if (otherCard.suit().equals(suit)) {
			if (otherCard.rank().equals(rank)) {
				if (otherCard.pointValue() == pointValue) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean matchesSuitAndRank(Card otherCard) {
		if (otherCard.suit().equals(suit)) {
			if (otherCard.rank().equals(rank)) {
				return true;
			}
		}
		return false;
	}	

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 *     "[Rank] of [Suit] (point value = [PointValue])".
	 * This provides a useful way of printing the contents
	 * of a <code>Deck</code> in an easily readable format or performing
	 * other similar functions.
	 *
	 * @return a <code>String</code> containing the rank, suit,
	 *         and point value of the card.
	 */
	@Override
	public String toString() {
		String pointValueString = Integer.toString(pointValue);
		return rank+" of "+suit;
//	return rank + " of " + suit + " (point value = " + pointValueString + ")";

	}












	/** shows who played a certain card...
		* returns a Hand
		*/

	private String playedBy;

	public void playedBy(String player){
		playedBy = player;
	}

	public String whoPlayed() {
		return playedBy;
	}


	//adding cards in a way that I can loop through
	public Card(int cardSuit, int cardPointValue) {
		if (cardSuit == 0) {
			suit = "clubs";
		}
		if (cardSuit == 1) {
			suit = "diamonds";
		}
		if (cardSuit == 2) {
			suit = "hearts";
		}
		if (cardSuit == 3) {
			suit = "spades";
		}
		if (cardPointValue == 11) {
			rank = "j";
		}
		if (cardPointValue == 12) {
			rank = "q";
		}
		if (cardPointValue == 13) {
			rank = "k";
		}
		if (cardPointValue == 14) {
			rank = "a";
		}
		if (cardPointValue == 10) {
			rank = "t";
		}
		if (cardPointValue < 10) {
			rank = Integer.toString(cardPointValue);
		}

		pointValue = cardPointValue;
	}



	public Card(String cardRank, String cardSuit) {
		suit = cardSuit;
		
		if (cardRank.toLowerCase().equals("j")) {
			pointValue = 11;
		
		} else if (cardRank.toLowerCase().equals("q")) {
			pointValue = 12;
		
		} 
		else if (cardRank.toLowerCase().equals("k")) {
			pointValue = 13;
			
		} else if (cardRank.toLowerCase().equals("a")) {
			pointValue = 14;
			
		} else if (cardRank.toLowerCase().equals("t")) {
			pointValue = 10;
			
		} else {	
			pointValue = Integer.parseInt(cardRank);
		} 
		//System.out.println("I made it here");
		rank = cardRank;
	}

}
