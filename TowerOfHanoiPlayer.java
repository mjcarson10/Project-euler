package ProblemSolutions;
import StackInfrastructure.*;
public class TowerOfHanoiPlayer
{
	/*
	 *  hold the players location as an integer
	 *  this integer will refer to the array index of the gameboard
	 */
	private int location;
	/*
	 * hold the disc as a disc Object
	 * refer to the TowerOfHanoiDisc object
	 */
	private TowerOfHanoiDisc currentDisc;
	/*
	 * hold the number of turns taken as an integer
	 * this will be incremented as the player moves
	 * and when a single trial of the game ends the value will be pushed into 
	 * a sum of all players for each trials number of turns
	 */
	private int numOfTurns;
	/*
	 * Constructor for the player
	 * Location will equal the parameter given
	 * the player starts without a disc and with 0 turns
	 */
	public TowerOfHanoiPlayer(int location)
	{
		this.location = location;
		currentDisc = null;
		numOfTurns = 0;
	}
	
	/*
	 * returns the disc currently held
	 */
	public TowerOfHanoiDisc getCurrentDisc()
	{
		return this.currentDisc;
	}
	
	/*
	 * returns the players current location
	 */
	public int getLocation()
	{
		return this.location;
	}
	
	/*
	 * returns the number of turns the player has taken
	 */
	public int getNumOfTurns()
	{
		return this.numOfTurns;
	}
	
	// contains the logic for a change in location
	private void changeLocationTo(int location, int max, double direction)
	{
		// if the players location is the leftmost location
		if(location == 0)
			// the player must move right in this case
			this.location++;
		// likewise, if the players location is at the rightmost location
		else if(location == max-1)
			// the player must move left
			this.location--;
		// otherwise the movement is randomized by direction
		// if direction should be rounded up
		else if(direction >= 0.5)
			// move right
			this.location++;
		// if the direction should be rounded down
		else
			// move left
			this.location--;
	}
	
	/*
	 *  moves the player in tower of Hanoi to the location necessary
	 *  Using the location of desired and the total number of tiles
	 *  This method will continue until the player location equals the goal
	 */
	private void moveTo(int goal, int tiles)
	{
		// loop until at the correct location
		while(getLocation() != goal)
		{
			// use random to randomly pick a direction
			double direction = Math.random();
			// call locationChange()
			this.changeLocationTo(getLocation(), tiles, direction);
			// increment number of turns
			this.numOfTurns++;
		}
	}
	
	/*
	 * This method combines the pickUp and SetDown method into one line of code
	 * utilized to simplify DrunkenTowerOfHanoi when the steps can be combined
	 * without further conditions
	 */
	private void replace(int moveFrom, int moveTo, int tiles, LinkedStack<TowerOfHanoiDisc>[] gameBoard)
	{
		// first the player must pick up a disc from the first goal
		pickUp(moveFrom, tiles, gameBoard);
		// and then the player will set down the disc at the second goal
		setDown(moveTo, tiles, gameBoard);
	}
	
	/*
	 * This method allows the player to pick up a disc
	 * it will move the player to the goal, collect the disc, and remove the 
	 * disc from the rod at the goal
	 */
	private void pickUp(int goal, int tiles, LinkedStack<TowerOfHanoiDisc>[] gameBoard)
	{
		// move to the goal location
		moveTo(goal, tiles);
		// collect the disc at the current location
		this.currentDisc = gameBoard[goal].getTop().getInfo();
		// remove the disc from the rod
		gameBoard[goal].pop();
	}
	
	/*
	 * This method allows the player to place a disc
	 * it will move the player to the goal, place the disc, and remove the 
	 * disc from the player
	 */
	private void setDown(int goal, int tiles, LinkedStack<TowerOfHanoiDisc>[] gameBoard)
	{
		// move to the goal location
		moveTo(goal, tiles);
		// push the disc onto the rod
		gameBoard[goal].push(getCurrentDisc());
		// remove the disc from the player
		this.currentDisc = null;
	}
	
	/*
	 * method to solve the towers of hanoi game
	 * utilizes recursion to generate the most efficient solution
	 */
	public void solve(int discs, int rodA, int rodB, int rodC, 
			int tiles, LinkedStack<TowerOfHanoiDisc>[] gameBoard)
	{
		// base case of only 1 disc
		if(discs == 1)
		{	
			// move rodA top to rodC
			replace(rodA-1, rodC-1, tiles, gameBoard);
		}
		// Recursive case
		else
		{
			// move n-1 discs from starting rod to middle rod
			solve( discs-1, rodA, rodC, rodB, tiles, gameBoard);
			// move nth disc to ending ring
			replace(rodA-1, rodC-1, tiles, gameBoard);
			// move n-1 discs from middle rod to ending rod
			solve(discs-1, rodB, rodA, rodC, tiles, gameBoard);
		}
	}
}
