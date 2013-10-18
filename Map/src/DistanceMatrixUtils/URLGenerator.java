package DistanceMatrixUtils;
import java.util.ArrayList;


public class URLGenerator {

	ArrayList<String> origins;
	ArrayList<String> destinations;
	boolean sensor;
	TMode m;
	
	public URLGenerator(ArrayList<String> origins,
			ArrayList<String> destinations, boolean sensor, TMode m) {
		super();
		this.origins = origins;
		this.destinations = destinations;
		this.sensor = sensor;
		this.m = m;
	}

	public ArrayList<String> getOrigins() {
		return origins;
	}

	public void setOrigins(ArrayList<String> origins) {
		this.origins = origins;
	}

	public ArrayList<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<String> destinations) {
		this.destinations = destinations;
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

	public TMode getMode() {
		return m;
	}

	public void setMode(TMode m) {
		this.m = m;
	}

	public String getURLString()
	{
		String base = "http://maps.googleapis.com/maps/api/distancematrix/json?";
		base += addOrigins();
		base += addDestinations();
		base += addMode();
		base += addSensor();
		return base;
	}
	
	public String addOrigins()
	{
		
		String s = "origins=";
		int size = origins.size();
		for(String o: origins)
		{
			o =o.replaceAll("\\s", "+");
			System.out.println(o);
			s+=o;
			if(--size!=0)
				s+="|";
		}
		s+="&";
		return s;
	}
	
	public String addDestinations()
	{
		String s = "destinations=";
		int size = destinations.size();
		for(String o: destinations)
		{
			o =o.replaceAll("\\s", "+");
			System.out.println(o);
			s+=o;
			if(--size!=0)
				s+="|";
		}
		s+="&";
		return s;
	}
	public String addMode()
	{
		String s = "mode=";
		if (m == TMode.BICYCLING)
			s += "bicycling&";
		else if(m==TMode.DRIVING)
			s += "driving&";
		else
			s += "walking&";
		return s;
	}
	public String addSensor()
	{
		
		return "sensor=" + sensor;
	}
	

}
