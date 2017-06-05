package estructuras;

public class NodoCamino<K> {
	
	private K idFinal;
	private K idIntermedio;
	private double weight;
	private int length;
	
	public NodoCamino(K fin, K intermedio, double w, int l)
	{
		idFinal=fin;
		idIntermedio=intermedio;
		weight=w;
		length=l;
	}
	
	public K fin() {return idFinal;}
	public K intermedio(){return idIntermedio;}
	public double weight(){return weight;}
	public int length(){return length;}
	
	public void increaseWeight(double w){weight+=w;}
	
	public void increaseLength(int l){length+=l;}

}
