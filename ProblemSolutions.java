package ProblemSolutions;

public class ProblemSolutions
{
	// produces the sum of the multiples of 3 and 5 up to a given max value
	public static int multiplesThreeAndFive(int maximum)
	{
		int sum = 0;
		// for all ints up to the max
		for(int i = 0; i < maximum; i++)
		{
			// if the number is divisible by 3 or 5 then add it to the sum
			if(i % 3 == 0 || i % 5 == 0)
				sum += i;
		}
		return sum;
	}
	// produces the digit at the given location in Champernowne's constant 
	public static char champernowne(int digit)
	{
		// the constant is a decimal value, as such it must begin with 0.
		String champernowne = new String("0.");
		// in the interest of not computing the entire number, it will only compute up to the value needed
		for(int i = 0; champernowne.length() < digit+2;  i++)
		{
			// concatenate the two strings
			champernowne = champernowne.concat(String.valueOf(i+1));
		}
		// return the character at the position in question
		return champernowne.charAt(digit+1);
	}
}
