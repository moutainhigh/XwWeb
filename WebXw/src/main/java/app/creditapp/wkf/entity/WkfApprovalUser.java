package app.creditapp.wkf.entity;
import app.base.BaseDomain;
/**
* Title: WkfApprovalUser.java
* Description:
* @version1.0
**/
public class WkfApprovalUser extends BaseDomain {
	private String seq;//序号
	private String wkfUserName;//操作员
	private String wkfRoleNo;//审批角色编号
	private String wkfBrNo;//审批用户所在机构
	private String br_no;    	//登记机构
	private String op_no;		//登记人
	private String tx_date;		//登记日期
	private String up_date;		//更新日期
	private String wkfRoleName;

	
	 public String getSeq() {
	 	return seq;
	 }
	 public void setSeq(String seq) {
	 	this.seq = seq;
	 }
	 public String getWkfUserName() {
	 	return wkfUserName;
	 }
	 public void setWkfUserName(String wkfUserName) {
	 	this.wkfUserName = wkfUserName;
	 }
	 public String getWkfRoleNo() {
	 	return wkfRoleNo;
	 }
	 public void setWkfRoleNo(String wkfRoleNo) {
	 	this.wkfRoleNo = wkfRoleNo;
	 }
	public void setWkfBrNo(String wkfBrNo) {
		this.wkfBrNo = wkfBrNo;
	}
	public String getWkfBrNo() {
		return wkfBrNo;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String brNo) {
		br_no = brNo;
	}
	public String getOp_no() {
		return op_no;
	}
	public void setOp_no(String opNo) {
		op_no = opNo;
	}
	public String getTx_date() {
		return tx_date;
	}
	public void setTx_date(String txDate) {
		tx_date = txDate;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String upDate) {
		up_date = upDate;
	}
	public String getWkfRoleName() {
		return wkfRoleName;
	}
	public void setWkfRoleName(String wkfRoleName) {
		this.wkfRoleName = wkfRoleName;
	}
	
}