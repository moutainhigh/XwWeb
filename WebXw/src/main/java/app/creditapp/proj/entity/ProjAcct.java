package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjAcct.java
* Description:
* @version：1.0
**/
public class ProjAcct extends BaseDomain {
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String upDate;//修改日期
	private String txDate;//登记日期
	private String opNo;//操作员
	private String opName;//操作员名称
	private String deptNo;//登记部门
	private String filler;//备注
	private String acctDesc;//账户说明
	private String cardNo;//关联银行账号[虚拟账户需要填写]
	private String acctUse;//账户用途[01-发放贷款02还贷款03代收费用]
	private Double acctBal;//账户余额
	private String opnBank;//账户开户行
	private String acctName;//账户户名
	private String acctType;//账户类型[01-专户02-虚拟户-03归集户]
	private String acctNo;//账hu号
	private String acctId;//账号ID[主键]
	private String projName;//项目名称
	private String projNo;//项目编号[外键]
	private String cardSts;//账户状态[01有效02失效]
	private String cardName;//备付金账户名称

	private String orgNo;//商户号
	private String cardChn;//C虚拟账户渠道方[]  channelNo
	private String bankProv;//开户银行所在省
	private String bankCity;//开户银行所在市
	private String query;
	
	//外加
	private String sendSts;
	private String projId;//项目编号[外键]
	private String loginid;//修改人员
	private String taCity;//软通开户账号所在市
	private String sellFlag;//结算标识SETT_FLAG
	/**
	 * @return 修改人员
	 */
	public String getUpOpno() {
	 	return upOpno;
	}
	/**
	 * @设置 修改人员
	 * @param upOpno
	 */
	public void setUpOpno(String upOpno) {
	 	this.upOpno = upOpno;
	}
	/**
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记部门
	 */
	public String getDeptNo() {
	 	return deptNo;
	}
	/**
	 * @设置 登记部门
	 * @param deptNo
	 */
	public void setDeptNo(String deptNo) {
	 	this.deptNo = deptNo;
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
	/**
	 * @return 账户说明
	 */
	public String getAcctDesc() {
	 	return acctDesc;
	}
	/**
	 * @设置 账户说明
	 * @param acctDesc
	 */
	public void setAcctDesc(String acctDesc) {
	 	this.acctDesc = acctDesc;
	}
	/**
	 * @return 关联银行账号[虚拟账户需要填写]
	 */
	public String getCardNo() {
	 	return cardNo;
	}
	/**
	 * @设置 关联银行账号[虚拟账户需要填写]
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
	 	this.cardNo = cardNo;
	}
	/**
	 * @return 账户用途[01-发放贷款02还贷款03代收费用]
	 */
	public String getAcctUse() {
	 	return acctUse;
	}
	/**
	 * @设置 账户用途[01-发放贷款02还贷款03代收费用]
	 * @param acctUse
	 */
	public void setAcctUse(String acctUse) {
	 	this.acctUse = acctUse;
	}
	/**
	 * @return 账户余额
	 */
	public Double getAcctBal() {
	 	return acctBal;
	}
	/**
	 * @设置 账户余额
	 * @param acctBal
	 */
	public void setAcctBal(Double acctBal) {
	 	this.acctBal = acctBal;
	}
	/**
	 * @return 账户开户行
	 */
	public String getOpnBank() {
	 	return opnBank;
	}
	/**
	 * @设置 账户开户行
	 * @param opnBank
	 */
	public void setOpnBank(String opnBank) {
	 	this.opnBank = opnBank;
	}
	/**
	 * @return 账户户名
	 */
	public String getAcctName() {
	 	return acctName;
	}
	/**
	 * @设置 账户户名
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
	 	this.acctName = acctName;
	}
	/**
	 * @return 账户类型[01-专户02-虚拟户-03归集户]
	 */
	public String getAcctType() {
	 	return acctType;
	}
	/**
	 * @设置 账户类型[01-专户02-虚拟户-03归集户]
	 * @param acctType
	 */
	public void setAcctType(String acctType) {
	 	this.acctType = acctType;
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
	 * @return 账号ID[主键]
	 */
	public String getAcctId() {
	 	return acctId;
	}
	/**
	 * @设置 账号ID[主键]
	 * @param acctId
	 */
	public void setAcctId(String acctId) {
	 	this.acctId = acctId;
	}
	/**
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
	}
	/**
	 * @return 项目编号[外键]
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号[外键]
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	public String getCardSts() {
		return cardSts;
	}
	public void setCardSts(String cardSts) {
		this.cardSts = cardSts;
	}
	public String getUpOpname() {
		return upOpname;
	}
	public void setUpOpname(String upOpname) {
		this.upOpname = upOpname;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public String getBankProv() {
		return bankProv;
	}
	public void setBankProv(String bankProv) {
		this.bankProv = bankProv;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getSendSts() {
		return sendSts;
	}
	public void setSendSts(String sendSts) {
		this.sendSts = sendSts;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getTaCity() {
		return taCity;
	}
	public void setTaCity(String taCity) {
		this.taCity = taCity;
	}
	public String getSellFlag() {
		return sellFlag;
	}
	public void setSellFlag(String sellFlag) {
		this.sellFlag = sellFlag;
	}
	
	
}