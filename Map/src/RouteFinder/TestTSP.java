package RouteFinder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import DistanceMatrixUtils.URLGenerator;

public class TestTSP {

	@Test
	public void testEqualDistances() {
		
		int[][]distances = new int[10][10];
		for(int i=0; i<10; i++)
		{
			for(int k=0; k<10; k++)
			{
				distances[i][k] =10;
			}
		}
		
		TSP map = new TSP(distances);
		ListNode n = map.solve();
		assertEquals(100, n.weight);
	}
	
	@Test
	public void testDistancesWithCities()
	{
		ArrayList<String> origins = new ArrayList<String>();
		origins.add("Los Angeles");
		origins.add("San Francisco");
		origins.add("Vancouver");
		origins.add("New York City, NY");
		origins.add("Pittsburgh, PA");
		origins.add("Dallas, TX");
		origins.add("Miami, Fl");
		URLGenerator urlGenerator;
		
	}
	

}
