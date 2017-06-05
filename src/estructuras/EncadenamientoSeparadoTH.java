package estructuras;

import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.LLOAD;


//En esta clase cambi� todos los objetos Object por sus respectivos K y V, as� es gen�rico del todo.
public class EncadenamientoSeparadoTH<K,V>  {

	private ListaLlaveValorSecuencial<K,V>[] listas;
	private boolean modificado;
	private int size;
	private ListaDobleEncadenada<K> lista;
	public EncadenamientoSeparadoTH(int m)
	{
		size=m;
		listas= (ListaLlaveValorSecuencial<K,V>[]) new ListaLlaveValorSecuencial[m];
		modificado=false;
		
	}
	public int darTamanio() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean estaVacia() {
		// TODO Auto-generated method stub
		return size==0;
	}

	public boolean tieneLlave(K llave) {
		if(llave==null)return false;
		if(listas[hash((K)llave)]==null) return false;
		return !listas[hash((K)llave)].estaVacia();
	}

	public V darValor(K llave) {
		if(llave==null) return null;
		if (listas[hash(llave)]==null) return null;
		return listas[hash(llave)].darValor(llave);
	}

	public void insertar(K llave, V valor) {

		if(llave==null) return;
		ListaLlaveValorSecuencial<K,V> x=listas[hash((K)llave)];
		if(x==null)
		{
			listas[hash((K)llave)]= new ListaLlaveValorSecuencial<K,V>();
		}
		listas[hash((K)llave)].insertar(llave, valor);
		modificado=true;
		
	}

	public Iterable<K> llaves() {
		if(modificado)
		{
			lista=new ListaDobleEncadenada<>();
			for(int i=0;i<size;i++)
			{
				if(listas[i]!=null)
				{
					for(K key:listas[i].llaves())
					{
						lista.agregarElementoFinal(key);
					}
				}
			}
		}
		return lista;
		
		
	}

	public int[] darLongitudListas() {
		int[] longitud= new int[size];
		for(int i=0;i<listas.length;i++)
		{
			if(listas[i]==null) longitud[i]=0;
			else longitud[i]=listas[i].darTamanio();
		}
		return longitud;
	}
	
	private int hash(K llave)
	{
		return (llave.hashCode() & 0x7FFFFFFF)%size;
	}

	
}
