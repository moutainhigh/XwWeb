package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * listRel
 * @描述   进件批量申请--输入实体类 LN_REL_MID
 */
public class WsIn2101_1_4 {
	
	private String appId;
	private String relName; //借款人名称
	private String relIdtype; //证件类型
	private String relIdno;  //证件号码
    private String relTel; //联系电话
    private String pactNo; //合同号
    
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getRelName() {
		return relName;
	}
	public void setRelName(String relName) {
		this.relName = relName;
	}
	public String getRelIdtype() {
		return relIdtype;
	}
	public void setRelIdtype(String relIdtype) {
		this.relIdtype = relIdtype;
	}
	public String getRelIdno() {
		return relIdno;
	}
	public void setRelIdno(String relIdno) {
		this.relIdno = relIdno;
	}
	public String getRelTel() {
		return relTel;
	}
	public void setRelTel(String relTel) {
		this.relTel = relTel;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
    
    
		
}
