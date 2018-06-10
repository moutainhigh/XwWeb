package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcLnMst.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcLnMst extends accounting.domain.sys.CMISDomain {
	private String loanNo;//借据号
	private String deductDay;//自动扣收本金天数
	private Double deductMinAmt;//自动扣收最小欠息金额
	private Double ttlPrvdAmt;//累计发放金额
	private Integer calTtlInstm;//实际计算期数
	private String overIntstTyp;//逾期结息方式
	private Double indpnAmt;//自主金额
	private Double trustAmt;//受托金额
	private Double trustAmtTrans;//受托金额支付交易累加
	private String oldLoanNo;//原借据号（借新还旧）
	private Double delqPrcpAmt;//拖欠本金
	private String pactNo;//合同号
	private String brNo;//机构号
	private String batchNo;//批次号
	private String prdtNo;//核算产品编号
	private String projNo;//项目编号
	private String brAccType;//机构核算类型[A/B]
	private String cifNo;//客户号
	private String cifName;//客户姓名
	private String loanTyp;//贷款种类
	private String assureMeans;//担保方式
	private Integer loanTerm;//贷款期限
	private String opnDt;//开户日期
	private String mtrDt;//到期日期
	private String termTyp;//期限类型
	private Integer termMon;//term_mon
	private Integer termDay;//term_day
	private String curNo;//币种
	private Double loanAmt;//贷款限额
	private Double loanBal;//余额
	private Double lnRate;//月利率
	private Double lnRateYear;//年利率
	private Double overRate;//逾期利率
	private String icDt;//起息日期
	private String repayDay;//还款日
	private String expInd;//展期控制标志
	private Double expRate;//展期利率
	private String expMtrDt;//展期到期日
	private String graceTyp;//宽限期类型
	private Integer graceDay;//宽限期限(天)
	private String delqInd;//拖欠标识
	private String devaInd;//减值标识
	private String dealSts;//处理标志
	private String failCaus;//失败原因
	private String loanSts;//贷款状态
	private String intToStpInd;//停罚息标志
	private String fiveSts;//五级分类状态
	private String lastSetlDt;//上一次扣款日
	private String curDueDt;//当前归还日期
	private String nextDueDt;//下一次还款日
	private String lstIntDt;//批量结息日期
	private Integer hstCnt;//明细笔数
	private String lstDt;//上笔发生日期
	private Double ysBal;//昨日余额
	private Double hstBal;//明细余额
	private String mac;//密押
	private String sailFlag;//sail_flag
	private String sailDt;//结清标志
	private Double zfBal;//zf_bal
	private Double deductBegAmt;//自动扣收本金起点金额
	private String upDt;//更新日期
	private String premRate;//保费费率
	
	public Double getLnRateYear() {
		return lnRateYear;
	}
	public void setLnRateYear(Double lnRateYear) {
		this.lnRateYear = lnRateYear;
	}
	/**
	 * @return 借据号
	 */
	public String getLoanNo() {
	 	return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
	 	this.loanNo = loanNo;
	}
	/**
	 * @return 自动扣收本金天数
	 */
	public String getDeductDay() {
	 	return deductDay;
	}
	/**
	 * @设置 自动扣收本金天数
	 * @param deductDay
	 */
	public void setDeductDay(String deductDay) {
	 	this.deductDay = deductDay;
	}
	/**
	 * @return 自动扣收最小欠息金额
	 */
	public Double getDeductMinAmt() {
	 	return deductMinAmt;
	}
	/**
	 * @设置 自动扣收最小欠息金额
	 * @param deductMinAmt
	 */
	public void setDeductMinAmt(Double deductMinAmt) {
	 	this.deductMinAmt = deductMinAmt;
	}
	/**
	 * @return 累计发放金额
	 */
	public Double getTtlPrvdAmt() {
	 	return ttlPrvdAmt;
	}
	/**
	 * @设置 累计发放金额
	 * @param ttlPrvdAmt
	 */
	public void setTtlPrvdAmt(Double ttlPrvdAmt) {
	 	this.ttlPrvdAmt = ttlPrvdAmt;
	}
	/**
	 * @return 实际计算期数
	 */
	public Integer getCalTtlInstm() {
	 	return calTtlInstm;
	}
	/**
	 * @设置 实际计算期数
	 * @param calTtlInstm
	 */
	public void setCalTtlInstm(Integer calTtlInstm) {
	 	this.calTtlInstm = calTtlInstm;
	}
	/**
	 * @return 逾期结息方式
	 */
	public String getOverIntstTyp() {
	 	return overIntstTyp;
	}
	/**
	 * @设置 逾期结息方式
	 * @param overIntstTyp
	 */
	public void setOverIntstTyp(String overIntstTyp) {
	 	this.overIntstTyp = overIntstTyp;
	}
	/**
	 * @return 自主金额
	 */
	public Double getIndpnAmt() {
	 	return indpnAmt;
	}
	/**
	 * @设置 自主金额
	 * @param indpnAmt
	 */
	public void setIndpnAmt(Double indpnAmt) {
	 	this.indpnAmt = indpnAmt;
	}
	/**
	 * @return 受托金额
	 */
	public Double getTrustAmt() {
	 	return trustAmt;
	}
	/**
	 * @设置 受托金额
	 * @param trustAmt
	 */
	public void setTrustAmt(Double trustAmt) {
	 	this.trustAmt = trustAmt;
	}
	/**
	 * @return 受托金额支付交易累加
	 */
	public Double getTrustAmtTrans() {
	 	return trustAmtTrans;
	}
	/**
	 * @设置 受托金额支付交易累加
	 * @param trustAmtTrans
	 */
	public void setTrustAmtTrans(Double trustAmtTrans) {
	 	this.trustAmtTrans = trustAmtTrans;
	}
	/**
	 * @return 原借据号（借新还旧）
	 */
	public String getOldLoanNo() {
	 	return oldLoanNo;
	}
	/**
	 * @设置 原借据号（借新还旧）
	 * @param oldLoanNo
	 */
	public void setOldLoanNo(String oldLoanNo) {
	 	this.oldLoanNo = oldLoanNo;
	}
	/**
	 * @return 拖欠本金
	 */
	public Double getDelqPrcpAmt() {
	 	return delqPrcpAmt;
	}
	/**
	 * @设置 拖欠本金
	 * @param delqPrcpAmt
	 */
	public void setDelqPrcpAmt(Double delqPrcpAmt) {
	 	this.delqPrcpAmt = delqPrcpAmt;
	}
	/**
	 * @return 合同号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 批次号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
	 	this.batchNo = batchNo;
	}
	/**
	 * @return 核算产品编号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 核算产品编号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 机构核算类型[A/B]
	 */
	public String getBrAccType() {
	 	return brAccType;
	}
	/**
	 * @设置 机构核算类型[A/B]
	 * @param brAccType
	 */
	public void setBrAccType(String brAccType) {
	 	this.brAccType = brAccType;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 客户姓名
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户姓名
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 贷款种类
	 */
	public String getLoanTyp() {
	 	return loanTyp;
	}
	/**
	 * @设置 贷款种类
	 * @param loanTyp
	 */
	public void setLoanTyp(String loanTyp) {
	 	this.loanTyp = loanTyp;
	}
	/**
	 * @return 担保方式
	 */
	public String getAssureMeans() {
	 	return assureMeans;
	}
	/**
	 * @设置 担保方式
	 * @param assureMeans
	 */
	public void setAssureMeans(String assureMeans) {
	 	this.assureMeans = assureMeans;
	}
	/**
	 * @return 贷款期限
	 */
	public Integer getLoanTerm() {
	 	return loanTerm;
	}
	/**
	 * @设置 贷款期限
	 * @param loanTerm
	 */
	public void setLoanTerm(Integer loanTerm) {
	 	this.loanTerm = loanTerm;
	}
	/**
	 * @return 开户日期
	 */
	public String getOpnDt() {
	 	return opnDt;
	}
	/**
	 * @设置 开户日期
	 * @param opnDt
	 */
	public void setOpnDt(String opnDt) {
	 	this.opnDt = opnDt;
	}
	/**
	 * @return 到期日期
	 */
	public String getMtrDt() {
	 	return mtrDt;
	}
	/**
	 * @设置 到期日期
	 * @param mtrDt
	 */
	public void setMtrDt(String mtrDt) {
	 	this.mtrDt = mtrDt;
	}
	/**
	 * @return 期限类型
	 */
	public String getTermTyp() {
	 	return termTyp;
	}
	/**
	 * @设置 期限类型
	 * @param termTyp
	 */
	public void setTermTyp(String termTyp) {
	 	this.termTyp = termTyp;
	}
	/**
	 * @return term_mon
	 */
	public Integer getTermMon() {
	 	return termMon;
	}
	/**
	 * @设置 term_mon
	 * @param termMon
	 */
	public void setTermMon(Integer termMon) {
	 	this.termMon = termMon;
	}
	/**
	 * @return term_day
	 */
	public Integer getTermDay() {
	 	return termDay;
	}
	/**
	 * @设置 term_day
	 * @param termDay
	 */
	public void setTermDay(Integer termDay) {
	 	this.termDay = termDay;
	}
	/**
	 * @return 币种
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
	/**
	 * @return 贷款限额
	 */
	public Double getLoanAmt() {
	 	return loanAmt;
	}
	/**
	 * @设置 贷款限额
	 * @param loanAmt
	 */
	public void setLoanAmt(Double loanAmt) {
	 	this.loanAmt = loanAmt;
	}
	/**
	 * @return 余额
	 */
	public Double getLoanBal() {
	 	return loanBal;
	}
	/**
	 * @设置 余额
	 * @param loanBal
	 */
	public void setLoanBal(Double loanBal) {
	 	this.loanBal = loanBal;
	}
	/**
	 * @return 月利率
	 */
	public Double getLnRate() {
	 	return lnRate;
	}
	/**
	 * @设置 月利率
	 * @param lnRate
	 */
	public void setLnRate(Double lnRate) {
	 	this.lnRate = lnRate;
	}
	/**
	 * @return 逾期利率
	 */
	public Double getOverRate() {
	 	return overRate;
	}
	/**
	 * @设置 逾期利率
	 * @param overRate
	 */
	public void setOverRate(Double overRate) {
	 	this.overRate = overRate;
	}
	/**
	 * @return 起息日期
	 */
	public String getIcDt() {
	 	return icDt;
	}
	/**
	 * @设置 起息日期
	 * @param icDt
	 */
	public void setIcDt(String icDt) {
	 	this.icDt = icDt;
	}
	/**
	 * @return 还款日
	 */
	public String getRepayDay() {
	 	return repayDay;
	}
	/**
	 * @设置 还款日
	 * @param repayDay
	 */
	public void setRepayDay(String repayDay) {
	 	this.repayDay = repayDay;
	}
	/**
	 * @return 展期控制标志
	 */
	public String getExpInd() {
	 	return expInd;
	}
	/**
	 * @设置 展期控制标志
	 * @param expInd
	 */
	public void setExpInd(String expInd) {
	 	this.expInd = expInd;
	}
	/**
	 * @return 展期利率
	 */
	public Double getExpRate() {
	 	return expRate;
	}
	/**
	 * @设置 展期利率
	 * @param expRate
	 */
	public void setExpRate(Double expRate) {
	 	this.expRate = expRate;
	}
	/**
	 * @return 展期到期日
	 */
	public String getExpMtrDt() {
	 	return expMtrDt;
	}
	/**
	 * @设置 展期到期日
	 * @param expMtrDt
	 */
	public void setExpMtrDt(String expMtrDt) {
	 	this.expMtrDt = expMtrDt;
	}
	/**
	 * @return 宽限期类型
	 */
	public String getGraceTyp() {
	 	return graceTyp;
	}
	/**
	 * @设置 宽限期类型
	 * @param graceTyp
	 */
	public void setGraceTyp(String graceTyp) {
	 	this.graceTyp = graceTyp;
	}
	/**
	 * @return 宽限期限(天)
	 */
	public Integer getGraceDay() {
	 	return graceDay;
	}
	/**
	 * @设置 宽限期限(天)
	 * @param graceDay
	 */
	public void setGraceDay(Integer graceDay) {
	 	this.graceDay = graceDay;
	}
	/**
	 * @return 拖欠标识
	 */
	public String getDelqInd() {
	 	return delqInd;
	}
	/**
	 * @设置 拖欠标识
	 * @param delqInd
	 */
	public void setDelqInd(String delqInd) {
	 	this.delqInd = delqInd;
	}
	/**
	 * @return 减值标识
	 */
	public String getDevaInd() {
	 	return devaInd;
	}
	/**
	 * @设置 减值标识
	 * @param devaInd
	 */
	public void setDevaInd(String devaInd) {
	 	this.devaInd = devaInd;
	}
	/**
	 * @return 贷款状态
	 */
	public String getLoanSts() {
	 	return loanSts;
	}
	/**
	 * @设置 贷款状态
	 * @param loanSts
	 */
	public void setLoanSts(String loanSts) {
	 	this.loanSts = loanSts;
	}
	/**
	 * @return 停罚息标志
	 */
	public String getIntToStpInd() {
	 	return intToStpInd;
	}
	/**
	 * @设置 停罚息标志
	 * @param intToStpInd
	 */
	public void setIntToStpInd(String intToStpInd) {
	 	this.intToStpInd = intToStpInd;
	}
	/**
	 * @return 五级分类状态
	 */
	public String getFiveSts() {
	 	return fiveSts;
	}
	/**
	 * @设置 五级分类状态
	 * @param fiveSts
	 */
	public void setFiveSts(String fiveSts) {
	 	this.fiveSts = fiveSts;
	}
	/**
	 * @return 上一次扣款日
	 */
	public String getLastSetlDt() {
	 	return lastSetlDt;
	}
	/**
	 * @设置 上一次扣款日
	 * @param lastSetlDt
	 */
	public void setLastSetlDt(String lastSetlDt) {
	 	this.lastSetlDt = lastSetlDt;
	}
	/**
	 * @return 当前归还日期
	 */
	public String getCurDueDt() {
	 	return curDueDt;
	}
	/**
	 * @设置 当前归还日期
	 * @param curDueDt
	 */
	public void setCurDueDt(String curDueDt) {
	 	this.curDueDt = curDueDt;
	}
	/**
	 * @return 下一次还款日
	 */
	public String getNextDueDt() {
	 	return nextDueDt;
	}
	/**
	 * @设置 下一次还款日
	 * @param nextDueDt
	 */
	public void setNextDueDt(String nextDueDt) {
	 	this.nextDueDt = nextDueDt;
	}
	/**
	 * @return 批量结息日期
	 */
	public String getLstIntDt() {
	 	return lstIntDt;
	}
	/**
	 * @设置 批量结息日期
	 * @param lstIntDt
	 */
	public void setLstIntDt(String lstIntDt) {
	 	this.lstIntDt = lstIntDt;
	}
	/**
	 * @return 明细笔数
	 */
	public Integer getHstCnt() {
	 	return hstCnt;
	}
	/**
	 * @设置 明细笔数
	 * @param hstCnt
	 */
	public void setHstCnt(Integer hstCnt) {
	 	this.hstCnt = hstCnt;
	}
	/**
	 * @return 上笔发生日期
	 */
	public String getLstDt() {
	 	return lstDt;
	}
	/**
	 * @设置 上笔发生日期
	 * @param lstDt
	 */
	public void setLstDt(String lstDt) {
	 	this.lstDt = lstDt;
	}
	/**
	 * @return 昨日余额
	 */
	public Double getYsBal() {
	 	return ysBal;
	}
	/**
	 * @设置 昨日余额
	 * @param ysBal
	 */
	public void setYsBal(Double ysBal) {
	 	this.ysBal = ysBal;
	}
	/**
	 * @return 明细余额
	 */
	public Double getHstBal() {
	 	return hstBal;
	}
	/**
	 * @设置 明细余额
	 * @param hstBal
	 */
	public void setHstBal(Double hstBal) {
	 	this.hstBal = hstBal;
	}
	/**
	 * @return 密押
	 */
	public String getMac() {
	 	return mac;
	}
	/**
	 * @设置 密押
	 * @param mac
	 */
	public void setMac(String mac) {
	 	this.mac = mac;
	}
	/**
	 * @return sail_flag
	 */
	public String getSailFlag() {
	 	return sailFlag;
	}
	/**
	 * @设置 sail_flag
	 * @param sailFlag
	 */
	public void setSailFlag(String sailFlag) {
	 	this.sailFlag = sailFlag;
	}
	/**
	 * @return sail_dt
	 */
	public String getSailDt() {
	 	return sailDt;
	}
	/**
	 * @设置 sail_dt
	 * @param sailDt
	 */
	public void setSailDt(String sailDt) {
	 	this.sailDt = sailDt;
	}
	/**
	 * @return zf_bal
	 */
	public Double getZfBal() {
	 	return zfBal;
	}
	/**
	 * @设置 zf_bal
	 * @param zfBal
	 */
	public void setZfBal(Double zfBal) {
	 	this.zfBal = zfBal;
	}
	/**
	 * @return 自动扣收本金起点金额
	 */
	public Double getDeductBegAmt() {
	 	return deductBegAmt;
	}
	/**
	 * @设置 自动扣收本金起点金额
	 * @param deductBegAmt
	 */
	public void setDeductBegAmt(Double deductBegAmt) {
	 	this.deductBegAmt = deductBegAmt;
	}
	public String getDealSts() {
		return dealSts;
	}
	public void setDealSts(String dealSts) {
		this.dealSts = dealSts;
	}
	public String getUpDt() {
		return upDt;
	}
	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getFailCaus() {
		return failCaus;
	}
	public void setFailCaus(String failCaus) {
		this.failCaus = failCaus;
	}
	public String getPremRate() {
		return premRate;
	}
	public void setPremRate(String premRate) {
		this.premRate = premRate;
	}
	
}