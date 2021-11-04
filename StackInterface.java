package StackInfrastructure;

// Interface for the Stack 
public interface StackInterface<T> 
{
	// Push a new element into the Stack, 
	// may throw overflow exception if Stack is full
	void push(T element) throws StackOverflowException; 
	// Pop a new element off the top of the stack, 
	// may throw underflow exception if Stack is empty
	void pop() throws StackUnderflowException;
	// Reveals top element of stack,
	// may throw underflow error if Stack is empty 
	T top() throws StackUnderflowException;
	// determines true or false based on whether or not the Stack has an entry
	public boolean isEmpty();
	// determines true or false based on whether or not the Stack is at maximum capacity
	boolean isFull();
	
}
