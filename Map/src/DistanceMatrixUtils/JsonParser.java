package DistanceMatrixUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.*;
public class JsonParser {
	String filename;
	DistanceMatrix mat;
	public JsonParser(String filename) {
		this.filename = filename;
	}

	public DistanceMatrix parseJson() throws JsonSyntaxException, JsonIOException, FileNotFoundException
	{
		Gson gson = new Gson();
		mat = gson.fromJson(new FileReader(filename), DistanceMatrix.class);
		return mat;
	}




		
		

	
}

