import java.util.*;
import java.lang.Object;

public class Hand {
	private ArrayList<Card> cards;

	private ArrayLizt<Card> c;
	private ArrayLizt<Card> d;	
	private ArrayLizt<Card> h;
	private ArrayLizt<Card> s;

	private boolean isLeading;

	/* pos is 0, 1, 2, or 3. dictates the order of the hands.
	 * 0 is North, 1 is East, 2 is South, 3 is West.
	 * there's gotta be an easier way to do this lol. 
	 */
	private int pos;

	// will be name of suit or null for NT.
	private String trumpSuit = null;


	private ArrayLizt<Card>[] listOfSuits = new ArrayLizt[4];
	/*yawei is a stupid name so I'm changing yawei to a more
	  to a more conservative name.
	 */
	



	public Hand() {
		cards = new ArrayList<Card>();
		c = new ArrayLizt<Card>("clubs");
		d = new ArrayLizt<Card>("diamonds");
		h = new ArrayLizt<Card>("hearts");
		s = new ArrayLizt<Card>("spades");
		listOfSuits[0] = c;
		listOfSuits[1] = d;
		listOfSuits[2] = h;
		listOfSuits[3] = s;
	}


	// Sets pos which is basically just N, S, E, or W
	public Hand(int num) {
		cards = new ArrayList<Card>();
		c = new ArrayLizt<Card>("clubs");
		d = new ArrayLizt<Card>("diamonds");
		h = new ArrayLizt<Card>("hearts");
		s = new ArrayLizt<Card>("spades");
		listOfSuits[0] = c;
		listOfSuits[1] = d;
		listOfSuits[2] = h;
		listOfSuits[3] = s;
		pos = num;
	}

	public int getPos() {
		return pos;
	}

	public void add(Card nextCard) {
		cards.add(nextCard);
	}

	
	//checks to see if given ghost of card is in hand
	public boolean isInHand(Card checkcheckSplit){
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).matches(checkcheckSplit)) {
				return true;
			}
		}
		return false;
	}


	public void sortCards() {
		Sorter kek = new Sorter();
		//this line sorts by value, not suit
		cards = kek.sortValues(cards);
		//sorts suit
		for (int i = 0; i < cards.size(); i++){
			String suit = cards.get(i).suit();
			for (int k = 0; k < 4; k++) {
				if (listOfSuits[k].getName().equals(suit)) {
					listOfSuits[k].add(cards.get(i));
				}
			}
		}
	}

	public void display() {
		System.out.println("Hand: ");
		System.out.println(listOfSuits[0].toString());
		System.out.println(listOfSuits[1]);
		System.out.println(listOfSuits[2]);
	 	System.out.println(listOfSuits[3]);
	}

	public boolean isLeading(){
		return isLeading;
	}

	public int length() {
		return cards.size();
	}



	//this searches all the cards in the hand and pops it
	// similsar to getCard(), but this searches the ArrayLizt
	public Card getFromHand(Card search) {
		for (int i = 0; i < 4; i++) {
			if (listOfSuits[i].getName().equals(search.suit())) {
				for (int k = 0; k < listOfSuits[i].size();k++) {
					if (search.matchesSuitAndRank(listOfSuits[i].get(k))) {
						Card pop = listOfSuits[i].get(k);
						remove(pop);
						return pop;
					}
				}
			}
		}
		return null;
	}

	/* Searches the hand for the specified card;
	 * returns null if the card isn't in the hand
	 */ 
	public Card getCard(Card search) {
		for (int i = 0; i <= 12; i++) {
			if (cards.get(i) == search) {
				return search;	
			}
		}
		return null;
	}

	//removes from listOfSuits
	public void remove(Card playedCard) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < listOfSuits[i].size();j++) {
				//System.out.println("I GOT HERE!!");

				Card toBeChecked = listOfSuits[i].get(j);
				if(toBeChecked.matches(playedCard)) {
					listOfSuits[i].remove(j);
					break;
				}
			} 
		}
	}







	/* for the sake of my testers, this returns a card
	 * at a specified position from the top of the hand.
	 */
	public Card getAt(int position) {
		//System.out.println(cards.toString());
		return cards.get(position);
	}


	/* returns HCP as int
	 */
	public int getHCP() {
		int HCP = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).rank().equals("j")) {
				HCP += 1;
			}
			if (cards.get(i).rank().equals("q")) {
				HCP += 2;	
			}
			if (cards.get(i).rank().equals("k")) {
				HCP += 3;
			}
			if (cards.get(i).rank().equals("a")) {
				HCP += 4;
			}
		}
		return HCP;
	}


	/* returns HCP with distribution as int
	 */
	public int getDistribution() {
		int HCP = 0;
		for (int i = 0; i < cards.size()-1; i++) {
			if (cards.get(i).rank().equals("j")) {
				HCP += 1;
			}
			if (cards.get(i).rank().equals("q")) {
				HCP += 2;	
			}
			if (cards.get(i).rank().equals("k")) {
				HCP += 3;
			}
			if (cards.get(i).rank().equals("a")) {
				HCP += 4;
			}
		}
		for (int distribution = 2; distribution >= 0; distribution--) {
			for (int p = 0; p < 4; p++) {
				if (listOfSuits[p].size() == distribution) {
					HCP += 3 - distribution;
				}	
			}/*
			if (c.size() == distribution) {
				HCP += 3 - distribution;
			}
			if (d.size() == distribution) {
				HCP += 3 - distribution;
			}
			if (h.size() == distribution) {
				HCP += 3 - distribution;
			}
			if (s.size() == distribution) {
				HCP += 3 - distribution;
			}*/
		}
		return HCP;
	}






	//also need to implement stuff for declarer and 
	//BIDDING and counting winners, probably in
	//bridge class. this is so confusing #adhd



	// once trump suit is decided, adds 14 points to the value
	// theDonald is 0 for club trump, 1 for d, 2 for h, 3 for s 
	// and -1 for noTrump.
	public void setTrumpSuit(int theDonald) {
		if (theDonald >= 0) {
			//System.out.println(listOfSuits[theDonald].toString());
			for (int i = 0; i < listOfSuits[theDonald].size(); i++) {
				int curVal = listOfSuits[theDonald].get(i).pointValue();
				listOfSuits[theDonald].get(i).setPointValue(14+curVal);
				//System.out.println(listOfSuits[theDonald].get(i).pointValue());
			}
		}
		if (theDonald >= 0) {
			trumpSuit = listOfSuits[theDonald].getName();
		}

	}
	// and I need to do trump. and the computer has 
	//to analyze what's been played already...


	// jacet, I'll get to this later
	public Card whatToLead() {
		//review bidding
		
		//this is a placeholder
		Card poppedCard = new Card("kek","hh",999);
		
		//this is how many cards in the hand
		int numCards = listOfSuits[0].size() + listOfSuits[1].size() +
			listOfSuits[2].size() + listOfSuits[3].size();

		// sortedSuits is the exact same thing as
		// listOfSuits, but sortedSuits is sorted
		// by suit length. Pretty cool, eh?
		Sorter s = new Sorter();
		ArrayLizt<Card>[] sortedSuits = s.zelectionZort(listOfSuits);
		// I hope it recreates
		// sortedSuits to match
		// listOfSuits every
		// time I run this
		
		

		//if you're the first to lead
		//to do:
		// check for not underleading aces,
		// check for specific leads (ex. leading K from KQxx),
		// leading partners suit,
		// 4th from longest + strongest,
		// what trump do I lead if I don't have a singleton trump?

		if(numCards > 12) {
		//due to the nature of popped card, put preferable leads at the end.
		// put the most desperate leads first.

			//if you're playing in a trump contract and it's ur lead
			if (trumpSuit != null) {
				//here, if your longest suit is trump, you lead 
				// a trump. idk why tho.
				if (sortedSuits[0].get(0).suit().equals(trumpSuit)) {
					//poppedCard = conventionCardLeads();
				}
			}

			if (trumpSuit == null) {
			    //this gets fourth from longest and strongest
				poppedCard = sortedSuits[0].get(3);

				//if partner has bid, play their suit (if u have it)
				/*if(partnersBid != -1 && listOfSuits[partnersBid].size() > 0) {
					if(listOfSuits[partnersBid].size() % 2 == 0) { //if even # of cards, lead high
						poppedCard = listOfSuits[partnersBid].get(0);
					} else {
						poppedCard = listOfSuits[partnersBid].get(listOfSuits[partnersBid].size()-1);
					}
				 }
			     */
			}
		}
		/* 
		 * this is a random "low" lead, however, it just finds
		 * a suit that isn't a void and plays the lowest card
		 * in it. pretty useless, but is good for when the hand is 
		 * running out of cards.
		 */
		// also this is temporary, will all be deleted soon
		for (int i = 0; i < 4; i++) {
			if (listOfSuits[i].size() > 0) {
				poppedCard = listOfSuits[i].get(listOfSuits[i].size()-1);
				listOfSuits[i].remove(listOfSuits[i].size()-1);
				
				return poppedCard;	
							
			}
		}


		//this is to delete the popped card, right before playing it
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < listOfSuits[i].size(); k++) {
				if (listOfSuits[i].get(k).matches(poppedCard)) {
					listOfSuits[i].remove(k);
					
				}			
			}
		}
		return poppedCard;

	}




	// this has nothing to do with the suit contract 
	//  (though it should), but once you use your
	//  better judgement to choose a suit, this spits
	//  out a card that might be good to play based
	//  on the back of your everyday convention card. 
/*	private Card conventionCardLeads(String suit) {
		for (int i = 0; i < 4; i++) {
			if (trumpSuit) {

			}
		}
	}
  */



	public Card whatCardToPlay(Table table, Bridge bridge){
		ArrayLizt<Card> kekkun = table.getCardz();
		//kekkun is the cards out on the table
		Card poppedCard = new Card("kek","hh",00);

		if (kekkun.size() <= 0) {
			//In this case, the hand is leading and that's not very good for me
			return this.whatToLead();		
		}

		//if my suit that is the same one as the one lead is not empty..
		for (int i = 0; i < 4; i++) {
			//also have to check to see if a higher card has been played

			// if the leader's suit matches my current yawei[i] suit, and that 
			//that yawei[i] suit isn't a void,
			if (table.getCardAtPos(bridge.getLeader()).suit().equals(listOfSuits[i].getName())
				&& listOfSuits[i].size() != 0) {


				// if one card has already been played
				if (kekkun.size() == 1) {
					//second hand low
					poppedCard = listOfSuits[i].get(listOfSuits[i].size());
					listOfSuits[i].remove(listOfSuits[i].size());
					//return listOfSuits[i].get(listOfSuits[i].size());
					return poppedCard;
				}

				if (kekkun.size() == 2) {
					//third hand high
					//but there are other cases...
					poppedCard = listOfSuits[i].get(0);
					listOfSuits[i].remove(0);
					return poppedCard;
				}

				if (kekkun.size() == 3) {
					poppedCard = listOfSuits[i].get(0);
					listOfSuits[i].remove(0);
					return poppedCard;
				}
				

			}
		}
		//ruff high  
		// that's a good knuckle tattoo
		for (int k = 0; k < 4; k++) {
			if (listOfSuits[k].getName().equals(trumpSuit) && listOfSuits[k].size() > 0) {
				poppedCard = listOfSuits[k].get(0);
				listOfSuits[k].remove(0);	
				return poppedCard;			
			} 

		}

		//out of trump?
		for (int j = 0; j < 4; j++) {
			if (listOfSuits[j].size() > 0) {
				poppedCard = listOfSuits[j].get(listOfSuits[j].size() - 1);
				remove(poppedCard);
				return poppedCard;
			}
		}
			

			//this is the case where you have a void in the led suit
			//have to implement trump
		
		//this is returned when out of the led suit
		return poppedCard;
	}







	//maybe this should be void and only set the table?
	//but the returning string will make it easier to format
	//in Main. I can just do System.out.print() and after
	//south bids, I can do a println. but how to center it... 
	public String whatToBid(Table table, int myPos) {
		int HCP = this.getHCP();
		int distributionHCP = this.getDistribution();
		Sorter s = new Sorter();
		ArrayLizt<Card>[] longestSuitFirst = s.zelectionZort(listOfSuits);
		String bid = null;

		// if no one has bid
		if (table.numberBidsSoFar() == 0) {
			if (distributionHCP > 13){
				bid = "1"+longestSuitFirst[0].getName().substring(0,1);
			}
			//will bid 1N if not more than 1 distribution pt (1 distribution pt means a doubleton)
			if (distributionHCP - HCP <= 1 && HCP >= 15) {
				bid = "1n";
			}
			if (distributionHCP - HCP <= 1 && HCP == 20 || HCP == 21) {
				bid = "2n";
			}
			if (HCP > 20) {
				bid = "2c";
			}
			if (distributionHCP < 11 && longestSuitFirst[0].size() > 5 &&
				!longestSuitFirst[0].getName().toLowerCase().equals("clubs")) {
				bid = "2" + longestSuitFirst[0].getName().substring(0,1);
			} 

		}


		int partnersPos = myPos + 2 < 4 ? myPos + 2 : myPos - 2;
		
		//if I'm responding to partner
		//if (table.playersLastBid(partnersPos).equals(table.lastBid()))
		
		//this is unlikely, will respond later
		//if (table.playersFirstBid(partnersPos).equals("2c")&&)
		table.setBid(bid,myPos);
		return bid;
	}

	//anything from 1 to 7
	private int levelOfBid(String newBid) {
		return Integer.parseInt(newBid.substring(0,1));
	}
	//can be c d h s or n
	private String suitOfBid(String newBid) {
		return newBid.substring(1).toLowerCase();
	}

}
