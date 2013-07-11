package DistanceMatrixUtils;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


public class JsonFetcherTest {

	@Test
	public void testFetchJson() throws IOException {
		JsonFetcher.fetchJson("http://maps.googleapis.com/maps/api/" +
				"distancematrix/json?origins=Vancouver+BC|Seattle&" +
				"destinations=San+Francisco|Victoria+BC&mode=bicy" +
				"cling&sensor=false", "testjson1.json");
		File f1 = new File("testjson1.json");
		File f2 = new File("ex.json");

		assertEquals(f2.length(),f1.length());	
	}

}
