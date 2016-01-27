package zdAPI;

public class Result {

	private int id;
	private int index;
	private String error;
	private String[] details;


	public int getid(){
		return this.id;
	}
	public int getindex(){
		return this.index;
	}
	public String geterror(){
		return this.error;
	}
	public String[] getdetails(){
		return this.details;
	}

	public void setid(int id){
		this.id = id;
	}
	public void setindex(int index){
		this.index = index;
	}
	public void seterror(String error){
		this.error = error;
	}
	public void setdetails(String[] details){
		this.details = details;
	}

	public Result(int id,int index,String error,String[] details) {

		this.id = id;
		this.index = index;
		this.error = error;
		this.details = details;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + this.getid()+"\r\n");
		sb.append("Index: " + this.getindex()+"\r\n");
		sb.append("Error: " + this.geterror()+"\r\n");
		sb.append("Details:"+"\r\n");
		for (String detail : this.getdetails()){
			sb.append("\t" + detail + "\r\n");
		}
		return sb.toString();
	}
}
