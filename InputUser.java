package zdAPI;

import java.util.Map;

public class InputUser {

	private String name;
	private String email;
	private String time_zone;
	private String phone;
	private int locale_id;
	private String locale;
	private long organization_id;
	private String external_id;
	private Map<String, Object> user_fields;
	
	public InputUser() {
		// For ZD Updates and creates. Like user but leaves out fields that cannot be updated.
		
	}
	public InputUser(String name, String email, String time_zone, 
			String phone, int locale_id, String locale, long organization_id, String external_id, Map<String,Object> user_fields){
		this.name = name;
		this.email = email;
		this.time_zone = time_zone;
		this.phone = phone;
		this.locale_id = locale_id;
		this.locale = locale;
		this.organization_id = organization_id;
		this.external_id = external_id;
		this.user_fields = user_fields;
	}
	
	public InputUser(User user){
		this.name = user.getname();
		this.email = user.getemail();
		this.time_zone = user.gettime_zone();
		this.phone = user.getphone();
		this.locale_id = user.getlocale_id();
		this.locale = user.getlocale();
		this.organization_id = user.getorganization_id();
		this.external_id = user.getexternal_id();
		this.user_fields = user.getuser_fields();
	}
	
	public String getname(){
		return this.name;
	}
	public String getemail(){
		return this.email;
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
	public String getexternal_id(){
		return this.external_id;
	}
	public Map<String, Object> getuser_fields(){
		return this.user_fields;
	}

	public void setname(String name){
		this.name = name;
	}
	public void setemail(String email){
		this.email = email;
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
	public void setexternal_id(String external_id){
		this.external_id = external_id;
	}
	public void setuser_fields(Map<String, Object> user_fields){
		this.user_fields = user_fields;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Name: " + this.name + "\r\n");
		sb.append("Email: " + this.email + "\r\n");
		sb.append("Phone: " + this.phone + "\r\n");
		sb.append("External ID: " + this.external_id + "\r\n");
		sb.append("User Fields: ");
		sb.append(this.user_fields == null ? "no user fields \r\n" : this.user_fields.toString());
		return sb.toString();
	}
}
