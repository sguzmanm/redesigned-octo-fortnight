package estructuras;

import java.util.ArrayList;

public class IndexMinHeap<K, T extends Comparable<T>> {

	private ArrayList<Valor> heap;
	private int size = 0;
	private int max = 0;
	
	private class Valor implements Comparable<Valor>{
		private K indice;
		private T valor;
		
		public Valor(K k,T v)
		{
			indice=k;
			valor=v;
		}
		
		public void replace(T v){valor=v;}
		
		public int compareTo(Valor o)
		{
			return valor.compareTo(o.valor);
		}
		
	}

	public void crearCP()
	{ 
		max=10;
		heap = new ArrayList<Valor>();
		for(int i=0;i<6;i++)
		{
			heap.add(null);
		}
	}

	public int darNumElementos()
	{ 
		return size;
	}

	public void agregar(K indice,T valor) throws Exception
	{
		Valor elemento=new Valor(indice,valor);
		if(indice==null) 
		{
			throw new Exception("El objeto a agregar es nulo");
		}
		if(size==0 || size < max)
		{
			heap.set(++size, elemento);
			swim(size);
		}
		if(size>=max/2) 
		{
			max=2*max+1;
			resize(max);
		}
	}

	public K min()
	{
		if (esVacia()) return null;
		Valor min = heap.get(1);
		exch(1, size--); 
		heap.set(size+1,null); 
		sink(1);
		if(size<=max/8 && size>4) 
			{
				max=max/2;
				resize(max/2);
			}

		return min.indice;
	}
	
	public T valorMin()
	{
		if (esVacia()) return null;
		Valor min = heap.get(1);
		exch(1, size--); 
		heap.set(size+1,null); 
		sink(1);
		if(size<=max/8 && size>4) 
			{
				max=max/2;
				resize(max/2);
			}

		return min.valor;
	}

	public boolean esVacia()
	{ 
		return size == 0; 
	}

	public int tamanoMax()
	{
		return max;
	}
	
	public boolean contains(K elem)
	{
		for(int i=1;i<size+1;i++)
		{
			if(heap.get(i).indice.equals(elem)) return true;
		}
		return false;
	}
	
	public void replace(K elem,T nuevo)
	{
		for(int i=1;i<size+1;i++)
		{
			if(heap.get(i).indice.equals(elem))
			{
				heap.get(i).replace(nuevo);
				exch(i,size);
				sink(i);
				break;
			}
		}
	}

	private void resize(int capacity) {
			ArrayList<Valor> temp = new ArrayList<Valor>();
			temp.add(null);
			for (int i = 1; i <= capacity; i++) {
				if(i<=size)
				temp.add(heap.get(i));
				else temp.add(null);
			}
			heap = temp;

	}

	private boolean greater(int i, int j)
	{ 
		return heap.get(i).compareTo(heap.get(j)) > 0; }

	private void exch(int i, int j)
	{ Valor t = heap.get(i); heap.set(i, heap.get(j)); heap.set(j, t);  }

	private void swim(int k)
	{
		while (k > 1 && greater(k/2, k))
		{
			exch(k, k/2);
			k = k/2;
		}
	}
	private void sink(int k)
	{
		while (2*k <= size)
		{
			int j = 2*k;
			if (j < size && greater(j, j+1)) j++;
			if (!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	public static void main(String[] args) throws Exception {

		

	}

}
