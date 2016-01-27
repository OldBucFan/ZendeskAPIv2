package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

public class ResultDeserializer implements JsonDeserializer<Result>{
	
	@Override
	public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		int id = 0;
		int index = 0;
		String error = null;
		List<String> details = new ArrayList<String>();
		
		for(Entry<String, JsonElement> entry : obj.entrySet()){
			String key = entry.getKey().toString();
			switch (key){
				case "id":
					id = entry.getValue().getAsInt();
					break;
				case "index":
					index = entry.getValue().getAsInt();
					break;
				case "error":
					error = entry.getValue().getAsString();
					break;
				case "details":
					JsonArray detailJson = entry.getValue().getAsJsonArray();
					for (JsonElement detailElement : detailJson){
						details.add(detailElement.getAsString());
					}
					break;
				default:
			}
		}
		String[] detailArr = null;
		if (details != null) detailArr = (String[]) details.toArray(new String[details.size()]);
		Result result = new Result(id,index,error,detailArr);
		return result;
	}
}
