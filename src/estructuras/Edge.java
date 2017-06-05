package estructuras;


public class Edge<K> implements Comparable<Edge<K>> {

	protected K origin;
	protected K end;
	protected double[] weight;
	
	public Edge(K v, K w, double [] weight)
	{
		origin=v;
		end=w;
		this.weight=weight;
	}
	
	
	public K getOrigin(){ return origin;}
	
	public K getEnd(){return end;}
	
	public double getWeight(){
		
		double suma=0;
		for(int i=0;i<weight.length;i++)
		{
			suma+=weight[i];
		}
		return suma;
	}
	


	public void setWeight(double [] peso) {
		weight=peso;
		
	}
	
	public void setOrigin(K k){origin=k;}
	
	public void setEnd(K k){end=k;}
	
	public int compareTo(Edge<K> o) {
		Double w1=getWeight();
		Double w2=o.getWeight();
		return w1.compareTo(w2);
	}
}
