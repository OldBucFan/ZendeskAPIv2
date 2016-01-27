package zdAPI;

import java.io.IOException;

public class OrgDelete {

	public static int main(String[] args) throws IOException {
		//args0 - org id
		//args1 - username
		//args2 - token
		String uriTail = "organizations/" +args[0] + ".json";
		String[] params = {uriTail, args[1], args[2]};
		return DeleteZendeskData.main(params);
	}
}
