package zdAPI;

import java.io.IOException;

public class UserDelete {

	public static int main(String[] args) throws IOException {
		//args0 - user id
		//args1 - username
		//args2 - token
		String uriTail = "users/" + args[0] + ".json";
		String[] params = {uriTail, args[1], args[2]};
		return DeleteZendeskData.main(params);
	}
}
