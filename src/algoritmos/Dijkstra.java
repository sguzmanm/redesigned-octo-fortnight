package algoritmos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import estructuras.Edge;
import estructuras.EncadenamientoSeparadoTH;
import estructuras.IndexMinHeap;
import estructuras.ListaDobleEncadenada;
import estructuras.NodoCamino;
import estructuras.WeightedEdgeDirectedGraph;

public class Dijkstra<K,V> {
	
	private K source;
		private ArrayList<NodoCamino<K>> camino;
	     private EncadenamientoSeparadoTH<K,Double> distTo;
	     private IndexMinHeap<K,Double> pq;
	     public Dijkstra(WeightedEdgeDirectedGraph<K,V> G, K s)
	     {
	    	 source=s;
	    	 camino=new ArrayList<NodoCamino<K>>();
	    	 pq=new IndexMinHeap<K,Double>();
	    	 pq.crearCP();
	    	 distTo=new EncadenamientoSeparadoTH<K,Double>(97);

	        Iterator<K> vertices=G.darVertices();
	        while(vertices.hasNext()) 
	        	{
	        		distTo.insertar(vertices.next(), Double.POSITIVE_INFINITY);
	        		camino.add(new NodoCamino<K>(null,null,0,0));
	        	}
	        distTo.insertar(s, 0.0);
	        try
	        {
		        pq.agregar(s,0.0);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        while (!pq.esVacia())
	           relax(G, pq.min());
	     }
	     private void relax(WeightedEdgeDirectedGraph<K,V> G, K v)
	     {
      	   boolean agregado=false;
      	   double getWeight=0;
	        for(Edge<K> e : G.adj(v))
	        {
	           K w = e.getEnd();
	           if (distTo.darValor(w) > distTo.darValor(v) + e.getWeight())
	           {
	        	   agregado=false;
	        	   getWeight=e.getWeight();
        		  distTo.insertar(w, distTo.darValor(v) + getWeight);
 	              NodoCamino<K> nodo= new NodoCamino<K>(w,v,getWeight,1);
 	              NodoCamino<K> temp=null;
 	              for(int i=0;i<camino.size();i++)
 	              {
 					temp=camino.get(i);
 					if(!agregado&&temp.intermedio()==null&&temp.fin()==null)
 					{
 						camino.set(i,nodo);
 						agregado=true;
 						break;
 					}
 					else
 					{
 						if(temp.intermedio()==null&&temp.fin()==null)
 						{
 							if(!agregado)
 							{
 								camino.set(i,nodo);
 	 							agregado=true;
 							}
 							break;
 						}
 						if(temp.fin().equals(nodo.fin()))
 						{
 							agregado=true;
 							camino.set(i, nodo);
 						}
 						else if(temp.fin().equals(nodo.intermedio())&&!(temp.fin().equals(temp.intermedio())))
 						{
 							nodo.increaseLength(temp.length());
 							nodo.increaseWeight(temp.weight());
 							if(agregado) break;
 						}
 					}
 					
 	              }
 	              try
 	              {
 	            	  if (pq.contains(w)) pq.replace(w, distTo.darValor(w));
 		              else                pq.agregar(w, distTo.darValor(w));
 	              }
 	              catch(Exception exc)
 	              {
 	            	  exc.printStackTrace();
 	              }
        	   }
	              
	              
	           }
	        }
	public double distTo(K v)
	{
		return distTo.darValor(v);
	}
	public boolean hasPathTo(K v)
	{
		Iterable<K> iter=pathTo(v);
		int contador=0;
		if(iter==null) return false;
		for(K nodo:iter)
		{
			contador++;
		}
		return contador!=0;
	}
	public Iterable<K> pathTo(K v)
	{
		int i=0;
		K temp=v;
		NodoCamino<K> nodo=null;
		boolean llegaOrigen=false;
		ListaDobleEncadenada<K> lista=new ListaDobleEncadenada<K>();
		while(!llegaOrigen)
		{
			for(i=0;i<camino.size();i++)
			{
				nodo=camino.get(i);
				if(temp.equals(source))
				{
					llegaOrigen=true;
					lista.agregarElementoPrincipio(temp);
					break;
				}
				if(nodo.fin()==null && nodo.intermedio()==null)
				{
					llegaOrigen=true;
					break;
				}
				if(temp.equals(nodo.fin()))
				{
					lista.agregarElementoPrincipio(temp);
					temp=nodo.intermedio();
					break;
				}
			}
			if(i==camino.size()) 
			{
				return null;
			}
		}
		return lista;
		
	}

}
