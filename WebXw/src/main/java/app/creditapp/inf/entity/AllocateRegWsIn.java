package app.creditapp.inf.entity;

import app.base.BaseDomain;

/**
 * Title: AllocateRegWsIn.java Description:
 * 
 * @version：1.0
 **/
public class AllocateRegWsIn extends BaseDomain {
	// 主信息对象
	private String id;// 唯一标识本条记录(唯一标识，外围系统通过该ID可获得生成的拨款台账主信息)
	private String businessflag;// 业务标示1:账户拨款，2:信托付自营，3:账户收款，4:自营代垫，5:信托付保障基金，6:多账户拨款
	private String  autoSyncFlag ;//自动同步辅助核算标示
	private String projectid;// 项目ID
	private String ddtype;// 代垫类型(条件必填，如果交易类型为自营代垫则必填 1:垫营业税及附加2:垫费)
	private String transdate;// 业务日期(拨款日期) yyyy-MM-dd
	private String transreason;// 拨款依据
	private String purpose;// 用途
	private String memo;// 摘要
	// private String userid;//创建人ID
	// private String submituserid;//提交人ID (条件必填：自动提交审批时，必填)
	private String userCode;// 创建人CODE
	private String staffapp;// 拨款对象 (自营代垫必填：0：外部账户 1：个人员工户2：信托户)
	// 明细信息对象
	private String amount;// 交易金额(2位小数)
	private String bktranstypecode;// 拨款的交易类型编码(拨款时必填)
	private String sktranstypecode;// 收款的交易类型编码 (条件必填，信托付自营或者收款交易时必填)
	private String feetypecode;// 费用类型编码(条件必填,费用类交易类型时候必填)
	private String taxtypecode;// 税目类型编码(条件必填,税目类交易时，必填 )
	private String customid;// 收款人ID
	private String accountid;// 资金运用方账号ID/借据ID(条件必填，当明细交易类型存在账户类交易类型时候必填)
	// private String opbankacntid ;//对方银行ID
	private String opbankacntno;// 对方银行账号
	private String opbankname;// 对方银行账号开户行
	private String opbankacntname;// 对方银行账号户名
	private String opbankprovince;// 开户行所属省
	private String opbankcity;// 开户行所属市
	private String repaytypeid;// 还款方式
								// 条件必填，在内部账户中的业务分类为贷款的，且明细交易类型中为本金的，01正常收回，02资产重组，03资产剥离，04以资抵债，05担保代偿，06核损核销，07政策性还款，08债转股、09转出。
	private String appusercode;// 内部人员(条件必填:自营代垫拨款对象为个人时候必填)

	// 以下内容为辅助核算内容
	private String personnel;// 人员(名字)
	private String project;// 项目(名字)
	private String customer;// 客户(名字)
	private String cashflow;// 现金流向
	private String virtualaccount;// 虚拟账户
	private String contractnumber;// 运用合同号
	private String stockcode;// 股票代码
	private String fundaccount;// 资金账号
	private String salesdepartment;// 营业部
	private String checknumber;// 支票号
	private String businesstype;// 业务类型
	private String cashflowstatement;// 现金流量表项
	private String taxcategory;// 税种
	private String interestrate;// 利率
	private String industry;// 行业
	private String stock;// 股比
	private String assetname;// 资产名称
	private String productname;// 产品名称
	private String clientorbeneficiary;// 委托人/受益人
	private String shareholder;// 股东
	private String entrustcontractnumber;// 委托合同号
	private String tradingpurpose;// 交易目的
	private String contractvariety;// 合约品种
	private String dateofreceipt;// 收付款日期
	private String interestdate;// 起息日
	private String duedate;// 到期日
	private String inorout;// 系统内外
	private String billnumber;// 票据编号
	// 其他
	private String acctId;
	private String wssts;
	private String txtime;
	private String sdtime;
	private String transid;
	private String workflowstate;
	private String remarks;

	/**
	 * @return 唯一标识本条记录(唯一标识，外围系统通过该ID可获得生成的拨款台账主信息)
	 */
	public String getId() {
		return id;
	}

	/**
	 * @设置 唯一标识本条记录(唯一标识，外围系统通过该ID可获得生成的拨款台账主信息)
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 业务标示1:账户拨款，2:信托付自营，3:账户收款，4:自营代垫，5:信托付保障基金，6:多账户拨款
	 */
	public String getBusinessflag() {
		return businessflag;
	}

	/**
	 * @设置 业务标示1:账户拨款，2:信托付自营，3:账户收款，4:自营代垫，5:信托付保障基金，6:多账户拨款
	 * @param businessflag
	 */
	public void setBusinessflag(String businessflag) {
		this.businessflag = businessflag;
	}

	/**
	 * @return 项目ID
	 */
	public String getProjectid() {
		return projectid;
	}

	/**
	 * @设置 项目ID
	 * @param projectid
	 */
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	/**
	 * @return 代垫类型(条件必填，如果交易类型为自营代垫则必填 1:垫营业税及附加2:垫费)
	 */
	public String getDdtype() {
		return ddtype;
	}

	/**
	 * @设置 代垫类型(条件必填，如果交易类型为自营代垫则必填 1:垫营业税及附加2:垫费)
	 * @param ddtype
	 */
	public void setDdtype(String ddtype) {
		this.ddtype = ddtype;
	}

	/**
	 * @return 业务日期(拨款日期) yyyy-MM-dd
	 */
	public String getTransdate() {
		return transdate;
	}

	/**
	 * @设置 业务日期(拨款日期) yyyy-MM-dd
	 * @param transdate
	 */
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	/**
	 * @return 拨款依据
	 */
	public String getTransreason() {
		return transreason;
	}

	/**
	 * @设置 拨款依据
	 * @param transreason
	 */
	public void setTransreason(String transreason) {
		this.transreason = transreason;
	}

	/**
	 * @return 用途
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @设置 用途
	 * @param purpose
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return 摘要
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @设置 摘要
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return 交易金额(2位小数)
	 */
	public String getAmount() {
		if(amount.indexOf(".")<0){
			amount=amount+".00";
		}else{
			amount =amount+"0000";
			amount = amount.substring(0, amount.indexOf(".")+3);
		}
		return amount;
	}

	/**
	 * @设置 交易金额(2位小数)
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 拨款的交易类型编码(拨款时必填)
	 */
	public String getBktranstypecode() {
		return bktranstypecode;
	}

	/**
	 * @设置 拨款的交易类型编码(拨款时必填)
	 * @param bktranstypecode
	 */
	public void setBktranstypecode(String bktranstypecode) {
		this.bktranstypecode = bktranstypecode;
	}

	/**
	 * @return 收款的交易类型编码 (条件必填，信托付自营或者收款交易时必填)
	 */
	public String getSktranstypecode() {
		return sktranstypecode;
	}

	/**
	 * @设置 收款的交易类型编码 (条件必填，信托付自营或者收款交易时必填)
	 * @param sktranstypecode
	 */
	public void setSktranstypecode(String sktranstypecode) {
		this.sktranstypecode = sktranstypecode;
	}

	/**
	 * @return 费用类型编码(条件必填,费用类交易类型时候必填)
	 */
	public String getFeetypecode() {
		return feetypecode;
	}

	/**
	 * @设置 费用类型编码(条件必填,费用类交易类型时候必填)
	 * @param feetypecode
	 */
	public void setFeetypecode(String feetypecode) {
		this.feetypecode = feetypecode;
	}

	/**
	 * @return 税目类型编码(条件必填,税目类交易时，必填 )
	 */
	public String getTaxtypecode() {
		return taxtypecode;
	}

	/**
	 * @设置 税目类型编码(条件必填,税目类交易时，必填 )
	 * @param taxtypecode
	 */
	public void setTaxtypecode(String taxtypecode) {
		this.taxtypecode = taxtypecode;
	}

	/**
	 * @return 收款人ID
	 */
	public String getCustomid() {
		return customid;
	}

	/**
	 * @设置 收款人ID
	 * @param customid
	 */
	public void setCustomid(String customid) {
		this.customid = customid;
	}

	/**
	 * @return 资金运用方账号ID/借据ID(条件必填，当明细交易类型存在账户类交易类型时候必填)
	 */
	public String getAccountid() {
		return accountid;
	}

	/**
	 * @设置 资金运用方账号ID/借据ID(条件必填，当明细交易类型存在账户类交易类型时候必填)
	 * @param accountid
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return 对方银行账号
	 */
	public String getOpbankacntno() {
		return opbankacntno;
	}

	/**
	 * @设置 对方银行账号
	 * @param opbankacntno
	 */
	public void setOpbankacntno(String opbankacntno) {
		this.opbankacntno = opbankacntno;
	}

	/**
	 * @return 对方银行账号开户行
	 */
	public String getOpbankname() {
		return opbankname;
	}

	/**
	 * @设置 对方银行账号开户行
	 * @param opbankname
	 */
	public void setOpbankname(String opbankname) {
		this.opbankname = opbankname;
	}

	/**
	 * @return 对方银行账号户名
	 */
	public String getOpbankacntname() {
		return opbankacntname;
	}

	/**
	 * @设置 对方银行账号户名
	 * @param opbankacntname
	 */
	public void setOpbankacntname(String opbankacntname) {
		this.opbankacntname = opbankacntname;
	}

	/**
	 * @return 开户行所属省
	 */
	public String getOpbankprovince() {
		return opbankprovince;
	}

	/**
	 * @设置 开户行所属省
	 * @param opbankprovince
	 */
	public void setOpbankprovince(String opbankprovince) {
		this.opbankprovince = opbankprovince;
	}

	/**
	 * @return 开户行所属市
	 */
	public String getOpbankcity() {
		return opbankcity;
	}

	/**
	 * @设置 开户行所属市
	 * @param opbankcity
	 */
	public void setOpbankcity(String opbankcity) {
		this.opbankcity = opbankcity;
	}

	/**
	 * @return 人员(名字)
	 */
	public String getPersonnel() {
		return personnel;
	}

	/**
	 * @设置 人员(名字)
	 * @param personnel
	 */
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	/**
	 * @return 项目(名字)
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @设置 项目(名字)
	 * @param project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return 客户(名字)
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @设置 客户(名字)
	 * @param customer
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return 现金流向
	 */
	public String getCashflow() {
		return cashflow;
	}

	/**
	 * @设置 现金流向
	 * @param cashflow
	 */
	public void setCashflow(String cashflow) {
		this.cashflow = cashflow;
	}

	/**
	 * @return 虚拟账户
	 */
	public String getVirtualaccount() {
		return virtualaccount;
	}

	/**
	 * @设置 虚拟账户
	 * @param virtualaccount
	 */
	public void setVirtualaccount(String virtualaccount) {
		this.virtualaccount = virtualaccount;
	}

	/**
	 * @return 运用合同号
	 */
	public String getContractnumber() {
		return contractnumber;
	}

	/**
	 * @设置 运用合同号
	 * @param contractnumber
	 */
	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	/**
	 * @return 股票代码
	 */
	public String getStockcode() {
		return stockcode;
	}

	/**
	 * @设置 股票代码
	 * @param stockcode
	 */
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	/**
	 * @return 资金账号
	 */
	public String getFundaccount() {
		return fundaccount;
	}

	/**
	 * @设置 资金账号
	 * @param fundaccount
	 */
	public void setFundaccount(String fundaccount) {
		this.fundaccount = fundaccount;
	}

	/**
	 * @return 营业部
	 */
	public String getSalesdepartment() {
		return salesdepartment;
	}

	/**
	 * @设置 营业部
	 * @param salesdepartment
	 */
	public void setSalesdepartment(String salesdepartment) {
		this.salesdepartment = salesdepartment;
	}

	/**
	 * @return 支票号
	 */
	public String getChecknumber() {
		return checknumber;
	}

	/**
	 * @设置 支票号
	 * @param checknumber
	 */
	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	/**
	 * @return 业务类型
	 */
	public String getBusinesstype() {
		return businesstype;
	}

	/**
	 * @设置 业务类型
	 * @param businesstype
	 */
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	/**
	 * @return 现金流量表项
	 */
	public String getCashflowstatement() {
		return cashflowstatement;
	}

	/**
	 * @设置 现金流量表项
	 * @param cashflowstatement
	 */
	public void setCashflowstatement(String cashflowstatement) {
		this.cashflowstatement = cashflowstatement;
	}

	/**
	 * @return 税种
	 */
	public String getTaxcategory() {
		return taxcategory;
	}

	/**
	 * @设置 税种
	 * @param taxcategory
	 */
	public void setTaxcategory(String taxcategory) {
		this.taxcategory = taxcategory;
	}

	/**
	 * @return 利率
	 */
	public String getInterestrate() {
		return interestrate;
	}

	/**
	 * @设置 利率
	 * @param interestrate
	 */
	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	/**
	 * @return 行业
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @设置 行业
	 * @param industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return 股比
	 */
	public String getStock() {
		return stock;
	}

	/**
	 * @设置 股比
	 * @param stock
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}

	/**
	 * @return 资产名称
	 */
	public String getAssetname() {
		return assetname;
	}

	/**
	 * @设置 资产名称
	 * @param assetname
	 */
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	/**
	 * @return 产品名称
	 */
	public String getProductname() {
		return productname;
	}

	/**
	 * @设置 产品名称
	 * @param productname
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}

	/**
	 * @return 委托人/受益人
	 */
	public String getClientorbeneficiary() {
		return clientorbeneficiary;
	}

	/**
	 * @设置 委托人/受益人
	 * @param clientorbeneficiary
	 */
	public void setClientorbeneficiary(String clientorbeneficiary) {
		this.clientorbeneficiary = clientorbeneficiary;
	}

	/**
	 * @return 股东
	 */
	public String getShareholder() {
		return shareholder;
	}

	/**
	 * @设置 股东
	 * @param shareholder
	 */
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}

	/**
	 * @return 委托合同号
	 */
	public String getEntrustcontractnumber() {
		return entrustcontractnumber;
	}

	/**
	 * @设置 委托合同号
	 * @param entrustcontractnumber
	 */
	public void setEntrustcontractnumber(String entrustcontractnumber) {
		this.entrustcontractnumber = entrustcontractnumber;
	}

	/**
	 * @return 交易目的
	 */
	public String getTradingpurpose() {
		return tradingpurpose;
	}

	/**
	 * @设置 交易目的
	 * @param tradingpurpose
	 */
	public void setTradingpurpose(String tradingpurpose) {
		this.tradingpurpose = tradingpurpose;
	}

	/**
	 * @return 合约品种
	 */
	public String getContractvariety() {
		return contractvariety;
	}

	/**
	 * @设置 合约品种
	 * @param contractvariety
	 */
	public void setContractvariety(String contractvariety) {
		this.contractvariety = contractvariety;
	}

	/**
	 * @return 收付款日期
	 */
	public String getDateofreceipt() {
		return dateofreceipt;
	}

	/**
	 * @设置 收付款日期
	 * @param dateofreceipt
	 */
	public void setDateofreceipt(String dateofreceipt) {
		this.dateofreceipt = dateofreceipt;
	}

	/**
	 * @return 起息日
	 */
	public String getInterestdate() {
		return interestdate;
	}

	/**
	 * @设置 起息日
	 * @param interestdate
	 */
	public void setInterestdate(String interestdate) {
		this.interestdate = interestdate;
	}

	/**
	 * @return 到期日
	 */
	public String getDuedate() {
		return duedate;
	}

	/**
	 * @设置 到期日
	 * @param duedate
	 */
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	/**
	 * @return 系统内外
	 */
	public String getInorout() {
		return inorout;
	}

	/**
	 * @设置 系统内外
	 * @param inorout
	 */
	public void setInorout(String inorout) {
		this.inorout = inorout;
	}

	/**
	 * @return 票据编号
	 */
	public String getBillnumber() {
		return billnumber;
	}

	/**
	 * @设置 票据编号
	 * @param billnumber
	 */
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}

	/**
	 * @return the acctId
	 */
	public String getAcctId() {
		return acctId;
	}

	/**
	 * @param acctId
	 *            the acctId to set
	 */
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}


	/**
	 * @return the staffapp
	 */
	public String getStaffapp() {
		return staffapp;
	}

	/**
	 * @param staffapp
	 *            the staffapp to set
	 */
	public void setStaffapp(String staffapp) {
		this.staffapp = staffapp;
	}

	/**
	 * @return the repaytypeid
	 */
	public String getRepaytypeid() {
		return repaytypeid;
	}

	/**
	 * @param repaytypeid
	 *            the repaytypeid to set
	 */
	public void setRepaytypeid(String repaytypeid) {
		this.repaytypeid = repaytypeid;
	}

	/**
	 * @return the appUserCode
	 */
	public String getAppusercode() {
		return appusercode;
	}

	/**
	 * @param appUserCode
	 *            the appUserCode to set
	 */
	public void setAppusercode(String appusercode) {
		this.appusercode = appusercode;
	}

	public String getWssts() {
		return wssts;
	}

	public void setWssts(String wssts) {
		this.wssts = wssts;
	}

	public String getTxtime() {
		return txtime;
	}

	public void setTxtime(String txtime) {
		this.txtime = txtime;
	}

	public String getSdtime() {
		return sdtime;
	}

	public void setSdtime(String sdtime) {
		this.sdtime = sdtime;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getWorkflowstate() {
		return workflowstate;
	}

	public void setWorkflowstate(String workflowstate) {
		this.workflowstate = workflowstate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAutoSyncFlag() {
		return autoSyncFlag;
	}

	public void setAutoSyncFlag(String autoSyncFlag) {
		this.autoSyncFlag = autoSyncFlag;
	}
}