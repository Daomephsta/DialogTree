package daomephsta.dialoguetree.responses;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class ResponseDialogue implements IResponse
{
	private final String text;
	
	public ResponseDialogue(String text)
	{
		this.text = text;
	}

	@Override
	public void respond(ResponseContextBase context)
	{
		context.displayString(text);
	}
	
	public String getDialogue()
	{
		return text;
	}
	
	@Override
	public String toString()
	{
		return "(Text: " + text + ")";
	}
	
	static class Serialiser extends IResponse.SerDes<ResponseDialogue>
	{
		@Override
		public JsonObject serialise(IResponse response, JsonSerializationContext context)
		{
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("text", ((ResponseDialogue) response).text);
			return jsonObj;
		}

		@Override
		public ResponseDialogue deserialise(JsonObject json, JsonDeserializationContext context)
		{
			return new ResponseDialogue(json.get("text").getAsString());
		}

		@Override
		String getIdentifier()
		{
			return "dialogue";
		}	
	}
}
