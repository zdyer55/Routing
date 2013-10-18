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
		origins.add("San Francisco");
		origins.add("Victoria BC");
		destinations.add("Vancouver BC");
		destinations.add("Seattle");
		destinations.add("San Francisco");
		destinations.add("Victoria BC");
		DistanceMatrixGetter g = new DistanceMatrixGetter();
		DistanceMatrix dmatrix = g.getDistanceMatrix(origins, destinations);
		SplitArrayMatrix sam = new SplitArrayMatrix(dmatrix);
		for(int i=0; i<sam.getDistances().length; i++)
		{
			for(int j=0; j<sam.getDistances().length; j++)
			{
				System.out.print((sam.getDistances())[i][j] + " ");
			}
			System.out.println();	
		}
			
	}

}
