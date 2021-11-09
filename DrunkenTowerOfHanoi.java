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
			// player of the game, refer to TowerOfHanoiPlayer
			TowerOfHanoiPlayer player;
			// integer to hold total moves made
			int sumNumOfTurns = 0;
			// loop for each trial
			for(int i = 0; i < trials; i++)
			{
				// Begin by creating the field. It will be an array of stacks of TowerOfHanoiDiscs
				LinkedStack<TowerOfHanoiDisc>[] gameBoard = new LinkedStack[tiles];
				// the game board will be instantiated as entirely empty stacks for a total of tiles indexes
				for(int j = 0; j < tiles; j++)
				{
					gameBoard[j] = new LinkedStack();
				}
				// After the board is created, use rodA to initialize n discs
				for(int j = discs; j > 0; j--)
				{
					// for the total number of discs, push them onto rodA in order of smallest at the top and largest at the bottom
					gameBoard[rodA-1].push(new TowerOfHanoiDisc(j));
				}
				// now that the board has been set up, the game can begin being played
				// the player begins at rod B
				player = new TowerOfHanoiPlayer(rodB-1);
				// for each turn, it is randomly decided whether the player moves to the left or right
				// the game runs until all discs are on rod C
				while(!(gameBoard[rodA-1].isEmpty() && gameBoard[rodB-1].isEmpty() && player.getCurrentDisc() == null))
				{
					// refer to standard algorithm for solution
					player.solve(discs, rodA, rodB, rodC, tiles, gameBoard);
				}
				// add the current players number of turns to the sum of number of turns
				sumNumOfTurns += player.getNumOfTurns();
			}
			// return the total number of turns by all players divided by the number of trials for average turns
			return sumNumOfTurns / trials;
		}

}
