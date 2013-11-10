package DistanceMatrixUtils;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class URLGeneratorTest 
{
	URLGenerator ugen;
	@Before
	public void setUp() throws Exception 
	{
			ArrayList<String> origins = new ArrayList<String>();
			ArrayList<String> destinations = new ArrayList<String>();
			origins.add("Vancouver BC");
			origins.add("Seattle");
			destinations.add("San Francisco");
			destinations.add("Victoria BC");
			
			ugen = new URLGenerator(origins, destinations, false, TMode.BICYCLING);

	}

	@After
	public void tearDown() throws Exception {
		ugen = null;
	}

	@Test
	public void testGetURL() {
		assertEquals("http://maps.googleapis.com/maps/api/" +
				"distancematrix/json?origins=Vancouver+BC|Seattle&" +
				"destinations=San+Francisco|Victoria+BC&mode=bicy" +
				"cling&sensor=false", ugen.getURLString());
	}

	@Test
	public void testAddOrigins() {
		assertEquals("origins=Vancouver+BC|Seattle&", ugen.addOrigins());
	}

	@Test
	public void testAddDestinations() {
		assertEquals("destinations=San+Francisco|Victoria+BC&", ugen.addDestinations());
	}

	@Test
	public void testAddMode() {
		assertEquals("mode=bicycling&", ugen.addMode());
	}

	@Test
	public void testAddSensor() {
		assertEquals("sensor=false", ugen.addSensor());
	}

}
