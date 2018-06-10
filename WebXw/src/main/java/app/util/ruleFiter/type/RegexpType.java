package app.util.ruleFiter.type;

import java.util.regex.Pattern;

public enum RegexpType {
	IDCARD("IDcard","身份证"),
	MOBILEPHONE("mobilephone","手机号码"),
	EMAIL("email","电子邮箱"),
	MONEY("money","金额"),
	DATE("date","日期"),
	TIME("time","时间"),
	IPADDRESS("ipaddress","IP地址");
	
	
	private String name;
	private String znName;
	
	private RegexpType(String name,String znName){
		this.name = name;
		this.znName = znName;
	}
	
	public boolean isValIdCard(String value){
		Pattern p15 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		Pattern p18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
		return  p15.matcher(value).matches() || p18.matcher(value).matches();
	}
	
	public boolean isMobilePhoneNumber(String value){
		 Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		 return p.matcher(value).matches();
	}
	
	public boolean isEmail(String value){
		 Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		 return p.matcher(value).matches();
	}
	
	public boolean isIPAddress(String value){
		 Pattern p = Pattern.compile("(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}");
		 return p.matcher(value).matches();
	}
	
	public boolean isMoney(String value){
		 Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		 return p.matcher(value).matches();
	}
	
	public boolean isTime(String value){
		 Pattern p = Pattern.compile("(([01][0-9])|(2[0-3])):[0-5][0-9]");
		 return p.matcher(value).matches();
	}
	
	public boolean isDate(String value){
		String macthStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		 Pattern p = Pattern.compile(macthStr);
		 return p.matcher(value).matches();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZnName() {
		return znName;
	}

	public void setZnName(String znName) {
		this.znName = znName;
	}

	public boolean validate(String value){
		switch (this) {
			case IDCARD:
				return isValIdCard(value);
			case MOBILEPHONE:
				return isMobilePhoneNumber(value);
			case EMAIL:
				return isEmail(value);
			case IPADDRESS:
				return isIPAddress(value);
			case TIME:
				return isTime(value);
			case DATE:
				return isDate(value);
			case MONEY:
				return isMoney(value);
			default:
				throw new RuntimeException("不正确的正则验证格式");
		}
	}
	
	public static RegexpType returnRegByName(String inputName){
		for(RegexpType type:values()){
			if(type.getName().equals(inputName))return type;
		}
		return null;
	}
	
	public static void main(String[] args) {
		double sMoney = 0.00;	
		System.out.println(RegexpType.MONEY.isMoney(String.valueOf(sMoney)));
		String date = "20120533";
		System.out.println(RegexpType.DATE.isDate(date));
	}
	
}
