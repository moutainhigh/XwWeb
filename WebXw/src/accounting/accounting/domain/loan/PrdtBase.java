package accounting.domain.loan;

import app.base.BaseDomain;

/**
 * Title: PrdtBase.java Description:
 * 
* @作者 su
* @日期 2018-3-20
 * @version：1.0
 **/
public class PrdtBase extends accounting.domain.sys.CMISDomain {
	private String prdtId;// 产品编号
	private String prdtNo;// 产品编号
	private String brNo;// 合作机构号
	private String prdtName;// 产品名称
	private String prdtType;// 产品归类[01经营类02消费类]
	private Double minAmt;// 金额下限
	private Double maxAmt;// 金额上限
	private Integer minMon;// 期限下限(月)
	private Integer maxMon;// 期限上限(月)
	private String repayNo;// 还款方案编号
	private String defNo;// 罚息公式编号
	private String damNo;// 违约金公式编号
	private Double maxRate;// 最大年利率
	private Double overRate;// 逾期日利率
	private String feeNo;// 代收费编号@分割
	private String prdtDesc;// 产品说明
	private Integer prdtVer;// 版本号
	private String prdtSts;// 产品状态
	private String opNo;// 登记人
	private String txDate;// 登记日期
	private String upDate;// 更新日期
	private String brAccType;// 核算类型
	private String allowArea;

	private Double offRate;// 折扣率
	private Double minRate;// 最小月利率
	private String endDate;// 到期日期
	private Integer failDays;//扣失最大天数[默认为30天]N天之后不再自动扣款扣失最大天数[默认为30天]N天之后不再自动扣款
	
	private String repaySeqCde;//还款顺序配置
	
	public String getRepaySeqCde() {
		return repaySeqCde;
	}

	public void setRepaySeqCde(String repaySeqCde) {
		this.repaySeqCde = repaySeqCde;
	}

	public PrdtBase() {
		offRate = 1.0d;//折扣率 默认值 1
		prdtVer = 1; //版本号 默认值 1
	}

	/**
	 * @return 产品编号
	 */
	public String getPrdtNo() {
		return prdtNo;
	}

	/**
	 * @设置 产品编号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}

	/**
	 * @return 合作机构号
	 */
	public String getBrNo() {
		return brNo;
	}

	/**
	 * @设置 合作机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	/**
	 * @return 产品名称
	 */
	public String getPrdtName() {
		return prdtName;
	}

	/**
	 * @设置 产品名称
	 * @param prdtName
	 */
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}

	/**
	 * @return 产品归类[01经营类02消费类]
	 */
	public String getPrdtType() {
		return prdtType;
	}

	/**
	 * @设置 产品归类[01经营类02消费类]
	 * @param prdtType
	 */
	public void setPrdtType(String prdtType) {
		this.prdtType = prdtType;
	}

	/**
	 * @return 金额下限
	 */
	public Double getMinAmt() {
		return minAmt;
	}

	/**
	 * @设置 金额下限
	 * @param minAmt
	 */
	public void setMinAmt(Double minAmt) {
		this.minAmt = minAmt;
	}

	/**
	 * @return 金额上限
	 */
	public Double getMaxAmt() {
		return maxAmt;
	}

	/**
	 * @设置 金额上限
	 * @param maxAmt
	 */
	public void setMaxAmt(Double maxAmt) {
		this.maxAmt = maxAmt;
	}

	/**
	 * @return 期限下限(月)
	 */
	public Integer getMinMon() {
		return minMon;
	}

	/**
	 * @设置 期限下限(月)
	 * @param minMon
	 */
	public void setMinMon(Integer minMon) {
		this.minMon = minMon;
	}

	/**
	 * @return 期限上限(月)
	 */
	public Integer getMaxMon() {
		return maxMon;
	}

	/**
	 * @设置 期限上限(月)
	 * @param maxMon
	 */
	public void setMaxMon(Integer maxMon) {
		this.maxMon = maxMon;
	}

	/**
	 * @return 产品说明
	 */
	public String getPrdtDesc() {
		return prdtDesc;
	}

	/**
	 * @设置 产品说明
	 * @param prdtDesc
	 */
	public void setPrdtDesc(String prdtDesc) {
		this.prdtDesc = prdtDesc;
	}

	/**
	 * @return 版本号
	 */
	public Integer getPrdtVer() {
		return prdtVer;
	}

	/**
	 * @设置 版本号
	 * @param prdtVer
	 */
	public void setPrdtVer(Integer prdtVer) {
		this.prdtVer = prdtVer;
	}

	/**
	 * @return 产品状态
	 */
	public String getPrdtSts() {
		return prdtSts;
	}

	/**
	 * @设置 产品状态
	 * @param prdtSts
	 */
	public void setPrdtSts(String prdtSts) {
		this.prdtSts = prdtSts;
	}

	/**
	 * @return 登记人
	 */
	public String getOpNo() {
		return opNo;
	}

	/**
	 * @设置 登记人
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
		this.opNo = opNo;
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
	 * @return 更新日期
	 */
	public String getUpDate() {
		return upDate;
	}

	/**
	 * @设置 更新日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getPrdtId() {
		return prdtId;
	}

	public void setPrdtId(String prdtId) {
		this.prdtId = prdtId;
	}

	public String getRepayNo() {
		return repayNo;
	}

	public void setRepayNo(String repayNo) {
		this.repayNo = repayNo;
	}

	public String getDefNo() {
		return defNo;
	}

	public void setDefNo(String defNo) {
		this.defNo = defNo;
	}

	public String getDamNo() {
		return damNo;
	}

	public void setDamNo(String damNo) {
		this.damNo = damNo;
	}

	public Double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}

	public Double getOverRate() {
		return overRate;
	}

	public void setOverRate(Double overRate) {
		this.overRate = overRate;
	}

	public String getFeeNo() {
		return feeNo;
	}

	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
	}


	public String getBrAccType() {
		return brAccType;
	}

	public void setBrAccType(String brAccType) {
		this.brAccType = brAccType;
	}

	public Double getOffRate() {
		return offRate;
	}

	public void setOffRate(Double offRate) {
		this.offRate = offRate;
	}

	public Double getMinRate() {
		return minRate;
	}

	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getFailDays() {
		return failDays;
	}

	public void setFailDays(Integer failDays) {
		this.failDays = failDays;
	}

	public String getAllowArea() {
		return allowArea;
	}

	public void setAllowArea(String allowArea) {
		this.allowArea = allowArea;
	}
	
}