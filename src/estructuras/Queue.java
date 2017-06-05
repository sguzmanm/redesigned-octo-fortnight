package estructuras;

import java.util.Iterator;

public class Queue<T> {	

	private ListaDobleEncadenada<T> lista;

	public Queue(){

		lista = new ListaDobleEncadenada<T>();
	}


	public void enqueue(T elem) {
		lista.agregarElementoFinal(elem);
	}

	public T dequeue() {
		T item = null;
		if (this.size()==0) {
			return item;
		}else{
			item = lista.darElemento(0);
			lista.eliminarElemento(0);
			return item;
		}
	}

	public int size(){
		int size = lista.darNumeroElementos();
		return size;
	}

	public T peek() {
		return lista.darElementoPosicionActual();
	}

	public boolean isEmpty(){
		if (this.size() == 00) {
			return true;
		}
		else{
			return false;
		}
	}




	
}