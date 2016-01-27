package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class OrganizationDeserializer implements JsonDeserializer<Organization>{

	@Override
	public Organization deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		int id = 0;
		String url = null;
		String external_id = null;
		String name = null;
		String created_at = null;
		String updated_at = null;
		List<String> domain_names = new ArrayList<String>();
		String details = null;
		String notes = null;
		int group_id = 0;
		boolean shared_tickets = false;
		boolean shared_comments = false;
		List<String> tags = new ArrayList<String>();
		Map<String, Object> organization_fields = new HashMap<String, Object>();
		
		for(Entry<String, JsonElement> entry : obj.entrySet()){
			String key = entry.getKey().toString();
			switch (key){
			case "id":
				id = Integer.parseInt(entry.getValue().getAsString());
				break;
			case "url":
				url = entry.getValue().getAsString();
				break;
			case "external_id":
				external_id = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString() : null;
				break;
			case "name":
				name = entry.getValue().getAsString();
				break;
			case "created_at":
				created_at = entry.getValue().getAsString();
				break;
			case "updated_at":
				updated_at = entry.getValue().getAsString();
				break;
			case "domain_names":
				JsonArray domArr = (JsonArray) entry.getValue();
				for(JsonElement dom : domArr){
					domain_names.add(dom.getAsString());
				}
				break;
			case "details":
				details = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString() : null;
				break;
			case "notes":
				notes = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString() : null;
				break;
			case "group_id":
				if(entry.getValue().toString().equalsIgnoreCase("null")) 
					break;
				group_id = entry.getValue().getAsInt();
				break;
			case "shared_tickets":
				shared_tickets = entry.getValue().getAsBoolean();
				break;
			case "shared_comments":
				shared_comments = entry.getValue().getAsBoolean();
				break;
			case "tags":
				JsonArray tagArr = (JsonArray) entry.getValue();
				for(JsonElement tag : tagArr){
					tags.add(tag.getAsString());
				}
				break;
			case "organization_fields":
				JsonObject ofObj = (JsonObject) entry.getValue();
				for(Entry<String, JsonElement> ofEntry : ofObj.entrySet()){
					organization_fields.put(ofEntry.getKey().toString(), ofEntry.getValue());
				}
				break;
			default:
			}
		}
		return new Organization(id, url, external_id, name, created_at, updated_at, domain_names, 
				details, notes, group_id, shared_tickets, shared_comments, tags, organization_fields);
	}

}
