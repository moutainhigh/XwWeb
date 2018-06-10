package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述  提前清贷申请[2303]  输入信息
 */
public class WsIn2303_1 {
	
	private int cnt;
	private double txPayOver;
	
	private String brNo;
	private String pactNo;
	private String wsId;
	/**
	 * @return the cnt
	 */
	public int getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	/**
	 * @return the txPayOver
	 */
	public double getTxPayOver() {
		return txPayOver;
	}
	/**
	 * @param txPayOver the txPayOver to set
	 */
	public void setTxPayOver(double txPayOver) {
		this.txPayOver = txPayOver;
	}
	/**
	 * @return the brNo
	 */
	public String getBrNo() {
		return brNo;
	}
	/**
	 * @param brNo the brNo to set
	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
	 * @return the pactNo
	 */
	public String getPactNo() {
		return pactNo;
	}
	/**
	 * @param pactNo the pactNo to set
	 */
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	/**
	 * @return the wsId
	 */
	public String getWsId() {
		return wsId;
	}
	/**
	 * @param wsId the wsId to set
	 */
	public void setWsId(String wsId) {
		this.wsId = wsId;
	}
	
	
}
