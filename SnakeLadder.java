package zomato;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;



public class SnakeLadder {
	
	private static final int WIN = 100;
	private boolean isWon;
	private int winner;
	
	private int turn = 0;
	
	private int[] curPosition;
	
	private HashMap<Integer, Integer> snakes;
	private HashMap<Integer, Integer> ladders;
	
	private ArrayList<Pair<Integer, Integer>> players;
	
	 PrintWriter out = new PrintWriter(System.out, true);
	
	SnakeLadder(int numPlayers)
	{
		if(numPlayers == 0 || numPlayers == 1)
		{
			out.println("Number of players cannot be 0 or 1.\nSetting number of players to be 2");
			numPlayers = 2;
		}
		
		curPosition = new int[numPlayers];
		isWon = false;
		winner = -1;
		snakes = new HashMap<>();
		ladders = new HashMap<>();
		players = new ArrayList<>();
		initGame();
		
	}
	
	private void initGame() {
		
		for(int i=0; i<curPosition.length; i++)
		{
			int dice = rollDice();
			out.println("Player " + (i+1)+ " rolls " + dice);
			players.add(new Pair<Integer, Integer>(i, dice));
		}
		
		players.sort(new Comparator<Pair<Integer, Integer>>() {

			@Override
			public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getSecond().compareTo(o1.getSecond());
			}
		});
		out.println("Player "+(players.get(0).getFirst()+1)+" will start the game.");
		generateSnakesAndLadders();
		
	}

	public void generateSnakesAndLadders()
	{
		setSnake(99,7);
		setSnake(92,35);
		setSnake(78,39);
		setSnake(73,53);
		setSnake(37,17);
		setSnake(31,14);
		
		setLadder(5,25);
		setLadder(10,29);
		setLadder(22,41);
		setLadder(28,55);
		setLadder(44,95);
		setLadder(70,89);
		setLadder(79,81);
	}
	
	public boolean isGameEnd()
	{
		return isWon == true;
	}
	
	public int getWinner()
	{
		return winner;
	}
	
	public void setSnake(int start, int end)
	{
		if(snakes.containsKey(start))
		{
			return;
		}
		
		snakes.put(start, end);
	}
	
	public void setLadder(int start, int end)
	{
		if(ladders.containsKey(start) && snakes.containsKey(end))
		{
			return;
		}
		ladders.put(start, end);
	}
	
	public int getCurrentPosition(int player)
	{
		return curPosition[player];
	}
	
	
	public void movePlayer(int player, int numMoves)
	{
		if(curPosition[player]+numMoves > WIN)
		{
			out.println("Cannot Move");
			return;
		}
		
		curPosition[player] += numMoves;
		
		if(snakes.containsKey(curPosition[player]))
		{
			out.println("Swallowed by snake");
			curPosition[player] = snakes.get(curPosition[player]);
		}
		if(ladders.containsKey(curPosition[player]))
		{
			out.println("Climibing up the ladder");
			curPosition[player] = ladders.get(curPosition[player]);
		}
		
		if(curPosition[player] == WIN)
		{
			isWon = true;
			winner = player;
		}
	}
	
	private int rollDice()
	{
		Random rn = new Random();
		return rn.nextInt(6)+1;
	}
	
	public void nextTurn()
	{
		
		if(!isWon)
		{
			int dice = rollDice();
			
			out.println("Player " + (players.get(turn).getFirst()+1) + " rolls " + dice);
			movePlayer(players.get(turn).getFirst(), dice);
			
			turn++;
			turn %= curPosition.length;
			
			if(isWon)
			{
				out.println("Player "+ (winner+1) + " wins.");
				return;
			}
			
		}
		else
		{
			out.println("Game has already ended with player " + (winner+1) + " as the winner");
		}
		
	}
}
