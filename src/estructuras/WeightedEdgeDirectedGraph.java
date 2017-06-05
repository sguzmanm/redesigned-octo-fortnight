package estructuras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import api.IWeightedDirectedGraph;

public class WeightedEdgeDirectedGraph<K,V> implements IWeightedDirectedGraph<K,V> {
	
	
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
			if(e.origin().equals(idOrigen)&&e.end().equals(idDestino)) 
			{
				if(e instanceof SpecialEdge)
				{
					if(!((SpecialEdge<K>)e).sePuede()) return -1;
				}
				return e.weight();
			}
		}
		throw new NoSuchElementException();
	}

	
	public void agregarArco(K idOrigen, K idDestino, double peso) {
		agregarArco(idOrigen,idDestino,peso,'z');
	}

	
	public void agregarArco(K idOrigen, K idDestino, double peso, char ordenLexicografico) {
		ListaDobleEncadenada<Edge<K>> lista=adj.darValor(idOrigen);
		Edge<K> edge=new Edge<K>(idOrigen,idDestino,peso,ordenLexicografico);
		if(lista.isEmpty()) 
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		for(Edge<K> e:lista )
		{
			if(idDestino.equals(e.end()))
			{
				e.setWeight(peso);
				return;
			}
			
		}
		lista=adj.darValor(idOrigen);
		if(ordenLexicografico<lista.darElemento(0).caracter()) 
		{
			lista.agregarElementoPrincipio(edge);
			numEdges++;
			return;
		}
		else if (ordenLexicografico>=lista.getLast().giveItem().caracter())
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		int i=0;
		Edge<K> temp=null;
		for(Edge<K> e:lista )
		{
			if(temp!=null)
			{
				if (ordenLexicografico>temp.caracter() && ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
				else if (ordenLexicografico==temp.caracter()&&ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
			}
			temp=e;
			i++;
		}
		
	}

	public void agregarArcoEspecial(K idOrigen, K idDestino, char ordenLexicografico, long pesoManana, long pesoTarde, long pesoNoche, long horaInicio, long horaFin)
	{
		ListaDobleEncadenada<Edge<K>> lista=adj.darValor(idOrigen);
		SpecialEdge<K> edge= new SpecialEdge<K>(idOrigen,idDestino,horaInicio,horaFin,pesoManana,pesoTarde,pesoNoche,ordenLexicografico);
		if(lista.isEmpty()) 
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		for(Edge<K> e:lista )
		{
			if(idDestino.equals(e.end()))
			{
				return;
			}
		}
		lista=adj.darValor(idOrigen);
		if(ordenLexicografico<lista.darElemento(0).caracter()) 
		{
			lista.agregarElementoPrincipio(edge);
			numEdges++;
			return;
		}
		else if (ordenLexicografico>=lista.getLast().giveItem().caracter())
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		int i=0;
		Edge<K> temp=null;
		for(Edge<K> e:lista )
		{
			if(temp!=null)
			{
				if (ordenLexicografico>temp.caracter() && ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
				else if (ordenLexicografico==temp.caracter()&&ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
			}
			temp=e;
			i++;
		}
	}
	
	public void agregarArcoEspecial(K idOrigen, K idDestino, char ordenLexicografico, long horaInicio, long horaFin)
	{
		ListaDobleEncadenada<Edge<K>> lista=adj.darValor(idOrigen);
		SpecialEdge<K> edge= new SpecialEdge<K>(idOrigen,idDestino,horaInicio,horaFin,ordenLexicografico);
		if(lista.isEmpty()) 
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		for(Edge<K> e:lista )
		{
			if(idDestino.equals(e.end()))
			{
				return;
			}
		}
		lista=adj.darValor(idOrigen);
		if(ordenLexicografico<lista.darElemento(0).caracter()) 
		{
			lista.agregarElementoPrincipio(edge);
			numEdges++;
			return;
		}
		else if (ordenLexicografico>=lista.getLast().giveItem().caracter())
		{
			lista.agregarElementoFinal(edge);
			numEdges++;
			return;
		}
		int i=0;
		Edge<K> temp=null;
		for(Edge<K> e:lista )
		{
			if(temp!=null)
			{
				if (ordenLexicografico>temp.caracter() && ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
				else if (ordenLexicografico==temp.caracter()&&ordenLexicografico<e.caracter())
				{
					lista.agregarElemento(i,edge);
					numEdges++;
					return;
				}
			}
			temp=e;
			i++;
		}
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
			lista.agregarElementoFinal(e.end());
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
	
	public Iterable<String> dfsLimite(int l,K source)
	{
		limite=l;
		caminos= new EncadenamientoSeparadoTH<String,ArrayList<NodoCamino<K>>>(97);
		camino= new ArrayList<NodoCamino<K>>();
		marcados=new EncadenamientoSeparadoTH<K,Boolean>(97);
		for(int i=0;i<l;i++)
		{
			camino.add(new NodoCamino<K>(null,null,0,0));
		}
		dfsLim(source);
		return caminos.llaves();
	}
	
	
	
	private void dfsLim(K source) {
		Iterable<Edge<K>> vertices=adj(source);
		NodoCamino<K> temp = null;
		ArrayList<NodoCamino<K>> aux;
		String msj=null;
		marcados.insertar(source, true);
		if(vertices==null) 
		{
			msj="";
			aux=new ArrayList<NodoCamino<K>>();
			for(int i=0;i<camino.size();i++)
			{
				if(temp!=null)
				{
					if(!temp.fin().equals(camino.get(i).intermedio())) break;
				}
				msj+=camino.get(i).intermedio()+"-";
				msj+=camino.get(i).fin()+".";
				aux.add(camino.get(i));
				temp=camino.get(i);
			}
			marcados.insertar(source, false);
			caminos.insertar(msj, aux);
			camino.set(camino.size()-limite,new NodoCamino<K>(null,null,0,0));
			limite++;

			return;
		}
		else
		{
			NodoCamino<K> nodo=null;
			for(Edge<K> e:vertices)
			{
				limite--;
				nodo= new NodoCamino<K>(e.end(),e.origin(),e.weight(),1);
				boolean marcado=false;
				System.out.println("HOLA "+e.origin()+" "+e.end());
				if(marcados.darValor(e.end())!=null && marcados.darValor(e.end())) marcado=true;
				if(marcado) marcados.insertar(e.origin(), false);
				for(int i=0;i<camino.size() && !marcado;i++)
				{
					temp=camino.get(i);
					if(temp.intermedio()==null&&temp.fin()==null)
					{
						camino.set(i,nodo);
						break;
					}
					else if (temp.fin().equals(nodo.fin())&&temp.intermedio().equals(nodo.intermedio()))
					{
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
				if(limite>0 && !marcado)
				{
					dfsLim(e.end());
					limite++;
				}
				else
				{
					msj="";
					aux=new ArrayList<NodoCamino<K>>();
					temp=null;
					for(int i=0;i<camino.size();i++)
					{
						if(camino.get(i).intermedio()==null && camino.get(i).fin()==null) break;
						System.out.println(camino.get(i).intermedio()+" "+camino.get(i).fin()+" "+i);
						if(temp!=null)
						{
							if(!temp.fin().equals(camino.get(i).intermedio())) break;
						}
						System.out.println("SIGUE");
						msj+=camino.get(i).intermedio()+"-";
						msj+=camino.get(i).fin()+".";
						System.out.println("XXX"+msj);
						aux.add(camino.get(i));
						temp=camino.get(i);	
					}
					if(aux.size()!=0)
					caminos.insertar(msj,aux);
					camino.set(camino.size()-1-limite,new NodoCamino<K>(null,null,0,0));
					limite++;
				}
			}
			aux=new ArrayList<NodoCamino<K>>();
			msj="";
			for(int i=0;i<camino.size();i++)
			{
				if(camino.get(i).intermedio()!=null && camino.get(i).fin()!=null)
				{
					msj+=camino.get(i).intermedio()+"-";
					msj+=camino.get(i).fin()+".";
					aux.add(camino.get(i));

				}
				else break;
			}
			if(aux.size()!=0)
			caminos.insertar(msj,aux);
			marcados.insertar(source, false);
			if(limite!=camino.size())
			camino.set(camino.size()-1-limite,new NodoCamino<K>(null,null,0,0));
		}
		
		
	}
	public static void main(String[] args) {
		WeightedEdgeDirectedGraph<Integer,Integer> wdg= new WeightedEdgeDirectedGraph<Integer,Integer>();
		for (int i = 1; i < 10; i++) {
			wdg.agregarVertice(i, i);	
		}
		wdg.agregarArco(1, 5, 4.0, 'a');
		wdg.agregarArco(2, 4, 1.0, 'b');
		wdg.agregarArco(2, 9, 4.0, 'a');
		wdg.agregarArco(3, 1, 2.0, 'a');
		wdg.agregarArco(3, 5, 4.0, 'b');
		wdg.agregarArco(4, 8, 3.0, 'a');
		wdg.agregarArco(5, 7, 5.0, 'a');
		wdg.agregarArco(5, 6, 5.0, 'd');
		wdg.agregarArco(5, 2, 8.0, 'b');
		wdg.agregarArco(5, 4, 8.0, 'c');
		wdg.agregarArco(6, 3, 6.0, 'c');
		wdg.agregarArco(6, 4, 4.0, 'a');
		wdg.agregarArco(6, 8, 6.0, 'b');
		wdg.agregarArco(7, 1,10.0, 'c');
		wdg.agregarArco(7, 2, 1.0, 'b');
		wdg.agregarArco(7, 9, 6.0, 'a');
		wdg.agregarArco(8, 3, 4.0, 'a');
		wdg.agregarArco(9, 1, 4.0, 'a');
		


	
		Iterable<String> nodos=wdg.dfsLimite(90, 3);
		int i=0;
			for(String n:nodos)
			{
				System.out.println("CAM "+i+" "+n);
				ArrayList<NodoCamino<Integer>> cam=wdg.caminos.darValor(n);
				for(int j=0;j<cam.size();j++)
				{
					System.out.println(cam.get(j).intermedio()+" "+cam.get(j).fin());
				}
				i++;
			}


	}
	public boolean noMarcado() {
		boolean m = true;
		Iterable<K> llaves=marcados.llaves();
		if(llaves==null) return m;
		else
		{
			for (K i : llaves) {
				if (marcados.darValor(i)) {
					m=false;
					break;
				}
			}
		}
		return m;
	}
	
	
	

}
