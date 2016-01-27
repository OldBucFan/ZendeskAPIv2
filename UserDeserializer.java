package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserDeserializer implements JsonDeserializer<User>{

	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		long id = 0;
		String url = null;
		String name = null;
		String email = null;
		String created_at = null;
		String updated_at = null;
		String time_zone = null;
		String phone = null;
		int locale_id = 0;
		String locale = null;
		long organization_id = 0;
		String role = null;
		String external_id = null;
		Map<String, Object> user_fields =  new HashMap<String, Object>();
		String[] tags = null;
		boolean verified = false;
		
		for(Entry<String, JsonElement> entry : obj.entrySet()){
			String key = entry.getKey().toString();
			switch (key){
			case "id":
				id = entry.getValue().getAsLong();
				break;
			case "url":
				url = entry.getValue().getAsString();
				break;
			case "name":
				name = entry.getValue().getAsString();
				break;
			case "email":
				email = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "created_at":
				created_at = entry.getValue().getAsString();
				break;
			case "updated_at":
				updated_at = entry.getValue().getAsString();
				break;
			case "time_zone":
				time_zone = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "phone":
				phone = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "locale_id":
				locale_id = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsInt(): null;;
				break;
			case "locale":
				locale = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "organization_id":
				organization_id = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsLong(): 0;
				break;
			case "external_id":
				external_id = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "role":
				role = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString(): null;
				break;
			case "user_fields":
				JsonObject ufObj = (JsonObject) entry.getValue();
				for(Entry<String, JsonElement> ufEntry : ufObj.entrySet()){
					user_fields.put(ufEntry.getKey(), ufEntry.getValue().getClass() != JsonNull.class ? ufEntry.getValue().getAsString(): null);
				}
	        	break;
			case "tags":
				ArrayList<String> alTags = new ArrayList<String>();
				JsonArray tagArr = (JsonArray) entry.getValue();
				for (JsonElement tag : tagArr){
					alTags.add(tag.getAsString());
				}
				tags = alTags.toArray(new String[alTags.size()]);
				break;
			case "verified":
				verified = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsBoolean(): null;
	        default:
			}	
		}
		return new User(id, url, name, email, created_at, updated_at, time_zone, phone, locale_id, locale, organization_id, tags, role, external_id, user_fields, verified);
	}

}
