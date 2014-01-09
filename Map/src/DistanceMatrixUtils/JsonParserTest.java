package DistanceMatrixUtils;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class JsonParserTest {

	@Test
	public void testParseJson() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		JsonParser parse = new JsonParser();
		DistanceMatrix d = parse.parseJsonFromFile("ex.json");
		assertNotNull(d);
		assertEquals(2,d.destination_addresses.length);
		assertEquals(2,d.origin_addresses.length);
		assertEquals(2,d.rows.get(0).elements.size());
		
		Element e1 = d.rows.get(0).elements.get(0);
		Element e2 = d.rows.get(0).elements.get(1);
		Element e3 = d.rows.get(1).elements.get(0);
		Element e4 = d.rows.get(1).elements.get(1);
		
		assertEquals("1,526 km",e1.distance.text);
		assertEquals(1526072,e1.distance.value);
		assertEquals("14 hours 49 mins",e1.duration.text);
		assertEquals(53359,e1.duration.value);
		
		assertEquals("112 km",e2.distance.text);
		assertEquals(111895,e2.distance.value);
		assertEquals("3 hours 1 min",e2.duration.text);
		assertEquals(10831,e2.duration.value);
		
		
		assertEquals("1,300 km",e3.distance.text);
		assertEquals(1299954,e3.distance.value);
		assertEquals("12 hours 29 mins",e3.duration.text);
		assertEquals(44915,e3.duration.value);
		
		assertEquals("172 km",e4.distance.text);
		assertEquals(172210,e4.distance.value);
		assertEquals("4 hours 25 mins",e4.duration.text);
		assertEquals(15874,e4.duration.value);
		
		
	}

}
