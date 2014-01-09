package RouteFinder;


import java.util.List;
/*
 * Represents a hamiltian path with a list of cities in order
 * to be visited
 * Can be used to return an object result of TSP
 */
public class HamPath {
	private List<String> cities;
	public HamPath(List<String> cities)
	{
		this.cities = cities;
	}
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
}
