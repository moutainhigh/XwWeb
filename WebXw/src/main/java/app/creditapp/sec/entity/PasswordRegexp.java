package app.creditapp.sec.entity;

import java.util.regex.Pattern;

public enum PasswordRegexp {
	MAX_LENGTH(1,Pattern.compile(".{1,6}$"),"密码长度限制为1-6位"),
	CONTAIN_NUMBER(2,Pattern.compile(".*[0-9]+.*"),"密码中必须包含数字"),
	CONTAIN_LETTER(3,Pattern.compile(".*[A-Za-z]+.*"),"密码必须包含英文字母"), 
	CONTAIN_UPPERCASE_LETTER(4,Pattern.compile(".*[A-Z]+.*"),"密码中必须包含大写字母"),
	CONTAIN_SPACIL_LETTER(5,Pattern.compile("[^%/$&]{1,}"),"密码中不得包含%/$&特殊字符"),
	CONTAIN_CASE_LETTER(6,Pattern.compile("[^A-Za-z]{1,}"),"密码中不得包含英文字母");
	
	public final int index;
	public final Pattern regexp;
	public final String desc;
	
	PasswordRegexp(int index,Pattern regexp,String desc){
		this.index = index;
		this.regexp = regexp;
		this.desc = desc;
	}
	
	public boolean matcherRegexp(String password){
		return this.regexp.matcher(password).matches();
	}
	public String matcherRegexpStr(String password){
		return this.regexp.matcher(password).matches()?Boolean.TRUE.toString():this.desc;
	}
	
	public static PasswordRegexp getRegexpByIndex(int index){
		for(PasswordRegexp reg:PasswordRegexp.values()){
			if(reg.index == index)return reg;
		}
		return null;
	}
	
	public static void main(String[] args) {
		String test = "dasdas大声道";
//		System.out.println(PasswordRegexp.getRegexpByIndex(2).matcherRegexpStr(test));
		for(PasswordRegexp reg:PasswordRegexp.values()){
			System.out.println(reg.desc);
			System.out.println(reg.matcherRegexpStr(test));
		}
	}
}
