package daomephsta.dialoguetree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import daomephsta.dialoguetree.responses.IResponse;
import daomephsta.dialoguetree.responses.ResponseSerDes;

public class DialogueTreeParser
{
	private static final Gson SERIALISER = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(DialogueTree.class, new DialogueTree.SerDes())
			.registerTypeAdapter(DialogueNode.class, new DialogueNode.SerDes())
			.registerTypeHierarchyAdapter(IResponse.class, ResponseSerDes.INSTANCE).create();

	public static DialogueTree parse(File f) throws IOException
	{
		try (Reader r = new FileReader(f))
		{
			return SERIALISER.fromJson(r, DialogueTree.class);
		}
	}
}
