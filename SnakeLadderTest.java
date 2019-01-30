/*
 * 
 * Run the code in this file to start the game.
 * Proceed further by watching the console output.
 * 
 */


package zomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SnakeLadderTest {
	
	private int players;
	
	public void startGame() throws NumberFormatException, IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		
		out.println("Enter the number of players: ");
		
		int numPlayers = Integer.parseInt(in.readLine());
		players = numPlayers;
		
		SnakeLadder game = new SnakeLadder(numPlayers);
		
		out.println("Press n to continue or any other character to end");
		
		String input = in.readLine();
		
		while(input.equals("n"))
		{
			game.nextTurn();
			
			if(game.isGameEnd() == false)
			{
				out.println("Press n to continue or any other character to end");
				
				input = in.readLine();
			}
			else
			{
				input = "q";
			}
		}
		
		for(int i = 0; i<players; i++)
		{
			out.println("Player "+ (i+1) + " is at " + game.getCurrentPosition(i));
		}
	}
	
	public static void main(String[] args) {
		SnakeLadderTest game = new SnakeLadderTest();
		try
		{
			game.startGame();
		}
		catch (Exception e)
		{
			System.out.println("Some Exception Occurred!!!");
			e.printStackTrace();
		}
	}

}
