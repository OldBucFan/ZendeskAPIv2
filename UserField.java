package zdAPI;

public class UserField {

	private String name;
	private Object value;

	public UserField() {
		// Auto-generated constructor stub
	}
	public UserField(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	public UserField(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public UserField(String name, boolean value){
		this.name = name;
		this.value = value;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public void setvalue(Object value){
		this.value = value;
	}
	public void setvalue(boolean value){
		this.value = value;
	}

	public String getname(){
		return this.name;
	}
	public Object getvalue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return this.name + " : " + this.value.toString(); 
	}
}
