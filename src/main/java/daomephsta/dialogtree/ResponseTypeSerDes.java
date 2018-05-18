package daomephsta.dialogtree;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class ResponseTypeSerDes
{
	private static final Map<String, JsonDeserializer<? extends IResponse>> ID_TO_DESERIALISER = new HashMap<>();
	private static final Map<Class<? extends IResponse>, JsonSerializer<? extends IResponse>> CLASS_TO_SERIALISER = new HashMap<>();
	
	public static <T extends IResponse> void register(Class<T> responseClass, String identifier,  JsonSerDes<T> serdes)
	{
		ID_TO_DESERIALISER.put(identifier, serdes);
		CLASS_TO_SERIALISER.put(responseClass, serdes);
	}
	
	public static interface JsonSerDes<T> extends JsonSerializer<T>, JsonDeserializer<T> {}
}
