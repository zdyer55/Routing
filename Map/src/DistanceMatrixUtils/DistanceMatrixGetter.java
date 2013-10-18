package DistanceMatrixUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class DistanceMatrixGetter {
	public DistanceMatrix getDistanceMatrix(ArrayList<String> origins, ArrayList<String> destinations) throws MalformedURLException, IOException
	{
		return getDistanceMatrix(origins, destinations, TMode.DRIVING);
	}
	public DistanceMatrix getDistanceMatrix(ArrayList<String> origins, ArrayList<String> destinations, TMode mode) throws MalformedURLException, IOException
	{
		URLGenerator urlGenerator = new URLGenerator(origins, destinations, false, mode);
		String urlstring = urlGenerator.getURLString();
		JsonFetcher fetcher = new JsonFetcher();
		String json =fetcher.fetchJson(urlstring);
		DistanceMatrix d = JsonParser.parseJsonFromString(json);
		return d;
	}
}
