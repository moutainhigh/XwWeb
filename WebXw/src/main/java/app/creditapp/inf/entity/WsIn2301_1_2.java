package app.creditapp.inf.entity;

public class WsIn2301_1_2 {
	private String pactNo;//合同号
    private int    cnt;// 期次号
	private String feeType;//费用类型
	private double feeAmt;//费用金额
	private String brNo;//合作机构号
	private String wsId;// 数据ID
	private String feeKind;//费用类型代收自收
	
	public String getFeeType() {
		return feeType;
	}
	
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	public double getFeeAmt() {
		return feeAmt;
	}
	
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getPactNo() {
		return pactNo;
	}

	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public String getFeeKind() {
		return feeKind;
	}

	public void setFeeKind(String feeKind) {
		this.feeKind = feeKind;
	}
	
	
}
