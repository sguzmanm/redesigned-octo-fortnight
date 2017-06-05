package estructuras;

import java.util.Iterator;

import java.util.NoSuchElementException;


public class ListaLlaveValorSecuencial<K,V> {

	
	
	private int size;
	private K llave;
	private NodoDoble<K,V> primero;
	public ListaLlaveValorSecuencial()
	{
		
	}
	public int darTamanio() {
		return size;
	}

	public boolean estaVacia() {
		return size==0;
	}

	public boolean tieneLlave(K llave) {
		
		return false;
	}

	public V darValor(K llave) {
		if(primero==null) return null;
		if(primero.giveKey().equals((K)llave)) return primero.giveItem();
		NodoDoble<K,V> current= primero;
		while(current.getNext()!=null)
		{
			current=current.getNext();
			if(current.giveKey().equals((K)llave)) 
				return current.giveItem();
		}
		return null;
		
	}

	public void insertar(K llave, V valor) {
		if(primero==null)
		{
			primero= new NodoDoble<K,V>((V)valor,(K)llave);
			size++;
			return;
		}
		else if(primero.giveKey().equals((K)llave))
		{
			primero.setItem((V)valor);
			return;
		}
		NodoDoble<K,V> current= primero;
		while(current.getNext()!=null) 
		{
			current= current.getNext();
			if(current.giveKey().equals((K)llave))
			{
				current.setItem((V)valor);
				return;
			}
		}
		current.setNext(new NodoDoble<K,V>((V)valor,(K)llave));
		size++;
	}
	

	public Iterable<K> llaves() {
		return (Iterable<K>) primero;
	}
	
	public static void main(String[] args) {
		ListaLlaveValorSecuencial<String,Integer> l= new ListaLlaveValorSecuencial<String,Integer>();
		l.insertar("S", 1);
		l.insertar("D", 2);
		l.insertar("A", 3);
		l.insertar("Z", 1);
		Iterable<String> it=l.llaves();
		Iterator<String> iter= it.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
	}


	
}
