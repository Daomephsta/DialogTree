package daomephsta.dialogtree;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DialogTreeParse
{
	private static final Gson SERIALISER = new GsonBuilder().create();
	
	public static void main(String[] args)
	{
		try(Writer w = new FileWriter(new File("test.json")))
		{
			w.write(SERIALISER.toJson(new DialogNode("Here come dat boi", null, true)));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
