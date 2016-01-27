package zdAPI;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserManyUpdate {

	public static ArrayList<JobStatus> main(String[] args, ArrayList<User> users) throws IOException {
		//args0 - username
		//args1 - token
		
		// break into clumps of 100 and process separately
		//ZD will only accept up to 100 items per PUT/POST
		
		String uriTail = "users/update_many.json";
		ArrayList<JobStatus> statuses = new ArrayList<JobStatus>();
		int clump = (int) Math.ceil(users.size()/100.0);
		for(int i=1;i<=clump;i++){
			Gson gson = new Gson();
			StringBuilder payload = new StringBuilder("{\"users\":[");
			String delimiter = "";
			for (int x = clump*100-100; x<(Math.min(clump*100, users.size()));x++){
				payload.append(delimiter);
				payload.append(gson.toJson(users.get(x)));
				delimiter = ",";
			}
			payload.append("]}");
			String[] params = {uriTail,args[0],args[1]};
			String result = PutZendeskData.main(params, payload.toString());
			if(result.startsWith("4"))
				throw new RuntimeException("Users not created. Result: " + result); 
			JsonObject resObj = (JsonObject) new JsonParser().parse(result);
			JsonElement jobStatusElement = resObj.get("job_status");
			Gson gson2 = new GsonBuilder()
				.registerTypeAdapter(JobStatus.class, new JobStatusDeserializer())
				.create();
			JobStatus newStat = gson2.fromJson(jobStatusElement, JobStatus.class); 
			statuses.add(newStat);
		}
		return statuses;
	}
}
