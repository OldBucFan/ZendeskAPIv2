package zdAPI;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsersGetByOrgId {
	
	public static ArrayList<User> get(String[] args) throws Exception{
		//args0 - username
		//args1 - token
		//args2 - org ID
		String uriTail = "organizations/"+args[2]+"/users.json";
		String[] params = {uriTail,args[0],args[1]};
		String raw = GetZendeskData.main(params);
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(UserQueryResponse.class, new UserQueryResponseDeserializer())
			.create();
		UserQueryResponse uqr = gson.fromJson(raw, UserQueryResponse.class);
		return uqr.getusers();
	}
}
