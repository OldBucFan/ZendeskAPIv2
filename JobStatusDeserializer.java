package zdAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JobStatusDeserializer implements JsonDeserializer<JobStatus>{

	@Override
	public JobStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		String id = null;
		String url = null;
		int total = 0;
		int progress = 0;
		String status = null;
		String message = null;
		ArrayList<Result> results = new ArrayList<Result>();
		
		for(Entry<String, JsonElement> entry : obj.entrySet()){
			String key = entry.getKey().toString();
			switch (key){
			case "id":
				id = entry.getValue().getAsString();
				break;
			case "url":
				url = entry.getValue().getAsString();
				break;
			case "total":
				total = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsInt() : 0; //null; 
				break;
			case "progress":
				progress = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsInt() : 0; //null;
				break;
			case "status":
				status = entry.getValue().getAsString();
				break;
			case "message":
				message = entry.getValue().getClass() != JsonNull.class ? entry.getValue().getAsString() : "";
				break;
			case "results":
				if (entry.getValue().getClass()!= JsonNull.class){
					JsonArray resultArray = entry.getValue().getAsJsonArray();
					for(JsonElement resultEntry : resultArray){
						Gson gson = new GsonBuilder()
							.registerTypeAdapter(Result.class, new ResultDeserializer())
							.create();
						results.add(gson.fromJson(resultEntry, Result.class));
					}
				} else results = null;
			}
		}
		Result[] resultArr = null;
		if (results != null) resultArr = results.toArray(new Result[results.size()]);
		JobStatus js = new JobStatus(id,url,total,progress,status,message,resultArr);
		return js;
	}
}
