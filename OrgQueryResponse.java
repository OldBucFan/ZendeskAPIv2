package zdAPI;

import java.util.List;

public class OrgQueryResponse {

	private List<Organization> organizations;
	private int count;
	private String next_page;
	private String previous_page;

	public OrgQueryResponse() {
		//  Auto-generated constructor stub
	}
	public OrgQueryResponse(List<Organization> organizations , int count , String next_page , String previous_page){
		this.organizations = organizations;
		this.count = count;
		this.next_page = next_page;
		this.previous_page = previous_page;
	}
	
	public List<Organization> getorganizations(){
		return this.organizations;
	}
	public int getcount(){
		return this.count;
	}
	public String getnext_page(){
		return this.next_page;
	}
	public String getprevious_page(){
		return this.previous_page;
	}

	public void setorganizations(List<Organization> organizations){
		this.organizations = organizations;
	}
	public void setcount(int count){
		this.count = count;
	}
	public void setnext_page(String next_page){
		this.next_page = next_page;
	}
	public void setprevious_page(String previous_page){
		this.previous_page = previous_page;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Org Query Response: \r\n\t");
		sb.append("count: " + this.count + "\r\n\t");
		sb.append("Next page URL: " + this.next_page + "\r\n\t");
		sb.append("Prev page URL: " + this.previous_page + "\r\n\t");
		sb.append("Orgs: \r\n\t\t");
		for(Organization org : this.organizations){
			sb.append(org.toString() + "\r\n\t\t");
		}
		return sb.toString();
	}
}
