package zdAPI;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrgCreate {

	public static Organization main(String[] args, Organization org) throws IOException {
		//args0 - username
		//args1 - token
		String uriTail = "organizations.json";
		Gson gson = new Gson();
		String payload = "{\"organization\":" + gson.toJson(org) + "}";
		String[] params = {uriTail,args[0], args[1]};
		String result = PostZendeskData.main(params, payload);
		JsonObject resObj = (JsonObject) new JsonParser().parse(result);
		JsonElement orgElement = resObj.get("organization");
		Gson gson2 = new GsonBuilder()
			.registerTypeAdapter(Organization.class, new OrganizationDeserializer())
			.create();
		return gson2.fromJson(orgElement,  Organization.class);
	}
}
