package app.creditapp.sys.entity;

import app.base.BaseDomain;

/**
 * Title: PrdtBase.java Description:
 * 
 * @version：1.0
 **/
public class AccessCheckInfo extends BaseDomain {
	private String brNo;// 合作机构号
	private String flag;// 标志
	private String checkItem;//检查项目
	private String checkResult;//检查结果
	
	private String serial;//序号
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCheckItem() {
		return checkItem;
	}
	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}	
}