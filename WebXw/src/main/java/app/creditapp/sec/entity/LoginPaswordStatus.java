package app.creditapp.sec.entity;

public enum LoginPaswordStatus {
	
	SUCCESS("登录成功"),
	FAIL("登录失败"),
	OVER_TIME("当前密码使用时间已经超过期限%d天,需要重新设定密码"),
	VALIDATE_FAIL("密码验证失败"),
	NEED_CHANGE_PASSWORD("您是首次登录，需要进行密码重置"),
	OVER_FAULT_TOLE("您累计输错密码次数已经超过容错次数%d次，请与管理员联系");
	
	private final String message;
	private int time = 0;
	
	LoginPaswordStatus(String message){
		this.time = 0;
		this.message = message;
	}
	
	public String showMessage(int time){
		return String.format(message, time);
	}
	
	public String showMessage(){
		return showMessage(this.time);
	}
	
	public LoginPaswordStatus changeTime(int time){
		this.time = time;
		return this;
	}
	
	public static void main(String[] args) {
		String str  = LoginPaswordStatus.OVER_FAULT_TOLE.changeTime(5).showMessage();
		System.out.println(str);
	}
}
