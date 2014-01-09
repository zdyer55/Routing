package RouteFinder;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import DistanceMatrixUtils.DistanceMatrix;
import DistanceMatrixUtils.DistanceMatrixGetter;
import DistanceMatrixUtils.SplitArrayMatrix;
/**
 * 
 * @author Zach Dyer
 * The response to the path of route/{cities}
 * cities should be a list of cities separated by '&'
 * returns a list of cities
 * path is made of first city as origin, the rest of cities in order, 
 * returning to the first city
 */
@Path("/route/{cities}")
public class RouteResource {
	
	@GET
	@Produces("text/plain")
	public String getRoute(@PathParam("cities") String cities)
	{
		String[] citiesArray = cities.split("&"); //cities in array form
		List<String> origins = Arrays.asList(citiesArray); //convert to List form
		DistanceMatrixGetter dmGetter = new DistanceMatrixGetter();
		DistanceMatrix dm = dmGetter.getDistanceMatrix(origins, origins); // Get distance matrix for these cities from google
		SplitArrayMatrix sam = new SplitArrayMatrix(dm); // converts distance matrix to more usable form
		TSP map = new TSP(sam.getDistances()); 
		ListNode n = map.solve(); //ListNode represents a city and the next city to visit
		String out ="";
		int[] nums = new int[citiesArray.length+1];
		/*
		while(n!null)
		{
			out += origins.get(n.num) + "\n"; //add city string corresponding to number
			n=n.next;
		}
		
		for(int i=0; i<nums.length; i++)
		{
			nums[i] = n.num;
			n =n.next;
		}
		*/
		Gson gson = new Gson();
		return gson.toJson(n);
		
	}

}