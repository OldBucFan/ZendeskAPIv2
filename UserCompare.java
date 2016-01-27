package zdAPI;

public class UserCompare {
	
	public static boolean compare (User tempUser, User zdUser){
		if (tempUser.getexternal_id().equalsIgnoreCase(zdUser.getexternal_id())){
			if(tempUser.getemail().equalsIgnoreCase(zdUser.getemail())){
				if(tempUser.getname().equalsIgnoreCase(zdUser.getname())){
					if(tempUser.getorganization_id()==zdUser.getorganization_id()){
						//custom fields
						String tprod = (String) tempUser.getuser_fields().get("product");
						String zprod = (String) zdUser.getuser_fields().get("product");
						if (tprod.equalsIgnoreCase(zprod)){
							Object tactive = tempUser.getuser_fields().get("active");
							Object zactive = zdUser.getuser_fields().get("active");
							if (tactive.toString().equalsIgnoreCase(zactive.toString())) return true;
						}
					}
				}
			}
		}
		return false;
	}

}
