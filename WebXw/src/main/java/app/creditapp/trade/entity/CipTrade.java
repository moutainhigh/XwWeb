package app.creditapp.trade.entity;
import app.base.BaseDomain;
/**
* Title: CipTrade.java
* Description:
* @version：1.0
**/
public class CipTrade extends BaseDomain {
	private String trade_id;//交易号
	private String trade_name;//交易名
	private String sql;//sql
	private String channel_id;//渠道(核心)
	private String req_msgid;//请求报文模板编号
    private String rsp_msgid;
    private String tx_flag;//是否启用通信接口

	/**
	 * @return 交易号
	 */
	public String getTrade_id() {
	 	return trade_id;
	}
	/**
	 * @设置 交易号
	 * @param trade_id
	 */
	public void setTrade_id(String trade_id) {
	 	this.trade_id = trade_id;
	}
	/**
	 * @return 交易名
	 */
	public String getTrade_name() {
	 	return trade_name;
	}
	/**
	 * @设置 交易名
	 * @param trade_name
	 */
	public void setTrade_name(String trade_name) {
	 	this.trade_name = trade_name;
	}
	/**
	 * @return sql
	 */
	public String getSql() {
	 	return sql;
	}
	/**
	 * @设置 sql
	 * @param sql
	 */
	public void setSql(String sql) {
	 	this.sql = sql;
	}
	/**
	 * @return 渠道(核心)
	 */
	public String getChannel_id() {
	 	return channel_id;
	}
	/**
	 * @设置 渠道(核心)
	 * @param channel_id
	 */
	public void setChannel_id(String channel_id) {
	 	this.channel_id = channel_id;
	}
	public String getReq_msgid() {
		return req_msgid;
	}
	public void setReq_msgid(String req_msgid) {
		this.req_msgid = req_msgid;
	}
	public String getRsp_msgid() {
		return rsp_msgid;
	}
	public void setRsp_msgid(String rsp_msgid) {
		this.rsp_msgid = rsp_msgid;
	}
	public String getTx_flag() {
		return tx_flag;
	}
	public void setTx_flag(String txFlag) {
		tx_flag = txFlag;
	}
}