package estructuras;


public class NodoDobleEncadenado<T> {

	private T item;
	private NodoDobleEncadenado<T> next;
	private NodoDobleEncadenado<T> prev;

	public NodoDobleEncadenado(T value)
	{
		item=value;
		next = null;
		prev = null;
	}

	public T giveItem()
	{
		return item;
	}

	public NodoDobleEncadenado<T> getNext()
	{
		return next;
	}

	public NodoDobleEncadenado<T> getPrev()
	{
		return prev;
	}

	public void setItem(T value)
	{
		item=value;
	}
	public void setNext(NodoDobleEncadenado<T> first)
	{
		next=first;
	}
	public void setPrev(NodoDobleEncadenado<T> actual)
	{
		prev=actual;
	}
}
