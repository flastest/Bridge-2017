import java.util.*;
import java.lang.Object;
import java.*;

public class Main {

	
	
	static Bridge game = new Bridge();

	public static void main(String[] args) throws InterruptedException {
		
		try {	
			Bridge game = new Bridge();

			Scanner kek = new Scanner(System.in);


			//just some AIDS I need to go through to get width of console
			Process p = Runtime.getRuntime().exec(new String[] {"bash", "-c", "tput cols 2> /dev/tty" });
			AGIANTPARSER myFURIOUS = new AGIANTPARSER();
			//System.out.println(myFURIOUS.convertStream(p.getInputStream())+myFURIOUS.convertStream(p.getInputStream()).getClass());
			int lineLen = myFURIOUS.convertStream(p.getInputStream());






			formatText("\n");
			String tildes = "";
			for (int i = 0; i < lineLen; i++) {
				tildes += "~";
			}
			formatText(tildes);
			Thread.sleep(100);
			formatText("Welcome to Ariel's Strip bridge!");
			Thread.sleep(500);
	
			game.deal();

			
			//this 0 should be reserved for whoever wins the bidding
			game.setLeader(0);
			Table table = new Table();

			

			formatText("\n\n");
			formatText(" NORTH:");
			game.showNorth();
			System.out.println("HCP without distribution of N is " + game.north.getHCP());
			System.out.println("HCP with distribution of N is " + game.north.getDistribution());
			
			formatText(" SOUTH:");
			game.showSouth();
			
			Thread.sleep(1000);
			System.out.println("\n\n");
			Thread.sleep(500);
			formatText("Here: set the trump suit");
			System.out.print("\n>>");
			game.setTrumpSuit(kek.next());
			kek = new Scanner(System.in);

			Thread.sleep(1000);

			boolean robot = false;
			formatText("Want to play? If not, you're just testing robots.");
			Thread.sleep(500);
			formatText("[y or n]");
			System.out.print("\n>>");
			if (kek.next().equals("n")) {
				robot = true;
			}
			kek = new Scanner(System.in);



			Thread.sleep(1000);
			
			if (robot) {
				table.setTable(game.north.whatToLead(),0);
				Thread.sleep(1000);
			} else {
				Card playedCard = askForCard(game);
				table.setTable(playedCard,0);
				Thread.sleep(1000);
			}
			table.setTable(game.east.whatCardToPlay(table, game),1);
			Thread.sleep(1000);
			table.setTable(game.south.whatCardToPlay(table, game),2);
			Thread.sleep(1000);
			table.setTable(game.west.whatCardToPlay(table, game),3);
	

			Thread.sleep(100);
			String[] tableGUI = table.getTable();
			formatText(tableGUI[0]);
			Thread.sleep(100);
			formatText(tableGUI[1]);
			Thread.sleep(100);
			formatText(tableGUI[2]);
		

			Thread.sleep(1000);
			System.out.println("\n\nWinning card of the hand is: ");
			Card weiner = game.getWinner(table);
			formatText(weiner.toString());
			System.out.println("Winner of the hand is: "); 
			String winnerDir = game.getWinnerDir(table,weiner);
			formatText(winnerDir);
			Thread.sleep(100);
			formatText("PRESS ENTER TO CONTINUE");
			kek.nextLine();
			
			

			//really should be i < 12, but I don't wanna play 13 tricks of bridge
			for (int i = 0; i < 12;i++) {
			//now the winner has to lead
				Thread.sleep(1000);
				if (winnerDir.equals("n")) {
					if (robot) {
						table.setTable(game.north.whatCardToPlay(table, game),0);
					} else {
						game.showNorth();
						Thread.sleep(1000);
						Card playedCard = askForCard(game);
						table.setTable(playedCard,0);
					}
					Thread.sleep(1000);
					table.setTable(game.east.whatCardToPlay(table, game),1);
					Thread.sleep(1000);
					table.setTable(game.south.whatCardToPlay(table, game),2);
					Thread.sleep(1000);
					table.setTable(game.west.whatCardToPlay(table, game),3);
				}

				if (winnerDir.equals("e")) {
					table.setTable(game.east.whatToLead(),1);
					Thread.sleep(1000);
					table.setTable(game.south.whatCardToPlay(table, game),2);
					Thread.sleep(1000);
					table.setTable(game.west.whatCardToPlay(table, game),3);
					Thread.sleep(1000);
					if (robot) {
						table.setTable(game.north.whatCardToPlay(table, game),0);
					} else {
						game.showNorth();
						Thread.sleep(1000);
						Card playedCard = askForCard(game);
						table.setTable(playedCard,0);
					}
				}

				if (winnerDir.equals("s")) {
					table.setTable(game.south.whatToLead(),2);
					Thread.sleep(1000);
					table.setTable(game.west.whatCardToPlay(table, game),3);
					Thread.sleep(1000);
					if (robot) {
						table.setTable(game.north.whatCardToPlay(table, game),0);
					} else {
						game.showNorth();
						Thread.sleep(1000);
						Card playedCard = askForCard(game);
						table.setTable(playedCard,0);
					}
					Thread.sleep(1000);
					table.setTable(game.east.whatCardToPlay(table, game),1);
				}

				if (winnerDir.equals("w")) {
					table.setTable(game.west.whatToLead(),3);
					Thread.sleep(1000);
					if (robot) {
						table.setTable(game.north.whatCardToPlay(table, game),0);
					} else {
						game.showNorth();
						Thread.sleep(1000);
						Card playedCard = askForCard(game);
						table.setTable(playedCard,0);
					}
					Thread.sleep(1000);
					table.setTable(game.east.whatCardToPlay(table, game),1);
					Thread.sleep(1000);
					table.setTable(game.south.whatCardToPlay(table, game),2);
				}
				
				Thread.sleep(1000);
				tableGUI = table.getTable();
				formatText(tableGUI[0]);
				Thread.sleep(100);
				formatText(tableGUI[1]);
				Thread.sleep(100);
				formatText(tableGUI[2]);
		

				Thread.sleep(1000);
				System.out.println("\n\nWinning card of the hand is: ");
				weiner = game.getWinner(table);
				formatText(weiner.toString());
				System.out.println("Winner of the hand is: "); 
				winnerDir = game.getWinnerDir(table,weiner);
				formatText(winnerDir);
				Thread.sleep(100);
				formatText("PRESS ENTER TO CONTINUE");
				kek.nextLine();
			}
				
			
			table.printTricksPlayed();
			System.out.println("here's who won the tricks: " + game.trickWinners.toString());

			formatText("I won " + game.tricksIWon() + " tricks!");
			

			}
		catch(Exception except) {
			except.printStackTrace();
		}
	
	}


	static Card askForCard(Bridge where) {
		Scanner s = new Scanner(System.in);
		System.out.println("\n\nWhich card does NORTH want to play?");
		System.out.println("(('7 spades' is seven of Spades, 't clubs' is 10 of clubs, 'J hearts' is Jack of Hearts))");
		System.out.print(">>");
		String input = s.nextLine();	
		//System.out.println("your card is: " + getCardFromText(input, where.north));	
		Card playedCard = getCardFromText(input, where.north);
		//where.north.remove(playedCard);
		//System.out.println("From Main line 221, we bring you the card you're about to play: "+playedCard.toString());
		return playedCard;

	}

	//tbc stands for to be converted
	static Card getCardFromText(String tbc,Hand player) {
		//tbc = tbc.toLowerCase();
		String rank = tbc.substring(0,1); //rank can be 3 or J
		String suit = tbc.substring(2);
		//System.out.println("rank and suit are "+rank+suit);
		Card calledCard = new Card(rank,suit);
		//System.out.println("Main line 232\nthis is the called card's pointValue: " + calledCard.pointValue());
		Card theChosenOne = player.getFromHand(calledCard);
		//System.out.println("this is the pointValue of the 'matching' card in your hand: " + theChosenOne.pointValue());
		
		return theChosenOne;
	
	}


	//centers text and prints it
	static void formatText(String text) {	
		try {

			//gets width of console
			//real pain in the ass, uses AGIANTPARSER
			Process p = Runtime.getRuntime().exec(new String[] {"bash", "-c", "tput cols 2> /dev/tty" });
			AGIANTPARSER myFURIOUS = new AGIANTPARSER();
			int lineLen = myFURIOUS.convertStream(p.getInputStream());


			int whitespace = (lineLen / 2) - (text.length() / 2);
			String ans = "";
			for (int i = 0; i < whitespace; i++) {
				ans += " ";
			}
			System.out.println (ans+text);
		}
		catch(Exception myFLAMINGHOMO){
			myFLAMINGHOMO.printStackTrace();
		}
		//return null;
	}




}
