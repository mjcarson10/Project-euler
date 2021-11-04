package ProblemSolutions;
// Tower of Hanoi disc object,
//stores the size of each disc and provides a method for comparing discs
public class TowerOfHanoiDisc
{
	private int size;
	
	public TowerOfHanoiDisc()
	{
		size = 1;
	}
	public TowerOfHanoiDisc(int size)
	{
		this.size = size;
	}
	public boolean compare(TowerOfHanoiDisc disc2)
	{
		boolean isLarger;
		if(this.size > disc2.getSize())
		{
			isLarger = true;
		}
		else if( this.size < disc2.getSize())
		{
			isLarger = false;
		}
		else
		{
			isLarger = true;
		}
		return isLarger;
	}
	public int getSize()
	{
		return this.size;
	}
}
