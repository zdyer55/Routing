package DistanceMatrixUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		ArrayList<String> origins = new ArrayList<String>();
		ArrayList<String> destinations = new ArrayList<String>();
		origins.add("Vancouver BC");
		origins.add("Seattle");
		destinations.add("San Francisco");
		destinations.add("Victoria BC");
		DistanceMatrixGetter g = new DistanceMatrixGetter();
		DistanceMatrix dmatrix = g.getDistanceMatrix(origins, destinations);
		System.out.println(dmatrix);
	}

}
