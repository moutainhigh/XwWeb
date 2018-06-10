package accounting.domain.sys;

import java.util.Map;

public class StsTransfer {
	private String prcpStp;	//本金转表外标志
	private String devi;	//减值标志
	private String prcpOver;//本金逾期标志
	private String isOver;	//贷款逾期标志
	private String stpInd;//利息转表外标志
	private double delqPrcp;//拖欠本金
	private double odPrcp;//逾期本金
	private double normInt;//正常利息
	private double odInt;//逾期利息
	
	/**
	 * @return the prcpStp
	 */
	public String getPrcpStp() {
		return prcpStp;
	}
	/**
	 * @param prcpStp the prcpStp to set
	 */
	public void setPrcpStp(String prcpStp) {
		this.prcpStp = prcpStp;
	}

	/**
	 * @return the odPrcp
	 */
	public double getOdPrcp() {
		return odPrcp;
	}
	/**
	 * @param odPrcp the odPrcp to set
	 */
	public void setOdPrcp(double odPrcp) {
		this.odPrcp = odPrcp;
	}
	/**
	 * @return the normInt
	 */
	public double getNormInt() {
		return normInt;
	}
	/**
	 * @param normInt the normInt to set
	 */
	public void setNormInt(double normInt) {
		this.normInt = normInt;
	}
	/**
	 * @return the odInt
	 */
	public double getOdInt() {
		return odInt;
	}
	/**
	 * @param odInt the odInt to set
	 */
	public void setOdInt(double odInt) {
		this.odInt = odInt;
	}
	/**
	 * @return the stpInd
	 */
	public String getStpInd() {
		return stpInd;
	}
	/**
	 * @param stpInd the stpInd to set
	 */
	public void setStpInd(String stpInd) {
		this.stpInd = stpInd;
	}



	/**
	 * @return the delqPrcp
	 */
	public double getDelqPrcp() {
		return delqPrcp;
	}
	/**
	 * @param delqPrcp the delqPrcp to set
	 */
	public void setDelqPrcp(double delqPrcp) {
		this.delqPrcp = delqPrcp;
	}

	/**
	 * @return devi 减值标志
	 */
	public String getDevi() {
		return devi;
	}
	/**
	 * @设置 减值标志
	 * @param devi 减值标志
	 */
	public void setDevi(String devi) {
		this.devi = devi;
	}
	/**
	 * @return prcpOver 本金逾期标志
	 */
	public String getPrcpOver() {
		return prcpOver;
	}
	/**
	 * @设置 本金逾期标志
	 * @param prcpOver 本金逾期标志
	 */
	public void setPrcpOver(String prcpOver) {
		this.prcpOver = prcpOver;
	}
	/**
	 * @return isOver 贷款逾期标志
	 */
	public String getIsOver() {
		return isOver;
	}
	/**
	 * @设置 贷款逾期标志
	 * @param isOver 贷款逾期标志
	 */
	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}

	
	
}
