package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class UserQueryResponseDeserializer implements JsonDeserializer<UserQueryResponse>{

	@Override
	@SuppressWarnings("unchecked")
	public UserQueryResponse deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		ArrayList<User> users = new ArrayList<User>();
		String next_page = null;
		String previous_page = null;
		int count = 0;	
		for(Object objJ : obj.entrySet()){
			Entry<String, JsonElement> entry = (Entry<String, JsonElement>) objJ;
			String key = entry.getKey().toString();
			switch (key){
			case "next_page":
				next_page = entry.getValue().toString();
				break;
			case "previous_page":
				previous_page = entry.getValue().toString();
				break;
			case "count":
				count = entry.getValue().getAsInt();
				break;
			case "users":
				JsonArray arr = (JsonArray) entry.getValue();
				Gson gson = new GsonBuilder()
					.registerTypeAdapter(User.class, new UserDeserializer())
					.create();
				Type collectionType = new TypeToken<Collection<User>>(){}.getType();
				Collection<User> usersc = gson.fromJson(arr, collectionType);
				users.addAll(usersc);
				break;
			default:
			}
		}
		return new UserQueryResponse(users, next_page, previous_page, count);
	}
}
