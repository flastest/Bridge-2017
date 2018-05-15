import java.util.*;
//import java.util.ArrayList;


public class Table {
	/* this is just what's already on the table.
	 */

	private Card north;
	private Card east;
	private Card south;
	private Card west;

	private Card[] table = new Card[4];

	private Card[][] playedTricks = new Card[4][13];
	private int trickIdx = 0;

	private String[][] theBidding = new String[4][27];
	private int bidIdx = 0;

	// should probably check the bid before it to see if it's legal...
	public void setBid(String bid, int position) {
		theBidding[position][bidIdx] = bid;
		if (bid.substring(0,1).toLowerCase().equals("p")) {
			theBidding[position][bidIdx] = null;
		}
		if (position == 0) {
			bidIdx++;
		}
	}

	//can probably make this shorter by just copying certain lines from the bidding
	public String[][] reviewTheBidding() {
		String[][] ans = new String[4][whereDoesBiddingEnd()];
		for (int i = 0; i < whereDoesBiddingEnd(); i++) {
			for (int k = 0; k < 4; k++) {
				if (theBidding[k][i].equals(null)) {
					ans[k][i] = "pass";
				} else {
					ans[k][i] = theBidding[k][i];
				}
			}
		}
		return ans;
	}

	public String[][] getTheBidding() {
		return theBidding;
	}

	//player int is a value bxn 0 and 3, correlates to n,s,e, or w
	//roundOfBidding is essentially after that # of calls by declarer
	public String getBid(int playerInt, int roundOfBidding) {
		return theBidding[playerInt][roundOfBidding];
	}

	// gets specified player's last bid
	public String playersLastBid(int playerInt) {
		return theBidding[playerInt][numBidsByPlayer(playerInt)];
	}

	public String playersFirstBid(int playerInt) {
		for (int i = 0; i < whereDoesBiddingEnd(); i++) {
			if (!theBidding[playerInt][i].equals(null)) {
				return theBidding[playerInt][i];
			}
		}
		return null;
	}

	public int numBidsByPlayer(int playerInt) {
		int counter = 0;
		for (int i = 0; i < whereDoesBiddingEnd(); i++) {
			if (!theBidding[playerInt][i].equals(null)) {
				counter++;
			}
		}
		return counter;
	}

	//this returns the last bid
	public String lastBid() {
		for (int i = 3; i >= 0; i--) {
			if (!theBidding[i][whereDoesBiddingEnd()].equals(null))
				return theBidding[i][whereDoesBiddingEnd()]; 
		}
		return null;
	}

	//this returns how many total calls have been made
	public int numberBidsSoFar() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < whereDoesBiddingEnd(); k++) {
				if (!theBidding[i][k].equals(null)) {
					count++;
				}
			}
		}
		return count;
	}

	// looks for 3 passes in a row, returns the round in which 
	// 3 passes occurred
	public int whereDoesBiddingEnd() {
		int passStreak = 0;
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 27; k++) {
				if (theBidding[i][k].equals(null)) {
					passStreak ++;
				}
				if (passStreak >= 3) {
					return k;
				}
				if (!theBidding[i][k].equals(null)) {
					passStreak = 0;
				}
			}
		}
		return -1;
	}

	public int posOfDeclarer() {
		String trumpsuit = lastBid().substring(1);
		for (int i = 0 ; i < whereDoesBiddingEnd(); i++) {
			for (int k = 0; k < 4; k ++){ 
				if (theBidding[k][i].substring(1).equals(trumpsuit)) {
					return k;
				} 
			}
		}
		return 0;
	}

	public String getTrump() {
		return lastBid().substring(1);
	}







	public Table(){
		north = null;
		east = null;
		south = null;
		west = null;
		Card[] table = new Card[]{north, east, south, west};
	}

	/* setting table; position 0 is north, 1 = east, 2 = south, 3 = west
	 */
	public void setTable(Card card, int position){
		table[position] = card;
		if (position == 0) {
			System.out.println("north plays "+card.toString());
		}
		if (position == 1) {
			System.out.println("east plays "+card.toString());	
		}
		if (position == 2) {
			System.out.println("south plays "+card.toString());
		}
		if (position == 3) {
			System.out.println("west plays "+card.toString());
		}
	}

	public Boolean isFull() {
		for (int i = 0; i < 4; i++) {
			playedTricks[i][trickIdx] = table[i];
			if (table[i] == null) {
				return false;
			}
		}
		trickIdx++;
		return true;
	}

	public void printTricksPlayed() {
		System.out.println("n           e           s           w");
		for (int i = 0; i < 13; i++) {
			System.out.println(playedTricks[0][i] + ", " + 
				playedTricks[1][i] + ", " + playedTricks[2][i] + 
				", " + playedTricks[3][i]);
		}
	}


	//void,String or String[]. printlns or wanna be able to format
	public String[] getTable(){
		String line1 = "   N:"+table[0];
		String line2 = "W:"+table[3]+"   E:"+table[1];
		String line3 = "   S:"+table[2];

		//System.out.println("   N:"+table[0]);
		//System.out.println("W:"+table[3]+"   E:"+table[1]);
		//System.out.println("   S:"+table[2]);
		
		//return "   N:"+table[0]+"\nW:"+table[3]+"   E:"+table[1]+"\n   S:"+table[2];

		//better yet,

		String[] ans = {line1,line2,line3};
		return ans;

	}


	/* This is for the purpose of sorting the cards greatest to least
	 * to decide the winner using Sorter in the Bridge class. 
	 */
	public ArrayList<Card> getCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < table.length; i++){
			cards.add(table[i]);
		}
		return cards;
	}

	public Card getCardAtPos(int position) {
		return table[position];
	}


	public void clear() {
		table = new Card[4];
	}

	public ArrayLizt<Card> getCardz() {
		ArrayLizt<Card> cards = new ArrayLizt<Card>();
		for (int i = 0; i < table.length-1; i++){
			cards.add(table[i]);
		}
		return cards;
	}



}