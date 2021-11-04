package StackInfrastructure;
// Linked List Node Class
// defines the behaviors of a Linked List Node
public class LLNode<T>
{
	// Class has two parameters, the reference to a variable in info,
	// the reference to the next node in the linked list
	protected T info;
	protected LLNode<T> link;
	// Constructor for linked list node
	public LLNode(T info)
	{
		this.info = info;
		link = null;
	}
	// getter/ setter methods necessary as well
	public void setInfo(T info)
	{
		this.info = info;
	}
	public void setLink(LLNode<T> link)
	{
		this.link = link;
	}
	public T getInfo()
	{
		return info;
	}
	public LLNode<T> getLink()
	{
		return link;
	}
}
