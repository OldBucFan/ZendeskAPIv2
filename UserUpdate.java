package zdAPI;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserUpdate {

	public static User main(String[] args, User user) throws IOException {
		//args0 - username
		//args1 - token
		String uriTail = "users/" + user.getid() + ".json";
		Gson gson = new Gson();
		String payload = "{\"user\":" + gson.toJson(user) + "}";
		String[] params = {uriTail,args[0], args[1]};
		String result = PutZendeskData.main(params, payload);
		JsonObject resObj = (JsonObject) new JsonParser().parse(result);
		JsonElement userElement = resObj.get("user");
		Gson gson2 = new GsonBuilder()
			.registerTypeAdapter(User.class, new UserDeserializer())
			.create();
		return gson2.fromJson(userElement,  User.class);
	}
}
