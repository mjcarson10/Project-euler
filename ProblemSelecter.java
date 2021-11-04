/*
 * *************************     Matthew Carson     *************************
 * This program allows for the selection of one of several project euler problems
 * when selected, it will receive parameters and solve the problem with the given parameters
 */
import java.util.InputMismatchException;
import java.util.Scanner;
import ProblemSolutions.*;
import ProblemSolutions.DrunkenTowerOfHanoi.*;
import java.lang.Math;
import java.lang.Object;
import StackInfrastructure.*;
public class ProblemSelecter
{
	public static void main(String[] args)
	{
		// open the scanner
		Scanner input = new Scanner(System.in);
		// boolean to facilitate the loop
		boolean runLoop = true;
		// loop until the user cancels
		while(runLoop)
		{
			// prompt user to select a problem
			System.out.println("Please select one of the following problems:");
			System.out.println("a: sum of the multiples of 3 and 5 up to a selected integer");
			System.out.println("b: Champernowne's constant");
			System.out.println("c: Drunken Tower of Hanoi");
			// switch cases for each problem
			switch(input.next().charAt(0))
			{
				// case a produces the sum of all multiples of 3 and 5 up to a maximum integer
				case 'a':
				{
					// boolean determines if the input is valid
					boolean validInteger = false;
					// variable to hold the input
					int maximum;
					while(!validInteger)
					{
						// prompt user to enter an integer
						System.out.println("Please enter an integer");
						// may throw an exception if user enters a string
						try
						{
							maximum = input.nextInt();
							// if the entry is valid print the sum
							System.out.println(ProblemSolutions.multiplesThreeAndFive(maximum));
							// end the loop
							validInteger = true;
						}
						catch(Exception notAnInt)
						{
							// if the input is not an integer, prompt user to try again
							System.out.println("Input is not an integer, please select another");
							// clear the scanner for next input
							input.nextLine();
						}
					}
					break;
				}
				case 'b':
				{
					// boolean determines if the input is valid
					boolean validInteger = false;
					// variable to hold the input
					int digit;
					while(!validInteger)
					{
						// prompt user to enter an integer
						System.out.println("Please enter an integer");
						// may throw an exception if user enters a string
						try
						{
							digit = input.nextInt();
							// if the entry is valid print digit
							System.out.println(ProblemSolutions.champernowne(digit));
							// end the loop
							validInteger = true;
						}
						catch(Exception notAnInt)
						{
							// if the input is not an integer, prompt user to try again
							System.out.println("Input is not an integer, please select another");
							// clear the scanner for next input
							input.nextLine();
						}
					}
					break;
				}
				case 'c':
				{
					// boolean determines if the input is valid
					boolean validInteger = false;
					// variable to hold the input
					int discs;
					int tiles;
					int rodA;
					int rodB;
					int rodC;
					int trials;
					while(!validInteger)
					{
						// prompt user to enter data fields
						System.out.println("Please enter number of discs, number of tiles, location of rod A, rod B, rod C, and number of trials to attempt");
						System.out.println("Please note that tiles must be greater than the location of all rods and");
						System.out.println("that rod A must be the leftmost rod and rod C must be rightmost");
						// may throw an exception if user enters a string, or if rod A, B, or C are out of bounds
						try
						{
							discs = input.nextInt();
							tiles = input.nextInt();
							rodA = input.nextInt();
							rodB = input.nextInt();
							rodC = input.nextInt();
							trials = input.nextInt();
							if((rodA > tiles || rodB > tiles || rodC > tiles) && (rodA >= rodB || rodA >= rodC) && (rodB >= rodC) )
							{
								throw new InputMismatchException("invalid Entry");
							}
							// if the entry is valid print the number of turns to complete drunken tower of hanoi
							System.out.println(DrunkenTowerOfHanoi.drunkenTowerOfHanoi(discs, tiles, rodA, rodB, rodC, trials));
							// end the loop
							validInteger = true;
						}
						catch(InputMismatchException notAnInt)
						{
							// if the input is not an integer, prompt user to try again
							System.out.println("Input is not valid, please try again");
							// clear the scanner for next input
							input.nextLine();
						}
					}
					break;
				}
				case 'd':
				{
					
				}
				default:
				{
					System.out.println("Not a valid option");
				}
			}
			System.out.println("Would you like to continue? Y/N");
			switch(input.next().charAt(0))
			{
				case 'Y':
				case 'y':
				{
					runLoop = true;
					break;
				}
				case 'N':
				case 'n':
				{
					runLoop = false;
					break;
				}
			}
		}
		input.close();
	}
}