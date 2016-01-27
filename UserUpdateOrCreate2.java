package zdAPI;

import java.util.ArrayList;

public class UserUpdateOrCreate2 {

	public static String main(String[] args, User user) throws Exception {
		//args0 - username
		//args1 - token
		
		//does not update or create, but marks for action. Used with updateMany and CreateMany 
		String result = "unchanged";
		
		// first see if we can get by External ID, then by email
		String[] params2 = {user.getexternal_id(),args[0],args[1]};
		ArrayList<User> extUsers = UserGetByExternalId.main(params2);
		if(extUsers.isEmpty()){ //nope
			String[] params = {user.getemail(), args[0], args[1]};
			ArrayList<User> retUsers = UsersGetByEmail.main(params);
			if(retUsers.isEmpty()){ //only create active
				if (user.getuser_fields().get("active").toString().equalsIgnoreCase("true")) result = "create";
			} else {
				user.setid(retUsers.get(0).getid());
				if (!UserCompare.compare(user,retUsers.get(0))){
					result = "update";
				}
			}
		} else {
			user.setid(extUsers.get(0).getid());
			if (!UserCompare.compare(user,extUsers.get(0)))	result = "update";
		}
		return result;
	}
}
