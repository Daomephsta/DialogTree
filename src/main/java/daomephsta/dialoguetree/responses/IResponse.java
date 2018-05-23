package daomephsta.dialoguetree.responses;

import com.google.gson.*;

public interface IResponse
{
	public void respond(ResponseContextBase context);
	
	public static abstract class SerDes<T extends IResponse>
	{
		public abstract JsonObject serialise(IResponse response, JsonSerializationContext context);

		public abstract T deserialise(JsonObject json, JsonDeserializationContext context);
		
		abstract String getIdentifier();
	}
}
