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

public class JsonFetcher 
{

	private JsonFetcher(){
		
	}
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

}
