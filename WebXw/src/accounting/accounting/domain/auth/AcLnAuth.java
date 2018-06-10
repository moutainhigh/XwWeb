package accounting.domain.auth;
/**
* Title: AcLnAuth.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcLnAuth extends AcAuth implements java.io.Serializable {
	private double indpnAmt;                //自主金额
	private double trustAmt;                //受托金额
	private String sbsyInd;                 //贴息贷款标志
	private String authNo;                  //授权编号
	private String prdtNoCrd;               //信贷产品编号
	private String loanTyp;                 //贷款类型
	private String contNo;              	//合同号
	private String loanNo;                  //借据号
	private String cifNo;                   //客户号
	private String cifName;                 //客户名称
	private int    loanTerm;                //贷款期限
	private int    loanTermDay;             //贷款期限
	private String begDt;                   //开始日期
	private String endDt;                   //到期日期
	private String curNo;                   //币种
	private double loanAmt;                 //贷款金额
	private String rateNo;                  //利率编号
	private double rateFlt;                 //利率浮动比例
	private double rate;                    //合同利率
	private double overRateFlt;             //逾期利率浮动比例
	private double overRate;                //逾期利率
	private double fineRateFlt;             //罚息利率浮动比例
	private double fineRate;                //罚息利率
	private double cmpdRate;                //复利利率
	private double actRate;                 //实际利率
	private String assureMeans;             //主要担保方式
	private String repayTermTyp;            //还款周期类型
	private int    repayTerm;               //还本周期
	private String rateMode;                //利率模式
	private String rateAdjTyp;              //利率调整方式
	private String disbAcNo;                //存款账号
	
	private String repayTyp;                //还款方式
	private String repayDayMode;            //还款日方式
	private String repayDay;                //指定还款日
	private String trustNo;                 //委托协议编号
	private String repayAdvOpt;             //提前还款选项
	private double feeAmt;                  //手续费
	private String multRepayInd;            //多阶段还款方式标识
	private int    pgsBegCnt;                  //递增递减开始期数
	private int    pgsPrcpFreq;             //递增递减间隔期数
	private double pgsPrcpAmt;              //递增递减金额
	private double pgsPrcpPct;              //递增递减比例
	private int    calTtlInstm;             //实际计算期数
	private int    plnAdjPerd ;				//还款计划变更开始期数
	private String setlMode;				//提前还款方式
	
	private String repayAcNoFst;               	//第一还款账号
	private String repayAcNoSnd;			  	//第二还款账号
	private String bailAcNo;					//保证金账号
	private Double bailAmt;						//保证金金额
	private Double bailBal;						//保证金余额
	private String depositAcNo;					//指定存款账号(欢心宝)
	private Double depositAmt;					//指定存款账号余额
	private Double normRateFlt;					//原正常利率浮动比
	private String oldLoanNo;					//原借据号（借新还旧）
	
	//20151123新增字段
	private String rateKind;					//利率类型
	private String preferRateInd;				//优惠利率标志（欢心宝）
	private String yLoanBail;					//是否自动保证金账户扣款 1-是 0-否
	private String fltType;						//浮动方式  1-浮动点 0-浮动比
	private double rateFltCount;				//浮动点数
	private String intstTermType;				//结息周期
	
	//数据来源渠道
	private String acSource;
	private String cifTyp;
	private String idTyp;
	private String idNo;
	
	private int graceDay;	//宽延期天数
	
	/**
	 * @return 自主金额
	 */
	public double getIndpnAmt() {
		return indpnAmt;
	}
	/**
	 * @param 自主金额
	 */
	public void setIndpnAmt(double indpnAmt) {
		this.indpnAmt = indpnAmt;
	}
	/**
	 * @return 受托金额
	 */
	public double getTrustAmt() {
		return trustAmt;
	}
	/**
	 * @param 受托金额
	 */
	public void setTrustAmt(double trustAmt) {
		this.trustAmt = trustAmt;
	}
	/**
	 * @return 贴息标志
	 */
	public String getSbsyInd() {
		return sbsyInd;
	}
	/**
	 * @param 贴息标志
	 */
	public void setSbsyInd(String sbsyInd) {
		this.sbsyInd = sbsyInd;
	}
	/**
	 * @return 授权编号
	 */
	public String getAuthNo() {
		return authNo;
	}
	/**
	 * @param 授权编号
	 */
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	/**
	 * @return 信贷产品编号
	 */
	public String getPrdtNoCrd() {
		return prdtNoCrd;
	}
	/**
	 * @param 信贷产品编号
	 */
	public void setPrdtNoCrd(String prdtNoCrd) {
		this.prdtNoCrd = prdtNoCrd;
	}
	/**
	 * @return 贷款类型
	 */
	public String getLoanTyp() {
		return loanTyp;
	}
	/**
	 * @param 贷款类型
	 */
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}
	/**
	 * @return 合同号
	 */
	public String getContNo() {
		return contNo;
	}
	/**
	 * @param 合同号
	 */
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	/**
	 * @return 借据号
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * @param 借据号
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
		return cifNo;
	}
	/**
	 * @param 客户号
	 */
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	/**
	 * @return 贷款期限
	 */
	public int getLoanTerm() {
		return loanTerm;
	}
	/**
	 * @param 贷款期限
	 */
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	/**
	 * @return 开始日期
	 */
	public String getBegDt() {
		return begDt;
	}
	/**
	 * @param 开始日期
	 */
	public void setBegDt(String begDt) {
		this.begDt = begDt;
	}
	/**
	 * @return 到期日期
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * @param 到期日期
	 */
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	/**
	 * @return 币种
	 */
	public String getCurNo() {
		return curNo;
	}
	/**
	 * @param 币种
	 */
	public void setCurNo(String curNo) {
		this.curNo = curNo;
	}
	/**
	 * @return 贷款金额
	 */
	public double getLoanAmt() {
		return loanAmt;
	}
	/**
	 * @param 贷款金额
	 */
	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}
	/**
	 * @return 利率编号
	 */
	public String getRateNo() {
		return rateNo;
	}
	/**
	 * @param 利率编号
	 */
	public void setRateNo(String rateNo) {
		this.rateNo = rateNo;
	}
	/**
	 * @return 利率浮动比例
	 */
	public double getRateFlt() {
		return rateFlt;
	}
	/**
	 * @param 利率浮动比例
	 */
	public void setRateFlt(double rateFlt) {
		this.rateFlt = rateFlt;
	}
	/**
	 * @return 合同利率
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param 合同利率
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return 逾期利率浮动比例
	 */
	public double getOverRateFlt() {
		return overRateFlt;
	}
	/**
	 * @param 逾期利率浮动比例
	 */
	public void setOverRateFlt(double overRateFlt) {
		this.overRateFlt = overRateFlt;
	}
	/**
	 * @return 逾期利率
	 */
	public double getOverRate() {
		return overRate;
	}
	/**
	 * @param 逾期利率
	 */
	public void setOverRate(double overRate) {
		this.overRate = overRate;
	}
	/**
	 * @return 罚息利率浮动比例
	 */
	public double getFineRateFlt() {
		return fineRateFlt;
	}
	/**
	 * @param 罚息利率浮动比例
	 */
	public void setFineRateFlt(double fineRateFlt) {
		this.fineRateFlt = fineRateFlt;
	}
	/**
	 * @return 罚息利率
	 */
	public double getFineRate() {
		return fineRate;
	}
	/**
	 * @param 罚息利率
	 */
	public void setFineRate(double fineRate) {
		this.fineRate = fineRate;
	}
	/**
	 * @return 复利利率
	 */
	public double getCmpdRate() {
		return cmpdRate;
	}
	/**
	 * @param 复利利率
	 */
	public void setCmpdRate(double cmpdRate) {
		this.cmpdRate = cmpdRate;
	}
	/**
	 * @return 实际利率
	 */
	public double getActRate() {
		return actRate;
	}
	/**
	 * @param 实际利率
	 */
	public void setActRate(double actRate) {
		this.actRate = actRate;
	}
	/**
	 * @return 主要担保方式
	 */
	public String getAssureMeans() {
		return assureMeans;
	}
	/**
	 * @param 主要担保方式
	 */
	public void setAssureMeans(String assureMeans) {
		this.assureMeans = assureMeans;
	}
	/**
	 * @return 还款周期类型
	 */
	public String getRepayTermTyp() {
		return repayTermTyp;
	}
	/**
	 * @param 还款周期类型
	 */
	public void setRepayTermTyp(String repayTermTyp) {
		this.repayTermTyp = repayTermTyp;
	}
	/**
	 * @return 还本周期
	 */
	public int getRepayTerm() {
		return repayTerm;
	}
	/**
	 * @param 还本周期
	 */
	public void setRepayTerm(int repayTerm) {
		this.repayTerm = repayTerm;
	}
	/**
	 * @return 利率模式
	 */
	public String getRateMode() {
		return rateMode;
	}
	/**
	 * @param 利率模式
	 */
	public void setRateMode(String rateMode) {
		this.rateMode = rateMode;
	}
	/**
	 * @return 利率调整方式
	 */
	public String getRateAdjTyp() {
		return rateAdjTyp;
	}
	/**
	 * @param 利率调整方式
	 */
	public void setRateAdjTyp(String rateAdjTyp) {
		this.rateAdjTyp = rateAdjTyp;
	}
	/**
	 * @return 存款账号
	 */
	public String getDisbAcNo() {
		return disbAcNo;
	}
	/**
	 * @param 存款账号
	 */
	public void setDisbAcNo(String disbAcNo) {
		this.disbAcNo = disbAcNo;
	}
	/**
	 * @return 还款方式
	 */
	public String getRepayTyp() {
		return repayTyp;
	}
	/**
	 * @param 还款方式
	 */
	public void setRepayTyp(String repayTyp) {
		this.repayTyp = repayTyp;
	}
	/**
	 * @return 还款日方式
	 */
	public String getRepayDayMode() {
		return repayDayMode;
	}
	/**
	 * @param 还款日方式
	 */
	public void setRepayDayMode(String repayDayMode) {
		this.repayDayMode = repayDayMode;
	}
	/**
	 * @return 指定还款日
	 */
	public String getRepayDay() {
		return repayDay;
	}
	/**
	 * @param 指定还款日
	 */
	public void setRepayDay(String repayDay) {
		this.repayDay = repayDay;
	}
	/**
	 * @return 委托协议编号
	 */
	public String getTrustNo() {
		return trustNo;
	}
	/**
	 * @param 委托协议编号
	 */
	public void setTrustNo(String trustNo) {
		this.trustNo = trustNo;
	}
	/**
	 * @return 提前还款选项
	 */
	public String getRepayAdvOpt() {
		return repayAdvOpt;
	}
	/**
	 * @param 提前还款选项
	 */
	public void setRepayAdvOpt(String repayAdvOpt) {
		this.repayAdvOpt = repayAdvOpt;
	}
	/**
	 * @return 手续费
	 */
	public double getFeeAmt() {
		return feeAmt;
	}
	/**
	 * @param 手续费
	 */
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}
	/**
	 * @return 多阶段还款方式标识
	 */
	public String getMultRepayInd() {
		return multRepayInd;
	}
	/**
	 * @param 多阶段还款方式标识
	 */
	public void setMultRepayInd(String multRepayInd) {
		this.multRepayInd = multRepayInd;
	}
	/**
	 * @return 递增递减开始期数
	 */
	public int getPgsBegCnt() {
		return pgsBegCnt;
	}
	/**
	 * @param 递增递减开始期数
	 */
	public void setPgsBegCnt(int pgsBegCnt) {
		this.pgsBegCnt = pgsBegCnt;
	}
	/**
	 * @return 递增递减间隔期数
	 */
	public int getPgsPrcpFreq() {
		return pgsPrcpFreq;
	}
	/**
	 * @param 递增递减间隔期数
	 */
	public void setPgsPrcpFreq(int pgsPrcpFreq) {
		this.pgsPrcpFreq = pgsPrcpFreq;
	}
	/**
	 * @return 递增递减金额
	 */
	public double getPgsPrcpAmt() {
		return pgsPrcpAmt;
	}
	/**
	 * @param 递增递减金额
	 */
	public void setPgsPrcpAmt(double pgsPrcpAmt) {
		this.pgsPrcpAmt = pgsPrcpAmt;
	}
	/**
	 * @return 递增递减比例
	 */
	public double getPgsPrcpPct() {
		return pgsPrcpPct;
	}
	/**
	 * @param 递增递减比例
	 */
	public void setPgsPrcpPct(double pgsPrcpPct) {
		this.pgsPrcpPct = pgsPrcpPct;
	}
	/**
	 * @return 实际计算期数
	 */
	public int getCalTtlInstm() {
		return calTtlInstm;
	}
	/**
	 * @param 实际计算期数
	 */
	public void setCalTtlInstm(int calTtlInstm) {
		this.calTtlInstm = calTtlInstm;
	}
	/**
	 * @return 开始还款期数
	 */
	public int getPlnAdjPerd() {
		return plnAdjPerd;
	}
	/**
	 * @param 开始还款期数
	 */
	public void setPlnAdjPerd(int plnAdjPerd) {
		this.plnAdjPerd = plnAdjPerd;
	}
	/**
	 * @return 提前还款模式
	 */
	public String getSetlMode() {
		return setlMode;
	}
	/**
	 * @param 提前还款模式
	 */
	public void setSetlMode(String setlMode) {
		this.setlMode = setlMode;
	}
	/**
	 * 
	 * @return 第一还款账户
	 */
	public String getRepayAcNoFst() {
		return repayAcNoFst;
	}
	/**
	 * @设置 第一还款账户
	 * @param repayAcNo1st
	 */
	public void setRepayAcNoFst(String repayAcNoFst) {
		this.repayAcNoFst = repayAcNoFst;
	}
	/**
	 * @return 第二还款账户
	 */
	public String getRepayAcNoSnd() {
		return repayAcNoSnd;
	}
	/**
	 * @设置 第二还款账户
	 * @param repayAcNo2nd
	 */
	public void setRepayAcNoSnd(String repayAcNoSnd) {
		this.repayAcNoSnd = repayAcNoSnd;
	}
	/**
	 * @return 保证金账户
	 */
	public String getBailAcNo() {
		return bailAcNo;
	}
	/**
	 * @设置 保证金账户
	 * @param bailAcNo
	 */
	public void setBailAcNo(String bailAcNo) {
		this.bailAcNo = bailAcNo;
	}
	public Double getBailAmt() {
		return bailAmt;
	}
	public void setBailAmt(Double bailAmt) {
		this.bailAmt = bailAmt;
	}
	/**
	 * 存款账号（欢心宝）
	 * @return 存款账号（欢心宝）
	 */
	public String getDepositAcNo() {
		return depositAcNo;
	}
	/**
	 * 存款账号（欢心宝）
	 * @param depositAcNo
	 */
	public void setDepositAcNo(String depositAcNo) {
		this.depositAcNo = depositAcNo;
	}
	public Double getBailBal() {
		return bailBal;
	}
	public void setBailBal(Double bailBal) {
		this.bailBal = bailBal;
	}
	/**
	 * 指定存款账号余额（欢心宝）
	 * @return 指定存款账号余额（欢心宝）
	 */
	public Double getDepositAmt() {
		return depositAmt;
	}
	/**
	 * 指定存款账号余额（欢心宝）
	 * @param depositAmt
	 */
	public void setDepositAmt(Double depositAmt) {
		this.depositAmt = depositAmt;
	}
	public String getCifTyp() {
		return cifTyp;
	}
	public void setCifTyp(String cifTyp) {
		this.cifTyp = cifTyp;
	}
	public String getIdTyp() {
		return idTyp;
	}
	public void setIdTyp(String idTyp) {
		this.idTyp = idTyp;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * 原借据号（借新还旧用）
	 * @return 原借据号（借新还旧用）
	 */
	public String getOldLoanNo() {
		return oldLoanNo;
	}
	/**
	 * 原借据号（借新还旧用）
	 * @param oldLoanNo
	 */
	public void setOldLoanNo(String oldLoanNo) {
		this.oldLoanNo = oldLoanNo;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	/**
	 * 数据来源
	 * @return 数据来源
	 */
	public String getAcSource() {
		return acSource;
	}
	/**
	 * 数据来源
	 * @param acSource 数据来源
	 */
	public void setAcSource(String acSource) {
		this.acSource = acSource;
	}
	/**
	 * 利率类型
	 * @return 利率类型
	 */
	public String getRateKind() {
		return rateKind;
	}
	/**
	 * 利率类型
	 * @param rateKind
	 */
	public void setRateKind(String rateKind) {
		this.rateKind = rateKind;
	}
	/**
	 * 宽延期天数
	 * @return 宽延期天数
	 */
	public int getGraceDay() {
		return graceDay;
	}
	/**
	 * 宽延期天数
	 * @param graceDay 
	 */
	public void setGraceDay(int graceDay) {
		this.graceDay = graceDay;
	}
	/**
	 * 原正常利率浮动比
	 * @return 原正常利率浮动比
	 */
	public Double getNormRateFlt() {
		return normRateFlt;
	}
	/**
	 * 原正常利率浮动比
	 * @param normRateFlt
	 */
	public void setNormRateFlt(Double normRateFlt) {
		this.normRateFlt = normRateFlt;
	}
	public String getPreferRateInd() {
		return preferRateInd;
	}
	public void setPreferRateInd(String preferRateInd) {
		this.preferRateInd = preferRateInd;
	}
	public String getYLoanBail() {
		return yLoanBail;
	}
	public void setYLoanBail(String yLoanBail) {
		this.yLoanBail = yLoanBail;
	}
	public int getLoanTermDay() {
		return loanTermDay;
	}
	public void setLoanTermDay(int loanTermDay) {
		this.loanTermDay = loanTermDay;
	}
	public String getFltType() {
		return fltType;
	}
	public void setFltType(String fltType) {
		this.fltType = fltType;
	}
	public double getRateFltCount() {
		return rateFltCount;
	}
	public void setRateFltCount(double rateFltCount) {
		this.rateFltCount = rateFltCount;
	}
	public String getIntstTermType() {
		return intstTermType;
	}
	public void setIntstTermType(String intstTermType) {
		this.intstTermType = intstTermType;
	}
}