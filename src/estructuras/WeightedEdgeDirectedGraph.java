package estructuras;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WeightedEdgeDirectedGraph<K,V> {
	
	
	private EncadenamientoSeparadoTH<K,V> vertices;
	
	private EncadenamientoSeparadoTH<K,ListaDobleEncadenada<Edge<K>>> adj;
	
	private EncadenamientoSeparadoTH<K,Boolean> marcados;
	
	private ArrayList<NodoCamino<K>> camino;
	
	private int limite;
	
	private EncadenamientoSeparadoTH<String,ArrayList<NodoCamino<K>>> caminos;
	
	private int numVertices;
	
	private int numEdges;
	
	public WeightedEdgeDirectedGraph()
	{
		vertices = new EncadenamientoSeparadoTH<K,V>(29);
		adj=new EncadenamientoSeparadoTH<K,ListaDobleEncadenada<Edge<K>>>(29);
		marcados= new EncadenamientoSeparadoTH<K,Boolean>(29);
		numVertices=0;
		numEdges=0;

	}
	public int numVertices() {
		return numVertices;
	}
	public V darVertice(K id) throws NoSuchElementException {
		if(vertices==null) throw new NoSuchElementException();
		V valor=vertices.darValor(id);
		if(valor==null) throw new NoSuchElementException();
		else return valor;
	}

	
	public void agregarVertice(K id, V infoVer) {
		vertices.insertar(id, infoVer);
		numVertices++;
		ListaDobleEncadenada<Edge<K>> hash= new ListaDobleEncadenada<Edge<K>>();
		adj.insertar(id, hash);
		
		
	}

	
	public int numArcos() {
		return numEdges;
	}

	
	public double darPeso(K idOrigen, K idDestino) throws NoSuchElementException {
		ListaDobleEncadenada<Edge<K>> insertado=adj.darValor(idOrigen);
		if(insertado==null) throw new NoSuchElementException();
		Iterable<Edge<K>> edges=insertado;
		for(Edge<K> e:edges)
		{
			if(e.getOrigin().equals(idOrigen)&&e.getEnd().equals(idDestino)) 
			{
				return e.getWeight();
			}
		}
		throw new NoSuchElementException();
	}

	
	public void agregarArco(K idOrigen, K idDestino, double[] peso) {
		ListaDobleEncadenada<Edge<K>> lista=adj.darValor(idOrigen);
		Edge<K> edge=new Edge<K>(idOrigen,idDestino,peso);
		if(lista.isEmpty()) 
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		for(Edge<K> e:lista )
		{
			if(idDestino.equals(e.getEnd()))
			{
				e.setWeight(peso);
				return;
			}
			
		}
		lista=adj.darValor(idOrigen);
		lista.agregarElementoFinal(edge);
		numEdges++;
		
	}

	
	public Iterator<K> darVertices() {
		try
		{
			return vertices.llaves().iterator();
		}
		catch (NullPointerException e)
		{
			return null;
		}
		
	}

	
	public int darGrado(K id) {
		int contador=0;
		ListaDobleEncadenada<Edge<K>> insertado=adj.darValor(id);
		if(insertado==null) return contador;
		Iterator<Edge<K>> iter=insertado.iterator();
		while(iter.hasNext())
		{
			contador++;
			iter.next();
		}
		return contador;
	}

	public Iterable<Edge<K>> adj(K id)
	{
		return adj.darValor(id);
	}
	
	public Iterator<K> darVerticesAdyacentes(K id) {
		Iterable<Edge<K>> insertado=adj.darValor(id);
		if(insertado==null) return null;
		ListaDobleEncadenada<K> lista=new ListaDobleEncadenada<K>();
		for(Edge<K> e:insertado)
		{
			lista.agregarElementoFinal(e.getEnd());
		}
		return lista.iterator();
	}

	
	public void desmarcar() {
		Iterable<K> llaves=marcados.llaves();
		if(llaves==null) return;
		for(K i:llaves)
		{
			marcados.insertar(i, false);
		}
		
	}

	
	public ArrayList<NodoCamino<K>> DFS(K idOrigen) {
		camino= (new ArrayList<NodoCamino<K>>());
		for(int i=0;i<numVertices();i++)
		{
			camino.add(new NodoCamino<K>(null,null,0,0));
		}
		dfs(idOrigen);
		return camino;
		
	}
	
	private void dfs(K key)
	{
		marcados.insertar(key, true);
		Iterator<K> adj=darVerticesAdyacentes(key);
		K llave=null;
		NodoCamino<K> nodo=null;
		NodoCamino<K> temp=null;
		if(adj==null) return;
		while(adj.hasNext())
		{
			llave=adj.next();
			if(llave.equals(key)||marcados.darValor(llave)==null||!marcados.darValor(llave)) 
			{
				nodo= new NodoCamino<K>(llave,key,darPeso(key,llave),1);
				for(int i=0;i<numVertices();i++)
				{
					temp=camino.get(i);
					if(temp.intermedio()==null&&temp.fin()==null)
					{
						camino.set(i,nodo);
						break;
					}
					else
					{
						if(temp.fin().equals(nodo.intermedio())&&!(temp.fin().equals(temp.intermedio())))
						{
							nodo.increaseLength(temp.length());
							nodo.increaseWeight(temp.weight());
						}
					}
					
				}
				if(!llave.equals(key))
				dfs(llave);
			}
		}
	}

	
	public ArrayList<NodoCamino<K>> BFS(K idOrigen) {
		camino= new ArrayList<NodoCamino<K>>();
		for(int i=0;i<numVertices;i++)
		{
			camino.add(new NodoCamino<K>(null,null,0,0));
		}
		bfs(idOrigen,0,0);
		return camino;
	}
	
	private void bfs(K key, double weight, int length)
	{
		marcados.insertar(key, true);
		Queue<K> queue= new Queue<K>();
		queue.enqueue(key);
		Iterator<K> adj=null;
		K aux=null;
		NodoCamino<K> nodo=null;
		NodoCamino<K> temp=null;
		
		while(!queue.isEmpty())
		{
			K llave=queue.dequeue();
			adj=darVerticesAdyacentes(llave);
			if(adj!=null)
			{
				while(adj.hasNext())
				{
					aux=adj.next();
					if(llave.equals(aux)||marcados.darValor(aux)==null||!marcados.darValor(aux))
					{
						if(!llave.equals(aux))
						queue.enqueue(aux);
						marcados.insertar(aux, true);
						nodo= new NodoCamino<K>(aux,llave,darPeso(llave,aux),1);
						for(int i=0;i<numVertices();i++)
						{
							temp=camino.get(i);
							if(temp.intermedio()==null&&temp.fin()==null)
							{
								camino.set(i,nodo);
								break;
							}
							else
							{
								if(temp.fin().equals(nodo.intermedio())&&!(temp.fin().equals(temp.intermedio())))
								{
									nodo.increaseLength(temp.length());
									nodo.increaseWeight(temp.weight());
								}
							}
						}
						
						
				
					}
			
				}
			}
	}
}

	
	public ArrayList<NodoCamino<K>> darCaminoDFS(K idOrigen, K idDestino) {
		 ArrayList<NodoCamino<K>> aux=new ArrayList<NodoCamino<K>>();
		 ArrayList<NodoCamino<K>> total=DFS(idOrigen);
		 if(total==null) return null;
		 else if (total.size()==0) return null;
		K temp=idDestino;
		boolean encuentraOrigen=false;
		System.out.println(total.size());
		while(!encuentraOrigen)
		{
			for(int j=0;j<total.size();j++)
			{
				if(total.get(j).intermedio()!=null && total.get(j).fin()!=null)
				{
					if(total.get(j).fin().equals(temp))
					{
						aux.add(total.get(j));
						temp=total.get(j).intermedio();
						break;
					}
					
				}
				if(j==total.size()-1) 
				{
					return null;
				}
				System.out.println(j);
				
			}
			if(temp.equals(idOrigen)) encuentraOrigen=true;
		}
		total=aux;
		int tamanho=0;
		for(int i=0;i<total.size();i++)
		{
			if(total.get(i)!=null) tamanho++;
			else break;
		}
		aux= (new ArrayList<NodoCamino<K>>());
		for(int i=tamanho-1;i>=0;i--)
		{
			aux.add(total.get(i));
		}
		if(aux.size()==0) aux=null;
		return aux;
	}

	
	public  ArrayList<NodoCamino<K>> darCaminoBFS(K idOrigen, K idDestino) {
		 ArrayList<NodoCamino<K>> aux=new ArrayList<NodoCamino<K>>();
		 ArrayList<NodoCamino<K>> total=BFS(idOrigen);
		 if(total==null) return null;
		 else if (total.size()==0) return null;
		K temp=idDestino;
		boolean encuentraOrigen=false;
		while(!encuentraOrigen)
		{
			for(int j=0;j<total.size();j++)
			{
				if(total.get(j).intermedio()!=null && total.get(j).fin()!=null)
				{
					if(total.get(j).fin().equals(temp))
					{
						aux.add(total.get(j));
						temp=total.get(j).intermedio();
						break;
					}
					
				}
				if(j==total.size()-1) 
				{
					return null;
				}
				
				
			}
			if(temp.equals(idOrigen)) encuentraOrigen=true;
		}
		
		total=aux;
		int tamanho=0;
		for(int i=0;i<total.size();i++)
		{
			if(total.get(i)!=null) tamanho++;
			else break;
		}
		aux= (new ArrayList<NodoCamino<K>>());
		for(int i=tamanho-1;i>=0;i--)
		{
			aux.add(total.get(i));
		}
		if(aux.size()==0) aux=null;
		return aux;
	}
	
	

}
