package zdAPI;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrgGetByExternalId {

	public static List<Organization> main(String[] args) throws Exception {
		//args0 - External ID
		//args1 - username
		//args2 - token
		
		String uriTail = "organizations/search.json?external_id=" + args[0];
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
