package app.creditapp.inf.entity;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 23, 2016
 * @描述   预审批批次查询实体类
 */
public class WsOut2005_1 {
	
	private String batNo; //批次号
	private String batTime; //接收时间
	private int    batNum;  //进件笔数
	private int    recvNum; //受理笔数
	private int    chkNum;  //审批笔数
	private int    chkNumOK;//审批通过笔数
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getBatTime() {
		return batTime;
	}
	public void setBatTime(String batTime) {
		this.batTime = batTime;
	}
	public int getBatNum() {
		return batNum;
	}
	public void setBatNum(int batNum) {
		this.batNum = batNum;
	}
	public int getRecvNum() {
		return recvNum;
	}
	public void setRecvNum(int recvNum) {
		this.recvNum = recvNum;
	}
	public int getChkNum() {
		return chkNum;
	}
	public void setChkNum(int chkNum) {
		this.chkNum = chkNum;
	}
	public int getChkNumOK() {
		return chkNumOK;
	}
	public void setChkNumOK(int chkNumOK) {
		this.chkNumOK = chkNumOK;
	}
	
	
}
