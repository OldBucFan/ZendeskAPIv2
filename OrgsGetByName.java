package zdAPI;

import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrgsGetByName {

	public static List<Organization> main(String[] args) throws Exception {
		// args0 - Org name 
		// args1 - ZD username
		// args2 - ZD token
		String uriTail = "organizations/autocomplete.json?name=" + URLEncoder.encode(args[0],"UTF-8");
		String[] params = {uriTail,args[1],args[2]};
		String raw = GetZendeskData.main(params);
		if(raw.charAt(0) == '{'){
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(OrgQueryResponse.class, new OrgQueryResponseDeserializer())
				.create();
			OrgQueryResponse oqr = gson.fromJson(raw, OrgQueryResponse.class);
			return oqr.getorganizations();
		} else {
			throw new RuntimeException("Returned content not Json: " + raw.substring(0, 20));
		}
	}

}
