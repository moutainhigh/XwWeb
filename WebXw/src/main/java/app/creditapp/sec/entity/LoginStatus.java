package app.creditapp.sec.entity;

public enum LoginStatus {
	LOGINSUCCESS("1"),LOGOUT("9"),LOGINFAIL("2"),OUTBYOTHER("8");
	
	private final String  value;
	LoginStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
