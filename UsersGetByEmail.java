package zdAPI;

import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsersGetByEmail {

	public static ArrayList<User> main(String[] args) throws Exception {
		// args0 - email address
		// args1 - ZD username
		// args2 - ZD token
		String uri_tail = "users/search.json?query=" + URLEncoder.encode(args[0],"UTF-8");
		String[] params = {uri_tail,args[1],args[2]};
		String raw = GetZendeskData.main(params);
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(UserQueryResponse.class, new UserQueryResponseDeserializer())
			.create();
		UserQueryResponse uqr = gson.fromJson(raw, UserQueryResponse.class);
		return uqr.getusers();
	}
}
