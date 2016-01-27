package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

public class OrgQueryResponseDeserializer  implements JsonDeserializer<OrgQueryResponse>{

	@Override
	public OrgQueryResponse deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException{
		JsonObject obj = json.getAsJsonObject();
		List<Organization> organizations = new ArrayList<Organization>();
		String next_page = null;
		String previous_page = null;
		int count = 0;
		
		for (Object objJ : obj.entrySet()){
			@SuppressWarnings("unchecked")
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
			case "organizations":
				JsonArray arr = (JsonArray) entry.getValue();
				Gson gson = new GsonBuilder()
					.registerTypeAdapter(Organization.class, new OrganizationDeserializer())
					.create();
				Type collectionType = new TypeToken<Collection<Organization>>(){}.getType();
				Collection<Organization> orgc = gson.fromJson(arr, collectionType);
				organizations.addAll(orgc);
				break;
			default:
			}
		}
		return new OrgQueryResponse(organizations, count, next_page, previous_page);
	}

}
