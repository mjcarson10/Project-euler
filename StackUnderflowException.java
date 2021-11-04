package StackInfrastructure;

public class StackUnderflowException extends RuntimeException
{
	public StackUnderflowException()
	{
		super();
	}
	public StackUnderflowException(String error)
	{
		super(error);
	}
}
