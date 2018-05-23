package daomephsta.dialoguetree;

import java.lang.reflect.Type;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;

public class DialogueTree
{
	private final ImmutableList<DialogueNode> roots;

	public DialogueTree(DialogueNode[] roots)
	{
		this.roots = ImmutableList.copyOf(roots);
	}
	
	public ImmutableList<DialogueNode> getRoots()
	{
		return roots;
	}
	
	@Override
	public String toString()
	{
		return roots.toString();
	}
	
	public static class SerDes implements JsonSerializer<DialogueTree>, JsonDeserializer<DialogueTree>
	{
		@Override
		public DialogueTree deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
		{
			if(!json.isJsonObject()) throw new JsonSyntaxException("Expected JSON object not " + json.getClass());
			JsonObject jsonObj = (JsonObject) json;
			DialogueNode[] roots = context.deserialize(jsonObj.get("roots"), DialogueNode[].class);
			return new DialogueTree(roots);
		}

		@Override
		public JsonElement serialize(DialogueTree tree, Type type, JsonSerializationContext context)
		{
			JsonObject jsonObj = new JsonObject();
			jsonObj.add("roots", context.serialize(tree.roots));
			return jsonObj;
		}
	}
}
