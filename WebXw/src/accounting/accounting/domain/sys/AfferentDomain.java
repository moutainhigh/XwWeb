package accounting.domain.sys;

public class AfferentDomain {
	
	
	private String txDt;		// 当前日期
	private String bizDt;		// 交易日期
	private String usrId;       // 柜员号
	private String brNo;        // 交易机构号
	private String finBrNo;		// 账务机构号
	
	/**
 	 * @return brNo 交易机构号
 	 */
	public String getBrNo() {
		return brNo;
	}
	/**
 	 * @设置  交易机构号
 	 * @param brNo
 	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
 	 * @return usrId  柜员号
 	 */
	public String getUsrId() {
		return usrId;
	}
	/**
 	 * @设置   柜员号
 	 * @param tel
 	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * 账务机构号
	 * @return
	 */
	public String getFinBrNo() {
		return finBrNo;
	}
	/**
	 * 账务机构号
	 * @param finBrNo
	 */
	public void setFinBrNo(String finBrNo) {
		this.finBrNo = finBrNo;
	}
	/**
	 * 当前日期
	 * @return
	 */
	public String getTxDt() {
		return txDt;
	}
	/**
	 * 当前日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}
	/**
	 * 交易日期
	 * @return
	 */
	public String getBizDt() {
		return bizDt;
	}
	/**
	 * 交易日期
	 * @param bizDt
	 */
	public void setBizDt(String bizDt) {
		this.bizDt = bizDt;
	}
	
	
}
