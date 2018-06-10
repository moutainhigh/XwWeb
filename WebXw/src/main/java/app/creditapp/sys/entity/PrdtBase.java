package app.creditapp.sys.entity;

import app.base.BaseDomain;

/**
 * Title: PrdtBase.java Description:
 * 
 * @version：1.0
 **/
public class PrdtBase extends BaseDomain {
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

	private String repayName;// 还款方案名称
	private String brName;// 合作机构名称

	private Double offRate;// 折扣率
	private Double minRate;// 最小月利率
	private String endDate;// 到期日期
	
	private String damName;//违约金公式名称
	private String defName;//罚息公式名称
	
	private String prdtByte;//运营方式[01线上02线下]
	private String allowArea;//允许展业地区
	private String allowAreaNo;//地区代码
	//后期添加
	private Float rgAppRate;//人工复核比例阈值
	private Integer failDays; //扣失最大天数[默认为30天]N天之后不再自动扣款
	private Integer regretDate;//反悔期[默认7天]反悔之后相当于无息贷款
	
	private String rulesName;//调用规则名称
	
	private String uploadDoc;//此产品下必传文档
	private String intopDoc;//此产品下与进件关联文档
	private String args;//参数字段，多个参数需要传入可合并传入用@分隔
	public PrdtBase() {
		offRate = 1.0d;//折扣率 默认值 1
		prdtVer = 1; //版本号 默认值 1
	}


	public String getUploadDoc() {
		return uploadDoc;
	}


	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}


	public String getIntopDoc() {
		return intopDoc;
	}


	public void setIntopDoc(String intopDoc) {
		this.intopDoc = intopDoc;
	}


	public String getArgs() {
		return args;
	}


	public void setArgs(String args) {
		this.args = args;
	}


	public String getPrdtId() {
		return prdtId;
	}


	public void setPrdtId(String prdtId) {
		this.prdtId = prdtId;
	}


	public String getPrdtNo() {
		return prdtNo;
	}


	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}


	public String getBrNo() {
		return brNo;
	}


	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}


	public String getPrdtName() {
		return prdtName;
	}


	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}


	public String getPrdtType() {
		return prdtType;
	}


	public void setPrdtType(String prdtType) {
		this.prdtType = prdtType;
	}


	public Double getMinAmt() {
		return minAmt;
	}


	public void setMinAmt(Double minAmt) {
		this.minAmt = minAmt;
	}


	public Double getMaxAmt() {
		return maxAmt;
	}


	public void setMaxAmt(Double maxAmt) {
		this.maxAmt = maxAmt;
	}


	public Integer getMinMon() {
		return minMon;
	}


	public void setMinMon(Integer minMon) {
		this.minMon = minMon;
	}


	public Integer getMaxMon() {
		return maxMon;
	}


	public void setMaxMon(Integer maxMon) {
		this.maxMon = maxMon;
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


	public String getPrdtDesc() {
		return prdtDesc;
	}


	public void setPrdtDesc(String prdtDesc) {
		this.prdtDesc = prdtDesc;
	}


	public Integer getPrdtVer() {
		return prdtVer;
	}


	public void setPrdtVer(Integer prdtVer) {
		this.prdtVer = prdtVer;
	}


	public String getPrdtSts() {
		return prdtSts;
	}


	public void setPrdtSts(String prdtSts) {
		this.prdtSts = prdtSts;
	}


	public String getOpNo() {
		return opNo;
	}


	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}


	public String getTxDate() {
		return txDate;
	}


	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}


	public String getUpDate() {
		return upDate;
	}


	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}


	public String getBrAccType() {
		return brAccType;
	}


	public void setBrAccType(String brAccType) {
		this.brAccType = brAccType;
	}


	public String getRepayName() {
		return repayName;
	}


	public void setRepayName(String repayName) {
		this.repayName = repayName;
	}


	public String getBrName() {
		return brName;
	}


	public void setBrName(String brName) {
		this.brName = brName;
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


	public String getDamName() {
		return damName;
	}


	public void setDamName(String damName) {
		this.damName = damName;
	}


	public String getDefName() {
		return defName;
	}


	public void setDefName(String defName) {
		this.defName = defName;
	}


	public String getPrdtByte() {
		return prdtByte;
	}


	public void setPrdtByte(String prdtByte) {
		this.prdtByte = prdtByte;
	}


	public String getAllowArea() {
		return allowArea;
	}


	public void setAllowArea(String allowArea) {
		this.allowArea = allowArea;
	}


	public String getAllowAreaNo() {
		return allowAreaNo;
	}

	public void setAllowAreaNo(String allowAreaNo) {
		this.allowAreaNo = allowAreaNo;
	}

	public Float getRgAppRate() {
		return rgAppRate;
	}

	public void setRgAppRate(Float rgAppRate) {
		this.rgAppRate = rgAppRate;
	}

	public Integer getFailDays() {
		return failDays;
	}

	public void setFailDays(Integer failDays) {
		this.failDays = failDays;
	}

	public Integer getRegretDate() {
		return regretDate;
	}


	public void setRegretDate(Integer regretDate) {
		this.regretDate = regretDate;
	}


	public String getRulesName() {
		return rulesName;
	}


	public void setRulesName(String rulesName) {
		this.rulesName = rulesName;
	}

}