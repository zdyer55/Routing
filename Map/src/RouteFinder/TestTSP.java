package RouteFinder;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTSP {

	@Test
	public void test() {
		
		int[][]distances = new int[20][20];
		for(int i=0; i<10; i++)
		{
			for(int k=0; k<10; k++)
			{
				distances[i][k] =10;
			}
		}
		
		TSP map = new TSP(distances);
		ListNode n = map.solve();
		assertEquals(90, n.weight);
	}
	
	

}
