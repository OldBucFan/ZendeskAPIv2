package zdAPI;

import java.util.List;

public class OrgUpdateOrCreate {

	public static String main(String[] args) throws Exception {
		//args0 - org name
		//args1 - org external ID
		//args2 - username
		//args3 - token

		String[] zdOrgParams = {args[1],args[2], args[3]};
		String[] zdOrgParams2 = {args[0],args[2], args[3]};
		String[] zdParams = {args[2],args[3]};
		List<Organization> lookupOrgs = OrgGetByExternalId.main(zdOrgParams);
		if (lookupOrgs.isEmpty()) lookupOrgs = OrgsGetByName.main(zdOrgParams2); //if no id try matching name
		Organization zdOrg = null;
		Organization returnOrg = null;
		String status = null;
		for(Organization org : lookupOrgs){
			if(org.getexternal_id()!=null && org.getexternal_id().equalsIgnoreCase(args[1])) {
				zdOrg = org;
				zdOrg.setname(args[0]);
				//System.out.println("id match.");
				break;
			}
			if(org.getname().equalsIgnoreCase(args[0])){
				zdOrg = org;
				zdOrg.setexternal_id(args[1]);
				//System.out.println("name match.");
				break;
			}
		}
		if (zdOrg != null){ //org exists - update it	
			returnOrg = OrgUpdate.main(zdParams, zdOrg);
			status = "updated";
		} else {   //org doesn't exist - create it
			zdOrg = new Organization();
			zdOrg.setname(args[0]);
			zdOrg.setexternal_id(args[1]);
			returnOrg = OrgCreate.main(zdParams, zdOrg);
			status = "created";
		}
		return Integer.toString(returnOrg.getid()) + ":" + status;
	}

}
