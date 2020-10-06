public class BridgeTester{
	public static void main (String[]args){
		Bridge game = new Bridge();
		//game.setHand2();
		game.deal();
		System.out.println("\n\n NORTH:");
		game.showNorth();
		System.out.println("HCP without distribution of N is " + game.north.getHCP());
		System.out.println("HCP with distribution of N is " + game.north.getDistribution());

		System.out.println("\n\n SOUTH:");
		game.showSouth();
		System.out.println("\n\n EAST:");
		game.showEast();

		System.out.println("\n\n WEST:");
		game.showWest();


	
		Table table = new Table();
		System.out.println("\n\n");
		//game.ThePlay(table);
		table.setTable(game.north.whatToLead(),0);
		table.setTable(game.east.whatCardToPlay(table, game),1);
		table.setTable(game.south.whatCardToPlay(table, game),2);
		table.setTable(game.west.whatCardToPlay(table, game),3);

		table.getTable();

		System.out.println("Winning card of the hand is: " + game.getWinner(table));
		System.out.println("Winner of the hand is: " + game.getWinnerDir(table));

	
	}
}