package daomephsta.dialoguetree;

import java.lang.reflect.Type;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;

import daomephsta.dialoguetree.responses.IResponse;

public class DialogueNode
{
	private final String text;
	private final ImmutableList<DialogueNode> children;
	private final IResponse response;
	
	public DialogueNode(String text, DialogueNode[] children, IResponse response)
	{
		this.text = text;
		this.children = ImmutableList.copyOf(children);
		this.response = response;
	}
	
	public String getText()
	{
		return text;
	}
	
	public ImmutableList<DialogueNode> getChildren()
	{
		return children;
	}
	
	public IResponse getResponse()
	{
		return response;
	}
	
	@Override
	public String toString()
	{
		return String.format("(Text: %s, Response: %s, isTerminal: %s)", text, response, children.isEmpty());
	}
	
	public static class SerDes implements JsonSerializer<DialogueNode>, JsonDeserializer<DialogueNode>
	{
		@Override
		public DialogueNode deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
		{
			if(!json.isJsonObject()) throw new JsonSyntaxException("Expected JSON object not " + json.getClass());
			JsonObject jsonObj = (JsonObject) json;
			String text = jsonObj.get("text").getAsString();
			IResponse response = context.deserialize(jsonObj.get("response"), IResponse.class);
			DialogueNode[] children = context.deserialize(jsonObj.get("children"), DialogueNode[].class);
			return new DialogueNode(text, children, response);
		}

		@Override
		public JsonElement serialize(DialogueNode node, Type type, JsonSerializationContext context)
		{
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("text", node.text);
			jsonObj.add("response", context.serialize(node.response));
			jsonObj.add("children", context.serialize(node.children));
			return jsonObj;
		}
	}
}
