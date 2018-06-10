package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   扣款信息上传[2301]listPayOver 输入信息
 */
public class WsIn2301_1_1 {
	
	private String pactNo;//合同号
    private int    cnt;// 期次号
	private double txPayOver;//应收罚息
	private String brNo;//合作机构号
	private String wsId;// 数据ID
	
	
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
	public double getTxPayOver() {
		return txPayOver;
	}
	public void setTxPayOver(double txPayOver) {
		this.txPayOver = txPayOver;
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

	
	
}
