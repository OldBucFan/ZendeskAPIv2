package zdAPI;

import java.util.ArrayList;

public class UserQueryResponse {

	private ArrayList<User> users;
	private String next_page;
	private String previous_page;
	private int count;
	
	public UserQueryResponse() {
		
	}
	public UserQueryResponse(ArrayList<User> users, String next_page, String previous_page, int count){
		this.users = users;
		this.next_page = next_page;
		this.previous_page = previous_page;
		this.count = count;
	}

	public ArrayList<User> getusers(){
		return this.users;
	}
	public String getnext_page(){
		return this.next_page;
	}
	public String getprevious_page(){
		return this.previous_page;
	}
	public int getcount(){
		return this.count;
	}

	public void setusers(ArrayList<User> users){
		this.users = users;
	}
	public void setnext_page(String next_page){
		this.next_page = next_page;
	}
	public void setprevious_page(String previous_page){
		this.previous_page = previous_page;
	}
	public void setcount(int count){
		this.count = count;
	}

}
