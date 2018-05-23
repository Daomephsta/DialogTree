package daomephsta.dialoguetree.responses;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

import daomephsta.dialoguetree.responses.IResponse.SerDes;

public class ResponseSerDes implements JsonSerializer<IResponse>, JsonDeserializer<IResponse>
{
	public static final ResponseSerDes INSTANCE = new ResponseSerDes();
	private static final Map<String, IResponse.SerDes<?>> ID_TO_DESERIALISER = new HashMap<>();
	private static final Map<Class<? extends IResponse>, IResponse.SerDes<?>> CLASS_TO_SERIALISER = new HashMap<>();
	static
	{
		register(ResponseDialogue.class, new ResponseDialogue.Serialiser());
	}
	
	private ResponseSerDes() {}
	
	public static <T extends IResponse> void register(Class<T> responseClass, IResponse.SerDes<T> serdes)
	{
		ID_TO_DESERIALISER.put(serdes.getIdentifier(), serdes);
		CLASS_TO_SERIALISER.put(responseClass, serdes);
	}
	
	public JsonElement serialize(IResponse response, Type type, JsonSerializationContext context)
	{
		if(!CLASS_TO_SERIALISER.containsKey(response.getClass())) throw new IllegalArgumentException("No serialiser registered for " + response.getClass());
		SerDes<?> serialiser = CLASS_TO_SERIALISER.get(response.getClass());
		JsonObject jsonObj = serialiser.serialise(response, context);
		jsonObj.addProperty("type", serialiser.getIdentifier());
		return jsonObj;
	}

	@Override
	public IResponse deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		if(!json.isJsonObject()) throw new JsonSyntaxException("Expected JSON object not " + json.getClass());
		JsonObject jsonObj = json.getAsJsonObject();
		String identifier = jsonObj.get("type").getAsString();
		if(!ID_TO_DESERIALISER.containsKey(identifier)) throw new JsonSyntaxException("Unknown response type " + identifier);
		return ID_TO_DESERIALISER.get(identifier).deserialise(jsonObj, context);
	}
}
