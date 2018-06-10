package app.creditapp.inf.entity;

/**
 * Title: AllocateReg.java Description:
 * 
 * @version：1.0
 **/
public class DetailTransactionCommonDTO {

	private String id;// 唯一标识本条记录
	private String amount;// 交易金额
	private String bkTransTypeCode;// 拨款的交易类型编码
	private String skTransTypeCode;// 收款的交易类型编码
	private String feeTypeCode;// 费用类型编码
	private String taxTypeCode;// 税目类型编码
	private String customID;// 收款人ID
	private String accountID;// 资金运用方账号ID/借据ID
	// private String opBankAcntID ;//对方银行ID
	private String opBankAcntNO;// 对方银行账号
	private String opBankName;// 对方银行账号开户行
	private String opBankAcntName;// 对方银行账号户名
	private String opBankProvince;// 开户行所属省
	private String opBankCity;// 开户行所属市
	private String repayTypeID;// 还款方式
								// 条件必填，在内部账户中的业务分类为贷款的，且明细交易类型中为本金的，01正常收回，02资产重组，03资产剥离，04以资抵债，05担保代偿，06核损核销，07政策性还款，08债转股、09转出。
	private String appUserCode;// 内部人员(条件必填:自营代垫拨款对象为个人时候必填)

	private String personnel;// 人员(名字)
	private String project;// 项目(名字)
	private String customer;// 客户(名字)
	private String cashFlow;// 现金流向
	private String virtualAccount;// 虚拟账户
	private String contractNumber;// 运用合同号
	private String stockCode;// 股票代码
	private String fundAccount;// 资金账号
	private String salesDepartment;// 营业部
	private String checkNumber;// 支票号
	private String businessType;// 业务类型
	private String cashFlowStatement;// 现金流量表项
	private String taxCategory;// 税种
	private String interestRate;// 利率
	private String industry;// 行业
	private String stock;// 股比
	private String assetName;// 资产名称
	private String productName;// 产品名称
	private String clientOrBeneficiary;// 委托人/受益人
	private String shareholder;// 股东
	private String entrustContractNumber;// 委托合同号
	private String tradingPurpose;// 交易目的
	private String contractVariety;// 合约品种
	private String dateOfReceipt;// 收付款日期
	private String interestDate;// 起息日
	private String dueDate;// 到期日
	private String inOrOut;// 系统内外
	private String billNumber;// 票据编号

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id == null ? "" : id;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount == null ? "" : amount;
	}

	/**
	 * @return the bkTransTypeCode
	 */
	public String getBkTransTypeCode() {
		return bkTransTypeCode;
	}

	/**
	 * @param bkTransTypeCode
	 *            the bkTransTypeCode to set
	 */
	public void setBkTransTypeCode(String bkTransTypeCode) {
		this.bkTransTypeCode = bkTransTypeCode == null ? "" : bkTransTypeCode;
	}

	/**
	 * @return the skTransTypeCode
	 */
	public String getSkTransTypeCode() {
		return skTransTypeCode;
	}

	/**
	 * @param skTransTypeCode
	 *            the skTransTypeCode to set
	 */
	public void setSkTransTypeCode(String skTransTypeCode) {
		this.skTransTypeCode = skTransTypeCode == null ? "" : skTransTypeCode;
	}

	/**
	 * @return the feeTypeCode
	 */
	public String getFeeTypeCode() {
		return feeTypeCode;
	}

	/**
	 * @param feeTypeCode
	 *            the feeTypeCode to set
	 */
	public void setFeeTypeCode(String feeTypeCode) {
		this.feeTypeCode = feeTypeCode == null ? "" : feeTypeCode;
	}

	/**
	 * @return the taxTypeCode
	 */
	public String getTaxTypeCode() {
		return taxTypeCode;
	}

	/**
	 * @param taxTypeCode
	 *            the taxTypeCode to set
	 */
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode == null ? "" : taxTypeCode;
	}

	/**
	 * @return the customID
	 */
	public String getCustomID() {
		return customID;
	}

	/**
	 * @param customID
	 *            the customID to set
	 */
	public void setCustomID(String customID) {
		this.customID = customID == null ? "" : customID;
	}

	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID == null ? "" : accountID;
	}

	/**
	 * @return the opBankAcntNO
	 */
	public String getOpBankAcntNO() {
		return opBankAcntNO;
	}

	/**
	 * @param opBankAcntNO
	 *            the opBankAcntNO to set
	 */
	public void setOpBankAcntNO(String opBankAcntNO) {
		this.opBankAcntNO = opBankAcntNO == null ? "" : opBankAcntNO;
	}

	/**
	 * @return the opBankName
	 */
	public String getOpBankName() {
		return opBankName;
	}

	/**
	 * @param opBankName
	 *            the opBankName to set
	 */
	public void setOpBankName(String opBankName) {
		this.opBankName = opBankName == null ? "" : opBankName;
	}

	/**
	 * @return the opBankAcntName
	 */
	public String getOpBankAcntName() {
		return opBankAcntName;
	}

	/**
	 * @param opBankAcntName
	 *            the opBankAcntName to set
	 */
	public void setOpBankAcntName(String opBankAcntName) {
		this.opBankAcntName = opBankAcntName == null ? "" : opBankAcntName;
	}

	/**
	 * @return the opBankProvince
	 */
	public String getOpBankProvince() {
		return opBankProvince;
	}

	/**
	 * @param opBankProvince
	 *            the opBankProvince to set
	 */
	public void setOpBankProvince(String opBankProvince) {
		this.opBankProvince = opBankProvince == null ? "" : opBankProvince;
	}

	/**
	 * @return the opBankCity
	 */
	public String getOpBankCity() {
		return opBankCity;
	}

	/**
	 * @param opBankCity
	 *            the opBankCity to set
	 */
	public void setOpBankCity(String opBankCity) {
		this.opBankCity = opBankCity == null ? "" : opBankCity;
	}

	/**
	 * @return the repaytypeid
	 */
	public String getRepayTypeID() {
		return repayTypeID;
	}

	/**
	 * @param repaytypeid
	 *            the repaytypeid to set
	 */
	public void setRepayTypeID(String repayTypeID) {
		this.repayTypeID = repayTypeID == null ? "" : repayTypeID;
	}

	/**
	 * @return the appUserCode
	 */
	public String getAppUserCode() {
		return appUserCode;
	}

	/**
	 * @param appUserCode
	 *            the appUserCode to set
	 */
	public void setAppUserCode(String appUserCode) {
		this.appUserCode = appUserCode == null ? "" : appUserCode;
	}

	/**
	 * @return the personnel
	 */
	public String getPersonnel() {
		return personnel;
	}

	/**
	 * @param personnel
	 *            the personnel to set
	 */
	public void setPersonnel(String personnel) {
		this.personnel = personnel == null ? "" : personnel;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project == null ? "" : project;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer == null ? "" : customer;
	}

	/**
	 * /**
	 * 
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry == null ? "" : industry;
	}

	/**
	 * @return the cashflow
	 */
	public String getCashFlow() {
		return cashFlow;
	}

	/**
	 * @param cashflow
	 *            the cashflow to set
	 */
	public void setCashFlow(String cashFlow) {
		this.cashFlow = cashFlow == null ? "" : cashFlow;
	}

	/**
	 * @return the virtualaccount
	 */
	public String getVirtualAccount() {
		return virtualAccount;
	}

	/**
	 * @param virtualaccount
	 *            the virtualaccount to set
	 */
	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount == null ? "" : virtualAccount;
	}

	/**
	 * @return the contractnumber
	 */
	public String getContractNumber() {
		return contractNumber;
	}

	/**
	 * @param contractnumber
	 *            the contractnumber to set
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber == null ? "" : contractNumber;
	}

	/**
	 * @return the stockcode
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockcode
	 *            the stockcode to set
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode == null ? "" : stockCode;
	}

	/**
	 * @return the fundaccount
	 */
	public String getFundAccount() {
		return fundAccount;
	}

	/**
	 * @param fundaccount
	 *            the fundaccount to set
	 */
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount == null ? "" : fundAccount;
	}

	/**
	 * @return the salesdepartment
	 */
	public String getSalesDepartment() {
		return salesDepartment;
	}

	/**
	 * @param salesdepartment
	 *            the salesdepartment to set
	 */
	public void setSalesDepartment(String salesDepartment) {
		this.salesDepartment = salesDepartment == null ? "" : salesDepartment;
	}

	/**
	 * @return the checknumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checknumber
	 *            the checknumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber == null ? "" : checkNumber;
	}

	/**
	 * @return the businesstype
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businesstype
	 *            the businesstype to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType == null ? "" : businessType;
	}

	/**
	 * @return the cashflowstatement
	 */
	public String getCashFlowStatement() {
		return cashFlowStatement;
	}

	/**
	 * @param cashflowstatement
	 *            the cashflowstatement to set
	 */
	public void setCashFlowStatement(String cashFlowStatement) {
		this.cashFlowStatement = cashFlowStatement == null ? "" : cashFlowStatement;
	}

	/**
	 * @return the taxcategory
	 */
	public String getTaxCategory() {
		return taxCategory;
	}

	/**
	 * @param taxcategory
	 *            the taxcategory to set
	 */
	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory == null ? "" : taxCategory;
	}

	/**
	 * @return the interestrate
	 */
	public String getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestrate
	 *            the interestrate to set
	 */
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate == null ? "" : interestRate;
	}

	/**
	 * @return the stock
	 */
	public String getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(String stock) {
		this.stock = stock == null ? "" : stock;
	}

	/**
	 * @return the assetname
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetname
	 *            the assetname to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName == null ? "" : assetName;
	}

	/**
	 * @return the productname
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productname
	 *            the productname to set
	 */
	public void setProductName(String productName) {
		this.productName = productName == null ? "" : productName;
	}

	/**
	 * @return the clientorbeneficiary
	 */
	public String getClientOrBeneficiary() {
		return clientOrBeneficiary;
	}

	/**
	 * @param clientorbeneficiary
	 *            the clientorbeneficiary to set
	 */
	public void setClientOrBeneficiary(String clientOrBeneficiary) {
		this.clientOrBeneficiary = clientOrBeneficiary == null ? "" : clientOrBeneficiary;
	}

	/**
	 * @return the shareholder
	 */
	public String getShareholder() {
		return shareholder;
	}

	/**
	 * @param shareholder
	 *            the shareholder to set
	 */
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder == null ? "" : shareholder;
	}

	/**
	 * @return the entrustcontractnumber
	 */
	public String getEntrustContractNumber() {
		return entrustContractNumber;
	}

	/**
	 * @param entrustcontractnumber
	 *            the entrustcontractnumber to set
	 */
	public void setEntrustContractNumber(String entrustContractNumber) {
		this.entrustContractNumber = entrustContractNumber == null ? "" : entrustContractNumber;
	}

	/**
	 * @return the tradingpurpose
	 */
	public String getTradingPurpose() {
		return tradingPurpose;
	}

	/**
	 * @param tradingpurpose
	 *            the tradingpurpose to set
	 */
	public void setTradingPurpose(String tradingPurpose) {
		this.tradingPurpose = tradingPurpose == null ? "" : tradingPurpose;
	}

	/**
	 * @return the contractvariety
	 */
	public String getContractVariety() {
		return contractVariety;
	}

	/**
	 * @param contractvariety
	 *            the contractvariety to set
	 */
	public void setContractVariety(String contractVariety) {
		this.contractVariety = contractVariety == null ? "" : contractVariety;
	}

	/**
	 * @return the dateofreceipt
	 */
	public String getDateOfReceipt() {
		return dateOfReceipt;
	}

	/**
	 * @param dateofreceipt
	 *            the dateofreceipt to set
	 */
	public void setDateOfReceipt(String dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt == null ? "" : dateOfReceipt;
	}

	/**
	 * @return the interestdate
	 */
	public String getInterestDate() {
		return interestDate;
	}

	/**
	 * @param interestdate
	 *            the interestdate to set
	 */
	public void setInterestDate(String interestDate) {
		this.interestDate = interestDate == null ? "" : interestDate;
	}

	/**
	 * @return the duedate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param duedate
	 *            the duedate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate == null ? "" : dueDate;
	}

	/**
	 * @return the inorout
	 */
	public String getInOrOut() {
		return inOrOut;
	}

	/**
	 * @param inorout
	 *            the inorout to set
	 */
	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut == null ? "" : inOrOut;
	}

	/**
	 * @return the billnumber
	 */
	public String getBillNumber() {
		return billNumber;
	}

	/**
	 * @param billnumber
	 *            the billnumber to set
	 */
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber == null ? "" : billNumber;
	}

}