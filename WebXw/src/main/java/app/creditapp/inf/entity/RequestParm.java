package app.creditapp.inf.entity;

/**
 * @作者 DHCC-zhangwei
 * @日期 Jun 23, 2016
 * @描述 请求参数实体
 */
public class RequestParm {
	private String reqNo; // 请求编号
	private String txCode; // 消息编号
	private String reqDate; // 日期
	private String reqTime; // 时间
	private String token; // 标识
	private String reqSerial; // 序列号
	private String content; // 请求内容（json格式）
	//新添加brNo合作机构编号
	private String brNo; // 合作机构编号

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReqSerial() {
		return reqSerial;
	}

	public void setReqSerial(String reqSerial) {
		this.reqSerial = reqSerial;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	

}
