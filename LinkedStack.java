package StackInfrastructure;

// Linked List Stack Class
// implements the stack interface for access to stack features
public class LinkedStack<T> implements StackInterface<T>
{
	// reference to the top of the Stack
	private LLNode<T> top;
	// constructor
	public LinkedStack()
	{
		// no data entry yet, the top of the stack must be empty
		setTop(null);
	}
	public void push(T element)
	{
		// Create new Node to push element into
		LLNode<T> newNode = new LLNode<T>(element);
		// designate the new node at the top of the stack
		newNode.setLink(getTop());
		setTop(newNode);
	}
	public void pop() throws StackUnderflowException
	{
		// throws error if stack is empty
		if(isEmpty())
			throw new StackUnderflowException();
		// otherwise the top is replaced with the next highest item in the stack
		else
			setTop(getTop().getLink());
	}
	public T top() throws StackUnderflowException
	{
		// throws error if stack is empty
		if(isEmpty())
			throw new StackUnderflowException();
		// returns the top of the stack
		else
			return getTop().getInfo();
	}
	public boolean isEmpty()
	{
		return (getTop() == null);
	}
	public boolean isFull()
	{
		// Linked List stacks cannot be full
		return false;
	}
	public LLNode<T> getTop()
	{
		return top;
	}
	public void setTop(LLNode<T> top)
	{
		this.top = top;
	}
}
