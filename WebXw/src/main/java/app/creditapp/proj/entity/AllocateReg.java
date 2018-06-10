package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: AllocateReg.java
* Description:
* @version：1.0
**/
public class AllocateReg extends BaseDomain {
	private String allocateRegId;//预拨款登记ID
	private String projNo;//项目编号
	private String acctId;//账号ID
	private String acctNo;//账号
	private String fAcctNo;//转入账号
	private Double txAmt;//拨款金额
	private String txDate;//拨款日期时间
	private String opNo;//拨款人员
	private String filler;//备注

	/**
	 * @return 预拨款登记ID
	 */
	public String getAllocateRegId() {
	 	return allocateRegId;
	}
	/**
	 * @设置 预拨款登记ID
	 * @param allocateRegId
	 */
	public void setAllocateRegId(String allocateRegId) {
	 	this.allocateRegId = allocateRegId;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 账号ID
	 */
	public String getAcctId() {
	 	return acctId;
	}
	/**
	 * @设置 账号ID
	 * @param acctId
	 */
	public void setAcctId(String acctId) {
	 	this.acctId = acctId;
	}
	/**
	 * @return 账号
	 */
	public String getAcctNo() {
	 	return acctNo;
	}
	/**
	 * @设置 账号
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
	 	this.acctNo = acctNo;
	}
	/**
	 * @return 转入账号
	 */
	public String getFAcctNo() {
	 	return fAcctNo;
	}
	/**
	 * @设置 转入账号
	 * @param fAcctNo
	 */
	public void setFAcctNo(String fAcctNo) {
	 	this.fAcctNo = fAcctNo;
	}
	/**
	 * @return 拨款金额
	 */
	public Double getTxAmt() {
	 	return txAmt;
	}
	/**
	 * @设置 拨款金额
	 * @param txAmt
	 */
	public void setTxAmt(Double txAmt) {
	 	this.txAmt = txAmt;
	}
	/**
	 * @return 拨款日期时间
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 拨款日期时间
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 拨款人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 拨款人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
	}
}