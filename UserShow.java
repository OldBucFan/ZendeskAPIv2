package zdAPI;

import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserShow {

	public static User main(String[] args) throws Exception {
		//args0 - ZD User ID
		//args1 - zd username
		//args2 - zd token
		
		String uriTail = "users/" + args[0] + ".json";
		String[] params = {uriTail,args[1],args[2]};
		
		String raw = GetZendeskData.main(params);
		System.out.println("Raw return: \r\n" + raw);
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(raw);
		User user = null;
		for(Entry<String, JsonElement> entry : json.entrySet()){
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(User.class, new UserDeserializer())
				.create();
			user = gson.fromJson(entry.getValue(), User.class);
		}
		return user;
	}
}
