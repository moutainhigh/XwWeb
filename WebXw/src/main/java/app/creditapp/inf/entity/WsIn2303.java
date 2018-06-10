package app.creditapp.inf.entity;

import java.util.List;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述  提前清贷申请[2303]  输入信息
 */
public class WsIn2303 {
	

	private String brNo;//合作机构号
	private String pactNo;//合同号
	private String acName;//账户姓名
	private String acNo;//还款账号
	private String opnCode;//银行代码
	private String opnName;//开户行
	private String serialNo;//扣款流水号
	private double payOver;//应收罚息
	private double feeRec;//应收代扣费总额
	private double repayTotal;//扣款金额
	private double repayAmt;//扣款本金
	private double repayInte;//扣款利息
	private double repayOver;//扣款罚息
	private double feeTotal;//代扣费总额
	
	
	private String cardChn;//虚拟账户渠道
	
	private String dealSts;//处理状态
	private String dealDesc;//处理描述
	//提前清贷数据id--王涛
	private String wsId;//数据ID  主键
	//20161101添加wt
	private List<WsIn2303_1> listPayOver;
	
	public WsIn2303() {
		dealSts = "01";
		dealDesc = "";
	}
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getAcNo() {
		return acNo;
	}
	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}
	public String getOpnCode() {
		return opnCode;
	}
	public void setOpnCode(String opnCode) {
		this.opnCode = opnCode;
	}
	public String getOpnName() {
		return opnName;
	}
	public void setOpnName(String opnName) {
		this.opnName = opnName;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public double getPayOver() {
		return payOver;
	}
	public void setPayOver(double payOver) {
		this.payOver = payOver;
	}
	public double getFeeRec() {
		return feeRec;
	}
	public void setFeeRec(double feeRec) {
		this.feeRec = feeRec;
	}
	public double getRepayTotal() {
		return repayTotal;
	}
	public void setRepayTotal(double repayTotal) {
		this.repayTotal = repayTotal;
	}
	public double getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(double repayAmt) {
		this.repayAmt = repayAmt;
	}
	public double getRepayInte() {
		return repayInte;
	}
	public void setRepayInte(double repayInte) {
		this.repayInte = repayInte;
	}
	public double getRepayOver() {
		return repayOver;
	}
	public void setRepayOver(double repayOver) {
		this.repayOver = repayOver;
	}
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public String getWsId() {
		return wsId;
	}
	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public String getDealSts() {
		return dealSts;
	}

	public void setDealSts(String dealSts) {
		this.dealSts = dealSts;
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public String getCardChn() {
		return cardChn;
	}

	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}

	/**
	 * @return the listPayOver
	 */
	public List<WsIn2303_1> getListPayOver() {
		return listPayOver;
	}

	/**
	 * @param listPayOver the listPayOver to set
	 */
	public void setListPayOver(List<WsIn2303_1> listPayOver) {
		this.listPayOver = listPayOver;
	}
	
	
}
