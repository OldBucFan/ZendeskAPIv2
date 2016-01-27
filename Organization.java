package zdAPI;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Organization {

	private int id;
	private String url;
	private String external_id;
	private String name;
	private String created_at;
	private String updated_at;
	private List<String> domain_names;
	private String details;
	private String notes;
	private int group_id;
	private boolean shared_tickets;
	private boolean shared_comments;
	private List<String> tags;
	private Map<String, Object> organization_fields;

	public Organization() {
		// Auto-generated constructor stub
	}
	public Organization(int id, String url, String external_id, String name, String created_at, String updated_at, List<String> domain_names, String details, String notes, int group_id, boolean shared_tickets, boolean shared_comments, List<String> tags, Map<String, Object> organization_fields){
		this.id = id;
		this.url = url;
		this.external_id = external_id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.domain_names = domain_names;
		this.details = details;
		this.notes = notes;
		this.group_id = group_id;
		this.shared_tickets = shared_tickets;
		this.shared_comments = shared_comments;
		this.tags = tags;
		this.organization_fields = organization_fields;
	}
	
	public int getid(){
		return this.id;
	}
	public String geturl(){
		return this.url;
	}
	public String getexternal_id(){
		return this.external_id;
	}
	public String getname(){
		return this.name;
	}
	public String getcreated_at(){
		return this.created_at;
	}
	public String getupdated_at(){
		return this.updated_at;
	}
	public List<String> getdomain_names(){
		return this.domain_names;
	}
	public String getdetails(){
		return this.details;
	}
	public String getnotes(){
		return this.notes;
	}
	public int getgroup_id(){
		return this.group_id;
	}
	public boolean getshared_tickets(){
		return this.shared_tickets;
	}
	public boolean getshared_comments(){
		return this.shared_comments;
	}
	public List<String> gettags(){
		return this.tags;
	}
	public Map<String, Object> getorganization_fields(){
		return this.organization_fields;
	}

	public void setid(int id){
		this.id = id;
	}
	public void seturl(String url){
		this.url = url;
	}
	public void setexternal_id(String external_id){
		this.external_id = external_id;
	}
	public void setname(String name){
		this.name = name;
	}
	public void setcreated_at(String created_at){
		this.created_at = created_at;
	}
	public void setupdated_at(String updated_at){
		this.updated_at = updated_at;
	}
	public void setdomain_names(List<String> domain_names){
		this.domain_names = domain_names;
	}
	public void setdetails(String details){
		this.details = details;
	}
	public void setnotes(String notes){
		this.notes = notes;
	}
	public void setgroup_id(int group_id){
		this.group_id = group_id;
	}
	public void setshared_tickets(boolean shared_tickets){
		this.shared_tickets = shared_tickets;
	}
	public void setshared_comments(boolean shared_comments){
		this.shared_comments = shared_comments;
	}
	public void settags(List<String> tags){
		this.tags = tags;
	}
	public void setorganization_fields(Map<String, Object> organization_fields){
		this.organization_fields = organization_fields;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Name: " + this.name + "\r\n");
		sb.append("ID: " + this.id + "\r\n");
		sb.append("URL: " + this.url + "\r\n");
		sb.append("External ID: " + this.external_id + "\r\n");
		sb.append("Created At: " + this.created_at + "\r\n");
		sb.append("Updated At: " + this.updated_at + "\r\n");
		sb.append("Domains: \r\n");
		if (this.domain_names != null)
			for (String domain : this.domain_names){
				sb.append("\t" + domain + "\r\n");
			}
		sb.append("Details: " + this.details + "\r\n");
		sb.append("Notes: " + this.notes + "\r\n");
		sb.append("Org Fields: \r\n");
		if (this.organization_fields != null)
			for (Entry<String, Object> orgField: this.organization_fields.entrySet()){
				sb.append("\t" + orgField.getKey().toString() + ": " + orgField.getValue().toString());
			}
		return sb.toString();
	}
}
