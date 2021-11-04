package ProblemSolutions;
import StackInfrastructure.*;
public class TowerOfHanoiPlayer
{
	private int location;
	private TowerOfHanoiDisc currentDisc;
	public TowerOfHanoiPlayer(int location)
	{
		this.location = location;
		currentDisc = null;
	}
	// gives the palyer a disc to hold
	public void grab(LLNode<TowerOfHanoiDisc> disc)
	{
		 this.currentDisc = disc.getInfo();
	}
	// removes the currently held disc
	public void place()
	{
		this.currentDisc = null;
	}
	// returns the disc currently held
	public TowerOfHanoiDisc getCurrentDisc()
	{
		return this.currentDisc;
	}
	// returns the players locations
	public int getLocation()
	{
		return this.location;
	}
	// contains the logic for a change in location
	public void locationChange(int location, int max, double direction)
	{
		if(location == 0)
			this.location++;
		else if(location == max-1)
			this.location--;
		else if(direction >= 0.5)
			this.location++;
		else
			this.location--;
	}
}
