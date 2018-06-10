package accounting.domain.loan;
/**
* Title: AcLnPayPln.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcLnPayPln extends accounting.domain.sys.CMISDomain {
	private String acId;                    //账户ID
	private String acSeq;                  //账户序号
	private String loanNo;                  //借据号
	private int    perdNo;                  //期号
	private String payDt;					//应还款日
	private String endDt;                   //到期日
	private double instmAmt;                //期供金额
	private double prcpAmt;                 //本金
	private double normInt;               //正常利息
	private double fineInt;               //罚息
	private double overInt;              //逾期利息
	private double inOverInt;			//表内逾期利息
	private double outOverInt;			//表外逾期利息
	private double cmpdInt;                 //复利
	private double prcpAcm;                 //本金积数
	private double intstAcm;                //利息积数
	private double cmpdAcm;                 //复利积数
	private double bal;                     //剩余本金
	private double repayPrcpAmt;            //已还本金
	private double repayNormInt;          //已还利息
	private double repayFineInt;          //已归还罚息金额
	private double repayOverInt;          //已还逾期利息
	private double repayInOverInt;		//已还表内逾期利息
	private double repayOutOverInt;      //已还表外逾期利息
	private double repayCmpdInt;         //已还复利
	private double rate;                    //贷款执行利率
	private double overRate;                //逾期利率
	private String overInd;                 //逾期标志
	private String lstPayDt;                //最近还款日
	private double normIntTaken;             //已计提正常利息
	private double overIntTaken;            //已计提逾期利息
	private double wvNormInt;             //减免正常利息
	private double wvOverInt;             //减免逾期利息
	private double wvInOverInt;
	private double wvOutOverInt;
	private double wvCmpdInt;             //减免复利
	private double wvFineInt;
	private double sbsyAmt;              //应贴息金额
	private String prcpSts;                 //本金状态
	private String intSts;                //利息状态
	private String setlSts;                 //是否还清标志
	private String fineIntDt;          	//上次结罚息日期
	private String ppErInd;                 //当期还款记录是否为主动还款
	private String acmDt;             		//滚积数日期
	private String acmBegDt;				//积数起始日期
	private double wvPrcpAmt;	//减免本金
	private String ifAdj;		//是否手动调整
	
	private double sbsyedAmt;		//已贴息金额
	
	/**
	 * @return 减免本金
	 */
	public double getWvPrcpAmt() {
		return wvPrcpAmt;
	}

	/**
	 * @param 减免本金
	 */
	public void setWvPrcpAmt(double wvPrcpAmt) {
		this.wvPrcpAmt = wvPrcpAmt;
	}

	/**
	 * @return 已计提正常利息
	 */
	public double getNormIntTaken() {
		return normIntTaken;
	}
	/**
	 * @param 已计提正常利息
	 */
	public void setNormIntTaken(double normIntTaken) {
		this.normIntTaken = normIntTaken;
	}
	/**
	 * @return 已计提逾期利息
	 */
	public double getOverIntTaken() {
		return overIntTaken;
	}
	/**
	 * @param 已计提逾期利息
	 */
	public void setOverIntTaken(double overIntTaken) {
		this.overIntTaken = overIntTaken;
	}
	/**
	 * @return 减免正常利息
	 */
	public double getWvNormInt() {
		return wvNormInt;
	}
	/**
	 * @param 减免正常利息
	 */
	public void setWvNormInt(double wvNormInt) {
		this.wvNormInt = wvNormInt;
	}
	/**
	 * @return 减免逾期利息
	 */
	public double getWvOverInt() {
		return wvOverInt;
	}
	/**
	 * @param 减免逾期利息
	 */
	public void setWvOverInt(double wvOverInt) {
		this.wvOverInt = wvOverInt;
	}
	/**
	 * @return 减免复利
	 */
	public double getWvCmpdInt() {
		return wvCmpdInt;
	}
	/**
	 * @param 减免复利
	 */
	public void setWvCmpdInt(double wvCmpdInt) {
		this.wvCmpdInt = wvCmpdInt;
	}
	/**
	 * @return 积数起始日期
	 */
	public String getAcmBegDt() {
		return acmBegDt;
	}
	/**
	 * @param 积数起始日期
	 */
	public void setAcmBegDt(String acmBegDt) {
		this.acmBegDt = acmBegDt;
	}
	/**
	 * @return 返回 账户ID
	 */
	public String getAcId() {
		return acId;
	}
	/**
	 * @设置 账户ID
	 * @param acId
	 */
	public void setAcId(String acId) {
		this.acId=acId;
	}
	/**
	 * @return 返回 账户序号
	 */
	public String getAcSeq() {
		return acSeq;
	}
	/**
	 * @设置 账户序号
	 * @param acSeqn
	 */
	public void setAcSeq(String acSeq) {
		this.acSeq=acSeq;
	}
	/**
	 * @return 返回 借据号
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo=loanNo;
	}
	/**
	 * @return 返回 期号
	 */
	public int getPerdNo() {
		return perdNo;
	}
	/**
	 * @设置 期号
	 * @param perdNo
	 */
	public void setPerdNo(int perdNo) {
		this.perdNo=perdNo;
	}
	
	/**
	 * 应还款日
	 * @return 应还款日
	 */
	public String getPayDt() {
		return payDt;
	}
	/**
	 * 应还款日
	 * @param payDt
	 */
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}

	/**
	 * @return 返回 到期日
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * @设置 到期日
	 * @param endDt
	 */
	public void setEndDt(String endDt) {
		this.endDt=endDt;
	}
	/**
	 * @return 返回 期供金额
	 */
	public double getInstmAmt() {
		return instmAmt;
	}
	/**
	 * @设置 期供金额
	 * @param instmAmt
	 */
	public void setInstmAmt(double instmAmt) {
		this.instmAmt=instmAmt;
	}
	/**
	 * @return 返回 本金
	 */
	public double getPrcpAmt() {
		return prcpAmt;
	}
	/**
	 * @设置 本金
	 * @param prcpAmt
	 */
	public void setPrcpAmt(double prcpAmt) {
		this.prcpAmt=prcpAmt;
	}
	/**
	 * @return 返回 正常利息
	 */
	public double getNormInt() {
		return normInt;
	}
	/**
	 * @设置 正常利息
	 * @param nromIntst
	 */
	public void setNormInt(double normInt) {
		this.normInt=normInt;
	}
	/**
	 * @return 返回 罚息
	 */
	public double getFineInt() {
		return fineInt;
	}
	/**
	 * @设置 罚息
	 * @param fineIntst
	 */
	public void setFineInt(double fineInt) {
		this.fineInt=fineInt;
	}
	/**
	 * @return 返回 逾期利息
	 */
	public double getOverInt() {
		return overInt;
	}
	/**
	 * @设置 逾期利息
	 * @param overInstst
	 */
	public void setOverInt(double overInt) {
		this.overInt=overInt;
	}
	/**
	 * @return 返回 复利
	 */
	public double getCmpdInt() {
		return cmpdInt;
	}
	/**
	 * @设置 复利
	 * @param cmpdAmt
	 */
	public void setCmpdInt(double cmpdInt) {
		this.cmpdInt=cmpdInt;
	}
	/**
	 * @return 返回 本金积数
	 */
	public double getPrcpAcm() {
		return prcpAcm;
	}
	/**
	 * @设置 本金积数
	 * @param prcpAcm
	 */
	public void setPrcpAcm(double prcpAcm) {
		this.prcpAcm=prcpAcm;
	}
	/**
	 * @return 返回 利息积数
	 */
	public double getIntstAcm() {
		return intstAcm;
	}
	/**
	 * @设置 利息积数
	 * @param intstAcm
	 */
	public void setIntstAcm(double intstAcm) {
		this.intstAcm=intstAcm;
	}
	/**
	 * @return 返回 复利积数
	 */
	public double getCmpdAcm() {
		return cmpdAcm;
	}
	/**
	 * @设置 复利积数
	 * @param cmpdAcm
	 */
	public void setCmpdAcm(double cmpdAcm) {
		this.cmpdAcm=cmpdAcm;
	}
	/**
	 * @return 返回 剩余本金
	 */
	public double getBal() {
		return bal;
	}
	/**
	 * @设置 剩余本金
	 * @param bal
	 */
	public void setBal(double bal) {
		this.bal=bal;
	}
	/**
	 * @return 返回 已还本金
	 */
	public double getRepayPrcpAmt() {
		return repayPrcpAmt;
	}
	/**
	 * @设置 已还本金
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(double repayPrcpAmt) {
		this.repayPrcpAmt=repayPrcpAmt;
	}
	/**
	 * @return 返回 已还利息
	 */
	public double getRepayNormInt() {
		return repayNormInt;
	}
	/**
	 * @设置 已还利息
	 * @param repayNormIntst
	 */
	public void setRepayNormInt(double repayNormInt) {
		this.repayNormInt=repayNormInt;
	}
	/**
	 * @return 返回 已归还罚息金额
	 */
	public double getRepayFineInt() {
		return repayFineInt;
	}
	/**
	 * @设置 已归还罚息金额
	 * @param repayFineIntst
	 */
	public void setRepayFineInt(double repayFineInt) {
		this.repayFineInt=repayFineInt;
	}
	/**
	 * @return 返回 已还逾期利息
	 */
	public double getRepayOverInt() {
		return repayOverInt;
	}
	/**
	 * @设置 已还逾期利息
	 * @param repayOverIntst
	 */
	public void setRepayOverInt(double repayOverInt) {
		this.repayOverInt=repayOverInt;
	}
	/**
	 * @return 返回 已还复利
	 */
	public double getRepayCmpdInt() {
		return repayCmpdInt;
	}
	/**
	 * @设置 已还复利
	 * @param repayCmpdInstst
	 */
	public void setRepayCmpdInt(double repayCmpdInt) {
		this.repayCmpdInt=repayCmpdInt;
	}
	/**
	 * @return 返回 贷款执行利率
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @设置 贷款执行利率
	 * @param rate
	 */
	public void setRate(double rate) {
		this.rate=rate;
	}
	/**
	 * @return 返回 逾期利率
	 */
	public double getOverRate() {
		return overRate;
	}
	/**
	 * @设置 逾期利率
	 * @param overRate
	 */
	public void setOverRate(double overRate) {
		this.overRate=overRate;
	}
	/**
	 * @return 返回 逾期标志
	 */
	public String getOverInd() {
		return overInd;
	}
	/**
	 * @设置 逾期标志
	 * @param overInd
	 */
	public void setOverInd(String overInd) {
		this.overInd=overInd;
	}
	/**
	 * @return 返回 最近还款日
	 */
	public String getLstPayDt() {
		return lstPayDt;
	}
	/**
	 * @设置 最近还款日
	 * @param lstPayDt
	 */
	public void setLstPayDt(String lstPayDt) {
		this.lstPayDt=lstPayDt;
	}
	
	/**
	 * @return 返回 贴息金额
	 */
	public double getSbsyAmt() {
		return sbsyAmt;
	}
	/**
	 * @设置 贴息金额
	 * @param sbsyIntAmt
	 */
	public void setSbsyAmt(double sbsyAmt) {
		this.sbsyAmt=sbsyAmt;
	}
	/**
	 * @return 返回 本金状态
	 */
	public String getPrcpSts() {
		return prcpSts;
	}
	/**
	 * @设置 本金状态
	 * @param prcpSts
	 */
	public void setPrcpSts(String prcpSts) {
		this.prcpSts=prcpSts;
	}
	/**
	 * @return 返回 利息状态
	 */
	public String getIntSts() {
		return intSts;
	}
	/**
	 * @设置 利息状态
	 * @param intstSts
	 */
	public void setIntSts(String intSts) {
		this.intSts=intSts;
	}
	/**
	 * @return setlSts 
	 */	
	public String getSetlSts() {
		return setlSts;
	}
	/**
	 * @设置 
	 * @param setlSts 
	 */
	public void setSetlSts(String setlSts) {
		this.setlSts = setlSts;
	}
	/**
	 * @return 返回 上次结罚息日期
	 */
	public String getFineIntDt() {
		return fineIntDt;
	}
	/**
	 * @设置 上次结罚息日期
	 * @param fineIntstGenDt
	 */
	public void setFineIntDt(String fineIntDt) {
		this.fineIntDt=fineIntDt;
	}
	/**
	 * @return 返回 当期还款记录是否为主动还款
	 */
	public String getPpErInd() {
		return ppErInd;
	}
	/**
	 * @设置 当期还款记录是否为主动还款
	 * @param ppErInd
	 */
	public void setPpErInd(String ppErInd) {
		this.ppErInd=ppErInd;
	}
	/**
	 * @return 返回 滚积数日期
	 */
	public String getAcmDt() {
		return acmDt;
	}
	/**
	 * @设置 滚积数日期
	 * @param psGenProdDt
	 */
	public void setAcmDt(String acmDt) {
		this.acmDt=acmDt;
	}

	/**
	 * @return inOverInt
	 */
	public double getInOverInt() {
		return inOverInt;
	}

	/**
	 * @param inOverInt the inOverInt to set
	 */
	public void setInOverInt(double inOverInt) {
		this.inOverInt = inOverInt;
	}

	/**
	 * @return outOverInt
	 */
	public double getOutOverInt() {
		return outOverInt;
	}

	/**
	 * @param outOverInt the outOverInt to set
	 */
	public void setOutOverInt(double outOverInt) {
		this.outOverInt = outOverInt;
	}

	/**
	 * @return repayInOverInt
	 */
	public double getRepayInOverInt() {
		return repayInOverInt;
	}

	/**
	 * @param repayInOverInt the repayInOverInt to set
	 */
	public void setRepayInOverInt(double repayInOverInt) {
		this.repayInOverInt = repayInOverInt;
	}

	/**
	 * @return repayOutOverInt
	 */
	public double getRepayOutOverInt() {
		return repayOutOverInt;
	}

	/**
	 * @param repayOutOverInt the repayOutOverInt to set
	 */
	public void setRepayOutOverInt(double repayOutOverInt) {
		this.repayOutOverInt = repayOutOverInt;
	}

	/**
	 * @return wvInOverInt
	 */
	public double getWvInOverInt() {
		return wvInOverInt;
	}

	/**
	 * @param wvInOverInt the wvInOverInt to set
	 */
	public void setWvInOverInt(double wvInOverInt) {
		this.wvInOverInt = wvInOverInt;
	}

	/**
	 * @return wvOutOverInt
	 */
	public double getWvOutOverInt() {
		return wvOutOverInt;
	}

	/**
	 * @param wvOutOverInt the wvOutOverInt to set
	 */
	public void setWvOutOverInt(double wvOutOverInt) {
		this.wvOutOverInt = wvOutOverInt;
	}

	public String getIfAdj() {
		return ifAdj;
	}

	public void setIfAdj(String ifAdj) {
		this.ifAdj = ifAdj;
	}

	public double getSbsyedAmt() {
		return sbsyedAmt;
	}
	public void setSbsyedAmt(double sbsyedAmt) {
		this.sbsyedAmt = sbsyedAmt;
	}

	public double getWvFineInt() {
		return wvFineInt;
	}

	public void setWvFineInt(double wvFineInt) {
		this.wvFineInt = wvFineInt;
	}
	
}