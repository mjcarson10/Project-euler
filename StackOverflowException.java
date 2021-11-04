package StackInfrastructure;

public class StackOverflowException extends RuntimeException
{
	public StackOverflowException()
	{
		super();
	}
	public StackOverflowException(String error)
	{
		super(error);
	}
}
