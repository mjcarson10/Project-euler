package ProblemSolutions;

import StackInfrastructure.LinkedStack;

public class DrunkenTowerOfHanoi
{
	// produces the average number of moves of all trials with the given parameters
		/*
		 * From Project Euler.net
		 * 
		 * Bob is very familiar with the famous mathematical puzzle/game, "Tower of Hanoi," which consists of three upright rods and disks of different sizes that can slide onto any of the rods. The game begins with a stack of n disks placed on the leftmost rod in descending order by size. The objective of the game is to move all of the disks from the leftmost rod to the rightmost rod, given the following restrictions:

			*1. Only one disk can be moved at a time.
			*2. A valid move consists of taking the top disk from one stack and placing it onto another stack (or an empty rod).
			*3. No disk can be placed on top of a smaller disk.

			*Moving on to a variant of this game, consider a long room k units (square tiles) wide, labeled from 1 to k in ascending order. Three rods are placed at squares a, b, and c, and a stack of n disks is placed on the rod at square a.

			*Bob begins the game standing at square b. His objective is to play the Tower of Hanoi game by moving all of the disks to the rod at square c. However, Bob can only pick up or set down a disk if he is on the same square as the rod / stack in question.

			*Unfortunately, Bob is also drunk. On a given move, Bob will either stumble one square to the left or one square to the right with equal probability, unless Bob is at either end of the room, in which case he can only move in one direction. Despite Bob's inebriated state, he is still capable of following the rules of the game itself, as well as choosing when to pick up or put down a disk.

			*The following animation depicts a side-view of a sample game for n = 3, k = 7, a = 2, b = 4, and c = 6:


		 */
		public static int drunkenTowerOfHanoi(int discs, int tiles, int rodA, int rodB, int rodC, int trials)
		{
			// integer to hold totalt moves made
			int numOfTurns = 0;
			// loop for each trial
			for(int i = 0; i < trials; i++)
			{
				// Begin by creating the field. It will be an array of stacks
				LinkedStack<TowerOfHanoiDisc>[] gameBoard = new LinkedStack[tiles];
				for(int j = 0; j < tiles; j++)
				{
					gameBoard[j] = new LinkedStack();
				}
				// After the board is created, use rodA to initialize n discs
				int size = discs;
				for(int j = discs; j > 0; j--)
				{
					gameBoard[rodA-1].push(new TowerOfHanoiDisc(size));
					size--;
				}
				// now that the board has been set up, the game can begin being played
				// the game begins at rod B
				TowerOfHanoiPlayer player = new TowerOfHanoiPlayer(rodB-1);
				// for each turn, it is randomly decided whether the player moves to the left or right
				// the game runs until all discs are on rod C
				while(!(gameBoard[rodA-1].isEmpty() && gameBoard[rodB-1].isEmpty() && player.getCurrentDisc() == null))
				{
					// special case for only 1 disc
					if(discs == 1)
					{
						numOfTurns += moveTo(rodA-1, player, tiles);
						// grab disc from rodA
						player.grab(gameBoard[rodA-1].getTop());
						gameBoard[rodA-1].pop();
						// place on rodC
						numOfTurns += moveTo(rodC-1, player, tiles);
						gameBoard[rodC-1].push(player.getCurrentDisc());
						player.place();
					}
					else
					{
						// continue the pattern of returning to rodA until it is empty
						if(!gameBoard[rodA-1].isEmpty())
						{
							// initial move will always go to rodA so long as it is not empty
							numOfTurns += moveTo(rodA-1, player, tiles);
							// grab disc from rodA
							player.grab(gameBoard[rodA-1].getTop());
							gameBoard[rodA-1].pop();
							// if rodB is empty place the disc on rodB 
							if(gameBoard[rodB-1].isEmpty())
							{
								numOfTurns += moveTo(rodB-1, player, tiles);
								gameBoard[rodB-1].push(player.getCurrentDisc());
								player.place();
							}
							// otherwise compare the disc to rodB to see if it fits on top
							else if(!player.getCurrentDisc().compare(gameBoard[rodB-1].getTop().getInfo()))
							{
								numOfTurns += moveTo(rodB-1, player, tiles);
								gameBoard[rodB-1].push(player.getCurrentDisc());
								player.place();
							}
							// if the current disc is larger, put it on rod C
							else if(player.getCurrentDisc().compare(gameBoard[rodB-1].getTop().getInfo()))
							{
								numOfTurns += moveTo(rodC-1, player, tiles);
								gameBoard[rodC-1].push(player.getCurrentDisc());
								player.place();
							}
						}
						// if rodA is empty and RodB top is larger than rodC top
						else if(!gameBoard[rodB-1].isEmpty() && !gameBoard[rodC-1].getTop().getInfo().compare(gameBoard[rodB-1].getTop().getInfo()))
						{
							// continue this pattern until the top of rodB can be placed onto rodC, or until rodB is empty
							while(!gameBoard[rodB-1].isEmpty() && !gameBoard[rodC-1].getTop().getInfo().compare(gameBoard[rodB-1].getTop().getInfo()))
							{
								// move to rod B, and place the disc on rodA
								numOfTurns += moveTo(rodB-1, player, tiles);
								player.grab(gameBoard[rodB-1].getTop());
								gameBoard[rodB-1].pop();
								numOfTurns += moveTo(rodA-1, player, tiles);
								gameBoard[rodA-1].push(player.getCurrentDisc());
								player.place();
							}
							// once complete put the top of rodC on RodB
							numOfTurns += moveTo(rodC-1, player, tiles);
							player.grab(gameBoard[rodC-1].getTop());
							gameBoard[rodC-1].pop();
							numOfTurns += moveTo(rodB-1, player, tiles);
							gameBoard[rodB-1].push(player.getCurrentDisc());
							player.place();
						}
						// if RodBs top is smaller than rod Cs move the top of rodB to rodC
						else if(!gameBoard[rodB-1].isEmpty() && gameBoard[rodC-1].getTop().getInfo().compare(gameBoard[rodB-1].getTop().getInfo()))
						{
							numOfTurns += moveTo(rodB-1, player, tiles);
							player.grab(gameBoard[rodB-1].getTop());
							gameBoard[rodB-1].pop();
							numOfTurns += moveTo(rodC-1, player, tiles);
							gameBoard[rodC-1].push(player.getCurrentDisc());
							player.place();
						}
					}
				}
			}
			return numOfTurns / trials;
		}
		// moves the player in tower of Hanoi to the location necessary
		public static int moveTo(int rod, TowerOfHanoiPlayer player, int tiles)
		{
			// store total number of moves
			int numOfTurns = 0;
			// loop until at the correct location
			while(player.getLocation() != rod)
			{
				// use random to randomly pick a direction
				double direction = Math.random();
				player.locationChange(player.getLocation(), tiles, direction);
				numOfTurns++;
			}
			return numOfTurns;
		}
		// method for standard tower of Hanoi
		public void towersOfHanoi(int discs, LinkedStack<TowerOfHanoiDisc> rodA,
				LinkedStack<TowerOfHanoiDisc> rodB, LinkedStack<TowerOfHanoiDisc> rodC)
		{
			// base case of only 1 disc
			if(discs == 1)
			{	
				// move the disc from rod A to C
				rodC.push(rodA.top());
				rodA.pop();
			}
			// Recursive case
			else
			{
				// move n-1 discs from starting rod to middle rod
				towersOfHanoi(discs-1, rodA, rodC, rodB);
				// move nth disc to ending ring
				rodC.push(rodA.top());
				rodA.pop();
				// move n-1 discs from middle rod to ending rod
				towersOfHanoi(discs-1, rodB, rodA, rodC);
				
			}
		}
}
