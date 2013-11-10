package DistanceMatrixUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class DistanceMatrixGetter {
	public DistanceMatrix getDistanceMatrix(List<String> origins, List<String> destinations) 
	{
		return getDistanceMatrix(origins, destinations, TMode.DRIVING);
	}
	public DistanceMatrix getDistanceMatrix(List<String> origins, List<String> destinations, TMode mode) 
	{
		URLGenerator urlGenerator = new URLGenerator(origins, destinations, false, mode);
		String urlstring = urlGenerator.getURLString();
		JsonFetcher fetcher = new JsonFetcher();
		String json =null;
		try {
			json = fetcher.fetchJson(urlstring);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DistanceMatrix d = JsonParser.parseJsonFromString(json);
		return d;
	}
}
