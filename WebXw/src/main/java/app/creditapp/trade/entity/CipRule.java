package app.creditapp.trade.entity;
import app.base.BaseDomain;
/**
* Title: CipRule.java
* Description:
* @version：1.0
**/
public class CipRule extends BaseDomain {
	private String rule_no;//规则编号
	private String trade_id;//交易号
	private String col_name;//字段名
	private String ch_col;//核心字段CH_COL
	private String rule_type;//字段处理类型
	private String col_length;//长度
	private String dec_length;//小数点后0的个数
	private String if_param;//是否参数处理
	private String if_send;//是否发送字段 1是0否
	private String key_col;//标签字段
	private String trade_name;//交易名

	/**
	 * @return 规则编号
	 */
	public String getRule_no() {
	 	return rule_no;
	}
	/**
	 * @设置 规则编号
	 * @param rule_no
	 */
	public void setRule_no(String rule_no) {
	 	this.rule_no = rule_no;
	}
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
	 * @return 字段名
	 */
	public String getCol_name() {
	 	return col_name;
	}
	/**
	 * @设置 字段名
	 * @param col_name
	 */
	public void setCol_name(String col_name) {
	 	this.col_name = col_name;
	}
	/**
	 * @return 核心字段
	 */
	public String getCh_col() {
	 	return ch_col;
	}
	/**
	 * @设置 核心字段
	 * @param ch_col
	 */
	public void setCh_col(String ch_col) {
	 	this.ch_col = ch_col;
	}
	/**
	 * @return 字段处理类型
	 */
	public String getRule_type() {
	 	return rule_type;
	}
	/**
	 * @设置 字段处理类型
	 * @param rule_type
	 */
	public void setRule_type(String rule_type) {
	 	this.rule_type = rule_type;
	}
	/**
	 * @return 长度
	 */
	public String getCol_length() {
	 	return col_length;
	}
	/**
	 * @设置 长度
	 * @param col_length
	 */
	public void setCol_length(String col_length) {
	 	this.col_length = col_length;
	}
	/**
	 * @return 小数点后0的个数
	 */
	public String getDec_length() {
	 	return dec_length;
	}
	/**
	 * @设置 小数点后0的个数
	 * @param dec_length
	 */
	public void setDec_length(String dec_length) {
	 	this.dec_length = dec_length;
	}
	/**
	 * @return 是否参数处理
	 */
	public String getIf_param() {
	 	return if_param;
	}
	/**
	 * @设置 是否参数处理
	 * @param if_param
	 */
	public void setIf_param(String if_param) {
	 	this.if_param = if_param;
	}
	/**
	 * @return 是否发送字段 1是0否
	 */
	public String getIf_send() {
	 	return if_send;
	}
	/**
	 * @设置 是否发送字段 1是0否
	 * @param if_send
	 */
	public void setIf_send(String if_send) {
	 	this.if_send = if_send;
	}
	/**
	 * @return 标签字段
	 */
	public String getKey_col() {
	 	return key_col;
	}
	/**
	 * @设置 标签字段
	 * @param key_col
	 */
	public void setKey_col(String key_col) {
	 	this.key_col = key_col;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
}