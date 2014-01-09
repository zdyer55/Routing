package DistanceMatrixUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.*;
public class JsonParser {
	
	public DistanceMatrix parseJsonFromFile(String filename) throws JsonSyntaxException, JsonIOException, FileNotFoundException
	{
		Gson gson = new Gson();
		DistanceMatrix mat = gson.fromJson(new FileReader(filename), DistanceMatrix.class);
		return mat;
	}
	public DistanceMatrix parseJsonFromString(String json)
	{
		Gson gson = new Gson();
		DistanceMatrix mat = gson.fromJson(json, DistanceMatrix.class);
		return mat;
	}




		
		

	
}

