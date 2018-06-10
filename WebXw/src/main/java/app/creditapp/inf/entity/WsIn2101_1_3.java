package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * listCom
 * @描述   进件批量申请--输入实体类 LN_COM_MID
 */
public class WsIn2101_1_3 {
	
	private String appId;
	private String comName;  //借款人名称
	private String comIdtype;//证件类型
	private String comIdno;  //证件号码
	private String comTel;   //联系电话
	private String pactNo; //合同号
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComIdtype() {
		return comIdtype;
	}
	public void setComIdtype(String comIdtype) {
		this.comIdtype = comIdtype;
	}
	public String getComIdno() {
		return comIdno;
	}
	public void setComIdno(String comIdno) {
		this.comIdno = comIdno;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
}
