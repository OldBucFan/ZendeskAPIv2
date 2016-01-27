package zdAPI;

import java.util.ArrayList;

public class UserUpdateOrCreate {

	public static String main(String[] args, User user) throws Exception {
		//args0 - username
		//args1 - token
		
		//Uses external ID as authoritative, then email. Updates if found regardless of content.
		String[] params = {user.getemail(), args[0], args[1]};
		String[] params2 = {args[0],args[1]};
		User newUser = null;
		String result = null;
		
		// first see if we can get by External ID, then by email
		String[] params3 = {user.getexternal_id(),args[0],args[1]};
		ArrayList<User> extUsers = UserGetByExternalId.main(params3);
		if(extUsers.isEmpty()){ //nope
			ArrayList<User> retUsers = UsersGetByEmail.main(params);
			if(retUsers.isEmpty()){
				newUser = UserCreate.main(params2, user);
				result = "created";
			} else {
				user.setid(retUsers.get(0).getid());
				newUser = UserUpdate.main(params2, user);
				result = "updated";
			}
		} else {
			user.setid(extUsers.get(0).getid());
			newUser = UserUpdate.main(params2, user);
			result = "updated";
		}
		return Long.toString(newUser.getid()) + ":" + result;
	}
}
