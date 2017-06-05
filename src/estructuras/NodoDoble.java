package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class NodoDoble<K ,V> implements Iterable<K>
{
	private V item;
	private NodoDoble<K,V> next;
	private NodoDoble<K,V> prev;
	private K key;
	public NodoDoble(V value,K k)
	{
		key=k;
		item=value;
		next = null;
		prev = null;
	}

	public V giveItem()
	{
		return item;
	}

	public NodoDoble<K,V> getNext()
	{
		return next;
	}

	public NodoDoble<K,V> getPrev()
	{
		return prev;
	}

	public void setItem(V value)
	{
		item=value;
	}
	public void setNext(NodoDoble<K,V> first)
	{
		next=first;
	}
	public void setPrev(NodoDoble<K,V> actual)
	{
		prev=actual;
	}
	
	public K giveKey()
	{
		return key;
	}

	public Iterator<K> iterator()
	{
		return new Iter();
	}
	
	private class Iter implements Iterator<K>
	{
		private NodoDoble<K,V> ante;

		public Iter()
		{
			ante = new NodoDoble<K,V>(null,null);
			ante.setNext(NodoDoble.this);
		}

		public boolean hasNext()      { if (ante.getNext() !=null) {return true;}else{return false;}}

		public K next() {
			if (!hasNext()){ throw new NoSuchElementException( "No hay elemento siguiente");}
			ante = ante.getNext();
			K item = (K) ante.giveKey();
			return item;
		}

		@Override
		public void remove() {
		}
	}
}
