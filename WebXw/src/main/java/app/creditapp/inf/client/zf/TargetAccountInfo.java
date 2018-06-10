package app.creditapp.inf.client.zf;

import app.creditapp.inf.client.IBody;

public class TargetAccountInfo implements IBody{
	
	private String Level;//优先级
	
	private AccountInfo accountInfo;//账户信息

	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		s.append("<TargetAccountInfo>");
		s.append("<Level>");
		s.append(this.Level==null?"":this.Level);
		s.append("</Level>");
		s.append(accountInfo.toXml());
		

		s.append("</TargetAccountInfo>");
		
		return s.toString();
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	
}
