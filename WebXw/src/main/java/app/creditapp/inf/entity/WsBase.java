package app.creditapp.inf.entity;
import app.base.BaseDomain;
/**
* Title: WsBase.java
* Description:
* @version：1.0
**/
public class WsBase extends BaseDomain {
	private String wsBaseId;//通讯ID:主键/WS_BASE_SEQ
	private String wsDate;//通讯日期
	private String wsTime;//通讯时间
	private String txCode;//接口编号
	private String wsToken;//口令
	private String wsSerial;//流水号
	private String respTime;//响应时间
	private String respCode;//响应码
	private String respDesc;//响应说明
	private String wsSts;//状态[01未处理02已处理]
	private String reqContent;//请求内容:JSON内容
	private String respContent;//响应内容:JSON内容
	private String brNo;//合作机构号
	private String brName;//合作机构号

	/**
	 * @return 通讯ID:主键/WS_BASE_SEQ
	 */
	public String getWsBaseId() {
	 	return wsBaseId;
	}
	/**
	 * @设置 通讯ID:主键/WS_BASE_SEQ
	 * @param wsBaseId
	 */
	public void setWsBaseId(String wsBaseId) {
	 	this.wsBaseId = wsBaseId;
	}
	/**
	 * @return 通讯日期
	 */
	public String getWsDate() {
	 	return wsDate;
	}
	/**
	 * @设置 通讯日期
	 * @param wsDate
	 */
	public void setWsDate(String wsDate) {
	 	this.wsDate = wsDate;
	}
	/**
	 * @return 通讯时间
	 */
	public String getWsTime() {
	 	return wsTime;
	}
	/**
	 * @设置 通讯时间
	 * @param wsTime
	 */
	public void setWsTime(String wsTime) {
	 	this.wsTime = wsTime;
	}
	/**
	 * @return 接口编号
	 */
	public String getTxCode() {
	 	return txCode;
	}
	/**
	 * @设置 接口编号
	 * @param txCode
	 */
	public void setTxCode(String txCode) {
	 	this.txCode = txCode;
	}
	/**
	 * @return 口令
	 */
	public String getWsToken() {
	 	return wsToken;
	}
	/**
	 * @设置 口令
	 * @param wsToken
	 */
	public void setWsToken(String wsToken) {
	 	this.wsToken = wsToken;
	}
	/**
	 * @return 流水号
	 */
	public String getWsSerial() {
	 	return wsSerial;
	}
	/**
	 * @设置 流水号
	 * @param wsSerial
	 */
	public void setWsSerial(String wsSerial) {
	 	this.wsSerial = wsSerial;
	}
	/**
	 * @return 响应时间
	 */
	public String getRespTime() {
	 	return respTime;
	}
	/**
	 * @设置 响应时间
	 * @param respTime
	 */
	public void setRespTime(String respTime) {
	 	this.respTime = respTime;
	}
	/**
	 * @return 响应码
	 */
	public String getRespCode() {
	 	return respCode;
	}
	/**
	 * @设置 响应码
	 * @param respCode
	 */
	public void setRespCode(String respCode) {
	 	this.respCode = respCode;
	}
	/**
	 * @return 响应说明
	 */
	public String getRespDesc() {
	 	return respDesc;
	}
	/**
	 * @设置 响应说明
	 * @param respDesc
	 */
	public void setRespDesc(String respDesc) {
	 	this.respDesc = respDesc;
	}
	/**
	 * @return 状态[01未处理02已处理]
	 */
	public String getWsSts() {
	 	return wsSts;
	}
	/**
	 * @设置 状态[01未处理02已处理]
	 * @param wsSts
	 */
	public void setWsSts(String wsSts) {
	 	this.wsSts = wsSts;
	}
	/**
	 * @return 请求内容:JSON内容
	 */
	public String getReqContent() {
	 	return reqContent;
	}
	/**
	 * @设置 请求内容:JSON内容
	 * @param reqContent
	 */
	public void setReqContent(String reqContent) {
	 	this.reqContent = reqContent;
	}
	/**
	 * @return 响应内容:JSON内容
	 */
	public String getRespContent() {
	 	return respContent;
	}
	/**
	 * @设置 响应内容:JSON内容
	 * @param respContent
	 */
	public void setRespContent(String respContent) {
	 	this.respContent = respContent;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
}