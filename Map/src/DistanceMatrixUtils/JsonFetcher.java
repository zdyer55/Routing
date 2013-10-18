package DistanceMatrixUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public class JsonFetcher 
{

	public static boolean fetchJson(String urlstring, String filename) throws IOException
	{
		URL url = new URL(urlstring);
		ReadableByteChannel inputChannel = null;
		FileChannel outputChannel = null;
		FileOutputStream fos =null;
		try {
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    InputStream urlStream = connection.getInputStream();
		    File outputFile = new File(filename);
		    inputChannel = Channels.newChannel(urlStream);
		    fos= new FileOutputStream(outputFile);
		    outputChannel = fos.getChannel();
		    long numread =0;
		    int pos =0;
		    do
		    {
		    	
		    	numread =outputChannel.transferFrom(inputChannel, pos, 8192);
		    	pos +=numread;
		    }while(numread==8192);
		    fos.close();
		    urlStream.close();
		    connection.disconnect();
		    
		} 
		catch (IOException ioe) {
		  ioe.printStackTrace();
		  return false;
		} 
		finally 
		{
		    //check for nulls and stuff before you do this
			try
			{
			    inputChannel.close();
			    outputChannel.close();
			    fos.close();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static String fetchJson(String urlString) throws MalformedURLException, IOException
	{
		String jsonText=null;
		InputStream is = new URL(urlString).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      jsonText = readAll(rd);
		    } finally {
		      is.close();
		    }
		return jsonText;
	}

	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

}
