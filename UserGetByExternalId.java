package zdAPI;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserGetByExternalId {

	public static ArrayList<User> main(String[] args) throws Exception {
		//args0 - external ID
		//args1 - username
		//args2 - token
		
		String uriTail = "users/search.json?external_id=" + args[0];
		String[] params = {uriTail, args[1], args[2]};
		String raw = GetZendeskData.main(params);
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(UserQueryResponse.class, new UserQueryResponseDeserializer())
			.create();
		UserQueryResponse uqr = gson.fromJson(raw, UserQueryResponse.class);
		return uqr.getusers();
	}
}
