package app.creditapp.batch.entity;
import app.base.BaseDomain;
/**
* Title: BatchLog.java
* Description:
* @version：1.0
**/
public class BatchLog extends BaseDomain {
	private String batch_date;//执行日期
	private String node_no;//节点号
	private String node_name;//节点名称
	private String log_info;//日志信息
	private String beg_time;//起始时间
	private String end_time;//结束时间

	/**
	 * @return 执行日期
	 */
	public String getBatch_date() {
	 	return batch_date;
	}
	/**
	 * @设置 执行日期
	 * @param batch_date
	 */
	public void setBatch_date(String batch_date) {
	 	this.batch_date = batch_date;
	}
	/**
	 * @return 节点号
	 */
	public String getNode_no() {
	 	return node_no;
	}
	/**
	 * @设置 节点号
	 * @param node_no
	 */
	public void setNode_no(String node_no) {
	 	this.node_no = node_no;
	}
	/**
	 * @return 节点名称
	 */
	public String getNode_name() {
	 	return node_name;
	}
	/**
	 * @设置 节点名称
	 * @param node_name
	 */
	public void setNode_name(String node_name) {
	 	this.node_name = node_name;
	}
	/**
	 * @return 日志信息
	 */
	public String getLog_info() {
	 	return log_info;
	}
	/**
	 * @设置 日志信息
	 * @param log_info
	 */
	public void setLog_info(String log_info) {
	 	this.log_info = log_info;
	}
	/**
	 * @return 起始时间
	 */
	public String getBeg_time() {
	 	return beg_time;
	}
	/**
	 * @设置 起始时间
	 * @param beg_time
	 */
	public void setBeg_time(String beg_time) {
	 	this.beg_time = beg_time;
	}
	/**
	 * @return 结束时间
	 */
	public String getEnd_time() {
	 	return end_time;
	}
	/**
	 * @设置 结束时间
	 * @param end_time
	 */
	public void setEnd_time(String end_time) {
	 	this.end_time = end_time;
	}
}