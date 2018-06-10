package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnApplyMid.java
* Description:
* @version：1.0
**/
public class LnFail extends BaseDomain {
	private String batchNo;//批次号码
	private String appId;//申请ID
	private String brNo;//合作机构号码
	private String pactNo;//合同编号
	private String cifName;//客户名称
	private String prdtName;//客户名称
	private Double appAmt;//客户名称
	private String projNo;//客户名称
	private String projName;//客户名称
	private String rsDesc;//结果说明
	private String appDate;//结果说明
	private String brName;//合作机构名称
	
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getRsDesc() {
		return rsDesc;
	}
	public void setRsDesc(String rsDesc) {
		this.rsDesc = rsDesc;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	/**
	 * @return the prdtName
	 */
	public String getPrdtName() {
		return prdtName;
	}
	/**
	 * @param prdtName the prdtName to set
	 */
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	/**
	 * @return the appAmt
	 */
	public Double getAppAmt() {
		return appAmt;
	}
	/**
	 * @param appAmt the appAmt to set
	 */
	public void setAppAmt(Double appAmt) {
		this.appAmt = appAmt;
	}
	
}