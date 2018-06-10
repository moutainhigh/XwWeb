package app.creditapp.wkf.entity;
import app.base.BaseDomain;
/**
* Title: WkfApprovalRole.java
* Description:
* @version1.0
**/
public class WkfApprovalRole extends BaseDomain 
{
	private String wkfRoleNo;   //审批角色编号
	private String wkfRoleName; //审批角色名称
	private String wkfrolelev;  //审批角色级别
	private String wkfbrno;		//机构号
	private String br_no;    	//登记机构
	private String op_no;		//登记人
	private String tx_date;		//登记日期
	private String up_date;		//更新日期

	/**
	 * @return 审批角色编号
	 */
	 public String getWkfRoleNo() {
	 	return wkfRoleNo;
	 }
	 /**
	 * @ 审批角色编号
	 * @param wkfRoleNo
	 */
	 public void setWkfRoleNo(String wkfRoleNo) {
	 	this.wkfRoleNo = wkfRoleNo;
	 }
	/**
	 * @return 审批角色名称
	 */
	 public String getWkfRoleName() {
	 	return wkfRoleName;
	 }
	 /**
	 * @审批角色名称
	 * @param wkfRoleName
	 */
	 public void setWkfRoleName(String wkfRoleName) {
	 	this.wkfRoleName = wkfRoleName;
	 }
	public String getWkfrolelev() {
		return wkfrolelev;
	}
	public String getWkfbrno() {
		return wkfbrno;
	}
	public void setWkfrolelev(String wkfrolelev) {
		this.wkfrolelev = wkfrolelev;
	}
	public void setWkfbrno(String wkfbrno) {
		this.wkfbrno = wkfbrno;
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
	
}