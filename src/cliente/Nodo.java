package cliente;

public class Nodo {

	private double longitude;
	
	private double latitude;
	
	private String name;
	
	public Nodo(String n, double lat, double lon)
	{
		name=n;
		latitude=lat;
		longitude=lon;
	}
	
	public String getNombre()
	{
		return name;
	}
	
	public double getLatitiude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public void setName(String n){name=n;}
	
	public void setLongitude(double l){longitude=l;}
	
	public void setLatitude(double l){latitude=l;}
	
	
}
