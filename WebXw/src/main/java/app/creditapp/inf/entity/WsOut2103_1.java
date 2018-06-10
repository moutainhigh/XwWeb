package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list
 * List
 * @描述   进件批次查询---输出实体类
 */
public class WsOut2103_1 {

	private String batNo;// 批次号
	private String batTime;// 接收时间
	private String batNum;// 进件笔数
	private String recvNum;// 受理笔数
	private String chkNum;// 审批笔数
	private String chkNumOk;// 审批通过笔数
	private String lnNum;// 放款笔数
	
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
	public String getBatNum() {
		return batNum;
	}
	public void setBatNum(String batNum) {
		this.batNum = batNum;
	}
	public String getRecvNum() {
		return recvNum;
	}
	public void setRecvNum(String recvNum) {
		this.recvNum = recvNum;
	}
	public String getChkNum() {
		return chkNum;
	}
	public void setChkNum(String chkNum) {
		this.chkNum = chkNum;
	}
	public String getChkNumOk() {
		return chkNumOk;
	}
	public void setChkNumOk(String chkNumOk) {
		this.chkNumOk = chkNumOk;
	}
	public String getLnNum() {
		return lnNum;
	}
	public void setLnNum(String lnNum) {
		this.lnNum = lnNum;
	}

}
