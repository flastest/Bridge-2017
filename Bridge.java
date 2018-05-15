import java.util.*;

public class Bridge {
	public Hand north;
	public Hand south;
	public Hand east;
	public Hand west;

	private Deck deck;

	private String[] ranks = {"2","3","4","5","6","7","8","9","t","j","q","k","a"};
	private String[] suits = {"clubs","diamonds","hearts","spades"};
	private int[] values = {2,3,4,5,6,7,8,9,10,11,12,13,14};

	private int leaderPos;
	//position of leader (0,1,2,3 are n,e,s,w respectively)

	private String trump = null;

	public void setTrumpSuit(String trumpSuit) {
		trumpSuit = trumpSuit.toLowerCase();
		if (trumpSuit.equals("s")||trumpSuit.equals("spades")) {
			north.setTrumpSuit(3);
			south.setTrumpSuit(3);  // should really do something that
			east.setTrumpSuit(3);   // references all the positions
			west.setTrumpSuit(3);   // at once, like a list. but then
			                        // I'd have to update everything...
		} if (trumpSuit.equals("h")||trumpSuit.equals("hearts")) {
			north.setTrumpSuit(2);
			south.setTrumpSuit(2);  
			east.setTrumpSuit(2);  
			west.setTrumpSuit(2);  
		} if (trumpSuit.equals("d")||trumpSuit.equals("diamonds")) {
			north.setTrumpSuit(1);
			south.setTrumpSuit(1);  
			east.setTrumpSuit(1);  
			west.setTrumpSuit(1);  
		} if (trumpSuit.equals("c")||trumpSuit.equals("clubs")) {
			north.setTrumpSuit(0);
			south.setTrumpSuit(0);  
			east.setTrumpSuit(0);  
			west.setTrumpSuit(0);  
		} else {
			trumpSuit = null;
		}

		trump = trumpSuit;

	}


	public Bridge() {
		leaderPos = 0;

		north = new Hand();
		south = new Hand();
		east = new Hand();
		west = new Hand();


		deck = new Deck(ranks, suits, values);
	}

	// the REAL "setHand"
	public void deal() {
//	System.out.println(deck.size());
		deck.shuffle();
		for (int i = 0; i < 13; i++) {
			north.add(deck.deal());
			south.add(deck.deal());
			east.add(deck.deal());
			west.add(deck.deal());
		}
		north.sortCards();
		south.sortCards();
		east.sortCards();
		west.sortCards();
	}

	public void showNorth() {
		//north.sortCards();
		north.display();
	}

	public void showSouth() {
		//south.sortCards();
		south.display();
	}

	public void showEast() {
		//east.sortCards();
		east.display();
	}

	public void showWest() {
		//west.sortCards();
		west.display();
	}


	//public void displayGame() {
	//	table.getTable();
  //}

	public void placeNextCard() {
		int i = 0;
		//eeee
	}


	/* Should return the winner of the cards placed on the table
	 */
	public Card getWinner(Table table) {
		while (table.isFull()) { //<-- this is a useful while loop!
			String leadingSuit = table.getCardAtPos(leaderPos).suit();
			Sorter sort = new Sorter();
			ArrayList<Card> winner = sort.sortValues(table.getCards());
			for (int i = 0; i < 4; i++) {
				//System.out.print(" "+ winner.get(i).pointValue());
				//if current card's suit is trump, then return highest pt val
				if (winner.get(i).pointValue() > 14) {
					return winner.get(0);
				}
				if (leadingSuit.equals(winner.get(i).suit())) {
					return winner.get(i);

				}
			}
		}
		return null;
	}

	//0=n,1=e,2=s,3=w
	public void setLeader(int leader) {
		leaderPos = leader; 
	} 

	public int getLeader() {
		return leaderPos;
	}

	public int getWinnerPos(Table table, Card winningCard) {		
		for (int i = 0; i < 4; i++) {
			if (table.getCardAtPos(i)==winningCard){
				leaderPos = i;
				return i;
			}
		}
		return -1;
	}




	public String getWinnerDir(Table table, Card winningCard) {
		String winnerDir = getLeaderDirFromInt(getWinnerPos(table,winningCard));
		trickWinners.add(winnerDir);
		idx++;
		return winnerDir;
	}


	public String getLeaderDirFromInt(int i) {
		if (i == 0) {
			return "n";
		}

		if (i == 1) {
			return "e";
		}

		if (i == 2) {
			return "s";
		}

		if (i == 3) {
			return "w";
		}
		return null;
	}
	


	//this is only for testing purposes so I can has the same hands everytim

	public void setHand() {
		north.add(new Card(0,12));
		north.add(new Card(0,11));
		north.add(new Card(0,10));
		north.add(new Card(0,6));
		north.add(new Card(1,12));
		north.add(new Card(1,6));
		north.add(new Card(1,5));
		north.add(new Card(2,10));
		north.add(new Card(2,6));
		north.add(new Card(2,2));
		north.add(new Card(3,9));
		north.add(new Card(3,13));
		north.add(new Card(3,12));

		south.add(new Card(0,9));
		south.add(new Card(0,13));
		south.add(new Card(0,8));
		south.add(new Card(0,5));
		south.add(new Card(1,14));
		south.add(new Card(1,11));
		south.add(new Card(1,8));
		south.add(new Card(1,7));
		south.add(new Card(1,3));
		south.add(new Card(2,14));
		south.add(new Card(2,12));
		south.add(new Card(2,5));
		south.add(new Card(3,8));
		
		east.add(new Card(0,4));
		east.add(new Card(0,3));
		east.add(new Card(0,2));
		east.add(new Card(1,10));
		east.add(new Card(1,9));
		east.add(new Card(1,2));
		east.add(new Card(2,8));
		east.add(new Card(2,7));
		east.add(new Card(2,4));
		east.add(new Card(3,11));
		east.add(new Card(3,10));
		east.add(new Card(3,6));
		east.add(new Card(3,2));
		
		west.add(new Card(0,14));
		west.add(new Card(0,7));
		west.add(new Card(1,13));
		west.add(new Card(1,4));
		west.add(new Card(2,13));
		west.add(new Card(2,11));
		west.add(new Card(2,9));
		west.add(new Card(2,3));
		west.add(new Card(3,14));
		west.add(new Card(3,7));
		west.add(new Card(3,5));
		west.add(new Card(3,4));
		west.add(new Card(3,3));
		
		north.sortCards();
		south.sortCards();
		east.sortCards();
		west.sortCards();
	}

	public void setHand2() {
		//west plays A of D to a D lead and loses to the K
		north.add(new Card(0,4));
		north.add(new Card(0,3));
		north.add(new Card(1,14));
		north.add(new Card(1,10));
		north.add(new Card(1,12));
		north.add(new Card(1,4));
		north.add(new Card(2,5));
		north.add(new Card(2,13));
		north.add(new Card(3,8));
		north.add(new Card(3,6));
		north.add(new Card(3,5));
		north.add(new Card(3,4));
		north.add(new Card(3,2));

		south.add(new Card(0,14));
		south.add(new Card(0,13));
		south.add(new Card(0,8));
		south.add(new Card(0,9));
		south.add(new Card(0,7));
		south.add(new Card(0,6));
		south.add(new Card(1,9));
		south.add(new Card(1,5));
		south.add(new Card(2,9));
		south.add(new Card(2,7));
		south.add(new Card(2,6));
		south.add(new Card(3,11));
		south.add(new Card(3,14));
		
		east.add(new Card(0,10));
		east.add(new Card(0,5));
		east.add(new Card(1,11));
		east.add(new Card(1,8));
		east.add(new Card(1,7));
		east.add(new Card(1,3));
		east.add(new Card(1,2));
		east.add(new Card(2,11));
		east.add(new Card(2,10));
		east.add(new Card(2,3));
		east.add(new Card(2,2));
		east.add(new Card(3,13));
		east.add(new Card(3,12));
		
		west.add(new Card(0,12));
		west.add(new Card(0,11));
		west.add(new Card(0,2));
		west.add(new Card(1,6));
		west.add(new Card(1,13));
		west.add(new Card(2,14));
		west.add(new Card(2,12));
		west.add(new Card(2,8));
		west.add(new Card(2,4));
		west.add(new Card(3,10));
		west.add(new Card(3,9));
		west.add(new Card(3,7));
		west.add(new Card(3,3));
		
		north.sortCards();
		south.sortCards();
		east.sortCards();
		west.sortCards();
	}





























	/*   \     / |  | |--- |\  |    /--   /\     ^ ^   |---   |--- |\  | |--\  /--\                  
          \ ^ /  |--| |-   | \ |   |  _  /--\   / V \  |--    |--  | \ | |   | \__                               
	       V V   |  | |___ |  \|    \_/ /    \ /     \ |___   |___ |  \| |__/  ,__\  0 0 0                   
	 */
	public ArrayList<String> trickWinners = new ArrayList<String>(); 
	private int idx = 0;
	
	private int yourTricks = 0;
	public int tricksIWon() {
		for (int i = 0; i < 13; i++) {
			if (trickWinners.get(i).equals("n")||trickWinners.get(i).equals("s")) {
				yourTricks++;
			}
		}
		return yourTricks;
	}





}
