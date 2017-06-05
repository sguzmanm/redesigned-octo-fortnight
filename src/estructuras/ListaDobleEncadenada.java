package estructuras;

import java.util.Iterator;





import java.util.NoSuchElementException;



public class ListaDobleEncadenada<T> implements Iterable<T>{

	private NodoDobleEncadenado<T> first;

	private NodoDobleEncadenado<T> actual;

	private NodoDobleEncadenado<T> last;

	private int size;

	public ListaDobleEncadenada()
	{
		size = 0;
	}

	public boolean isEmpty(){return size == 0; }

	
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator<T>();

	}


	private class DoublyLinkedListIterator<T> implements Iterator<T> {
		private NodoDobleEncadenado<T> ante;

		public DoublyLinkedListIterator()
		{
			ante = new NodoDobleEncadenado<T>(null);
			ante.setNext((NodoDobleEncadenado<T>) first);
		}

		public boolean hasNext()      { if (ante.getNext() !=null) {return true;}else{return false;}}

		public T next() {
			if (!hasNext()){ throw new NoSuchElementException( "No hay elemento siguiente");}
			ante = ante.getNext();
			T item = ante.giveItem();
			return item;
		}

		@Override
		public void remove() {
		}


	}
	
	public void agregarElementoPrincipio(T elem)
	{
		NodoDobleEncadenado<T> newnode= new NodoDobleEncadenado<T>(elem);
		if(last==null) last=newnode;
		if(first==null){
			first=newnode;
			actual=newnode;
			newnode.setNext(null);
			newnode.setPrev(null);
		}else{
			first.setPrev(newnode);
			newnode.setNext(first);
			first=newnode;
		}
		size++;
	}

	public void agregarElementoFinal(T elem) {
		NodoDobleEncadenado<T> newnode = new NodoDobleEncadenado<T>(elem);
		if(first==null){
			first=newnode;
			actual=newnode;
			last=newnode;
			newnode.setNext(null);
			newnode.setPrev(null);
		}else{
			newnode.setPrev(last);
			last.setNext(newnode);
			last=newnode;
		}
		size++;
	}

	public T darElemento(int pos) {
		Iterator<T> it = iterator();
		int i=0;
		T rta=null;
		while(it.hasNext() && i<=pos)
		{
			rta=it.next();
			if(i==pos)
			{
				return rta;
			}
			i++;
		}
		return rta;
	}


	public int darNumeroElementos() {
		return size;
	}

	public T darElementoPosicionActual() throws NullPointerException{
		return actual.giveItem();
	}

	public boolean avanzarSiguientePosicion() {
		if(actual==null)return false;
		else if(actual.getNext()!=null)
		{
			actual=actual.getNext();
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean retrocederPosicionAnterior() {
		if (actual == null) {return false;}
		else if (actual.getPrev() != null){actual = actual.getPrev(); return true;}
		else {return false;}
	}

	public NodoDobleEncadenado<T> getFirst()
	{
		return first;
	}

	public NodoDobleEncadenado<T> getActual()
	{
		return actual;
	}

	public void resetActual()
	{
		actual=first;
	}

	public void increase()
	{
		size++;
	}

	public void setActual(NodoDobleEncadenado<T> x) {

		actual=x;
	}

	public void setFirst(NodoDobleEncadenado<T> x)
	{
		first=x;
	}
	
	public void eliminarContenido()
	{
		first=null;
		actual=null;
		last=null;
		size=0;
	}
	public void eliminarElemento(int pos){
		if (first == null) {
			return;
		}
		if (pos == 0) {
			first = first.getNext();
			
			if (first == null) {
				last = null;
			}
			else
			{
				first.setPrev(null);
			}
		}

		else{
			NodoDobleEncadenado<T> temp = first;
			for (int index = 0; index < pos; index++){
				temp = temp.getNext();
			}
			if(temp.getNext()!=null)temp.getNext().setPrev(temp.getPrev());
			temp.getPrev().setNext(temp.getNext());
		}
		size--;
	}

	public void setLast(NodoDobleEncadenado<T> actual2) {
		last=actual2;
	}
	
	public NodoDobleEncadenado<T> getLast()
	{
		return last;
	}
	
	public void agregarLista(ListaDobleEncadenada<T> lista)
	{
		last.setNext(lista.getFirst());
		last=lista.getLast();
	}

	public void agregarElemento(int pos,T elemento) {
		if(pos==0) agregarElementoPrincipio(elemento);
		else if (pos==size) agregarElementoFinal(elemento);
		else if (pos>0 && pos<size)
		{
			NodoDobleEncadenado<T> it = first.getNext();
			int i=1;
			NodoDobleEncadenado<T> nodo= new NodoDobleEncadenado<T>(elemento);
			while(it.getNext()!=null && i<=pos)
			{
				if(i==pos)
				{
					it.getPrev().setNext(nodo);
					nodo.setPrev(it.getPrev());
					it.setPrev(nodo);
					nodo.setNext(it);
					return;
				}
				i++;
				it=it.getNext();
			}
			if(last.getPrev()!=null)
			last.getPrev().setNext(nodo);
			nodo.setPrev(last.getPrev());
			last.setPrev(nodo);
			nodo.setNext(last);
		}
		else return;
		
	}
}

