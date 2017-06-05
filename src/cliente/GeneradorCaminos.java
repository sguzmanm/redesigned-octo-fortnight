package cliente;

import algoritmos.Dijkstra;
import estructuras.WeightedEdgeDirectedGraph;

public class GeneradorCaminos {
	
	private WeightedEdgeDirectedGraph<String,Nodo> graph;
	
	public GeneradorCaminos(String ruta)
	{
		
	}
	
	public Iterable<String> ruta(String origen, String fin)
	{
		Dijkstra<String,Nodo> D= new Dijkstra<String,Nodo>(graph,origen);
		return D.pathTo(fin);
	}

}
