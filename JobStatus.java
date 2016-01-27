package zdAPI;

import java.io.Serializable;


public class JobStatus implements Serializable{

	private static final long serialVersionUID = 2327692271094751684L;
	private String id;
	private String url;
	private int total;
	private int progress;
	private String status;
	private String message;
	private Result[] results;

	public String getid(){
		return this.id;
	}
	public String geturl(){
		return this.url;
	}
	public int gettotal(){
		return this.total;
	}
	public int getprogress(){
		return this.progress;
	}
	public String getstatus(){
		return this.status;
	}
	public String getmessage(){
		return this.message;
	}
	public Result[] getresults(){
		return this.results;
	}

	public void setid(String id){
		this.id = id;
	}
	public void seturl(String url){
		this.url = url;
	}
	public void settotal(int total){
		this.total = total;
	}
	public void setprogress(int progress){
		this.progress = progress;
	}
	public void setstatus(String status){
		this.status = status;
	}
	public void setmessage(String message){
		this.message = message;
	}
	public void setresults(Result[] results){
		this.results = results;
	}

	public JobStatus(String id,String url,int total,int progress,String status,String message,Result[] results) {
		this.id = id;
		this.url = url;
		this.total = total;
		this.progress = progress;
		this.status = status;
		this.message = message;
		this.results = results;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + this.getid() + "\r\n");
		sb.append("URL: " + this.geturl() + "\r\n");
		sb.append("Progress: " + this.getprogress() + "\r\n");
		sb.append("Status: " + this.getstatus() + "\r\n");
		sb.append("Message: " + this.getmessage() + "\r\n");
		sb.append("Results:");
		if (this.results == null) {
			System.out.println("No results");
		} else {
			for (Result resultOut : results){
				sb.append("\r\n\t" + resultOut.toString());
			}
		}
		return sb.toString();
	}
}
