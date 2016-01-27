package zdAPI;

import java.util.Map;
import java.util.Map.Entry;


public class User {

	private long id;
	private String url;
	private String name;
	private String email;
	private String created_at;
	private String updated_at;
	private String time_zone;
	private String phone;
	private int locale_id;
	private String locale;
	private long organization_id;
	private String[] tags;
	private String role;
	private String external_id;
	private Map<String, Object> user_fields;
	private boolean verified;
 
	public User() {
		
	}
	public User(long id, String url, String name, String email, String created_at, String updated_at, String time_zone, 
			String phone, int locale_id, String locale, long organization_id, String[] tags, String role, String external_id, Map<String, Object> user_fields, boolean verified){
		this.id = id;
		this.url = url;
		this.name = name;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.time_zone = time_zone;
		this.phone = phone;
		this.locale_id = locale_id;
		this.locale = locale;
		this.organization_id = organization_id;
		this.tags = tags;
		this.role = role;
		this.external_id = external_id;
		this.user_fields = user_fields;
		this.verified = verified;
	}
	
	public long getid(){
		return this.id;
	}
	public String geturl(){
		return this.url;
	}
	public String getname(){
		return this.name;
	}
	public String getemail(){
		return this.email;
	}
	public String getcreated_at(){
		return this.created_at;
	}
	public String getupdated_at(){
		return this.updated_at;
	}
	public String gettime_zone(){
		return this.time_zone;
	}
	public String getphone(){
		return this.phone;
	}
	public int getlocale_id(){
		return this.locale_id;
	}
	public String getlocale(){
		return this.locale;
	}
	public long getorganization_id(){
		return this.organization_id;
	}
	public String[] getTags(){
		return this.tags;
	}
	public String getrole(){
		return this.role;
	}
	public String getexternal_id(){
		return this.external_id;
	}
	public Map<String, Object> getuser_fields(){
		return this.user_fields;
	}
	public boolean getverified(){
		return this.verified;
	}
	
	public void setid(long id){
		this.id = id;
	}
	public void seturl(String url){
		this.url = url;
	}
	public void setname(String name){
		this.name = name;
	}
	public void setemail(String email){
		this.email = email;
	}
	public void setcreated_at(String created_at){
		this.created_at = created_at;
	}
	public void setupdated_at(String updated_at){
		this.updated_at = updated_at;
	}
	public void settime_zone(String time_zone){
		this.time_zone = time_zone;
	}
	public void setphone(String phone){
		this.phone = phone;
	}
	public void setlocale_id(int locale_id){
		this.locale_id = locale_id;
	}
	public void setlocale(String locale){
		this.locale = locale;
	}
	public void setorganization_id(long organization_id){
		this.organization_id = organization_id;
	}
	public void setTags(String[] tags){
		this.tags = tags;
	}
	public void setrole(String role){
		this.role = role;
	}
	public void setexternal_id(String external_id){
		this.external_id = external_id;
	}
	public void setuser_fields(Map<String, Object> user_fields){
		this.user_fields = user_fields;
	}
	public void setverified( boolean verified){
		this.verified = verified;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Name: " + this.name + "\r\n");
		sb.append("ID: " + this.getid() + "\r\n");
		sb.append("Email: " + this.getemail() + "\r\n");
		sb.append("Phone: " + this.getphone() + "\r\n");
		sb.append("External ID: " + this.getexternal_id() + "\r\n");
		sb.append("User Fields:");
		if (this.user_fields == null) {
			System.out.println("No user fields");
		} else {
			for (Entry<String, Object> field : this.getuser_fields().entrySet()){
				sb.append("\r\n\t" + field.getKey() + ": " + (field.getValue() != null ? field.getValue().toString() : "null"));
			}
		}
		sb.append("\r\nVerified: " + this.verified);
		return sb.toString();
	}
}
