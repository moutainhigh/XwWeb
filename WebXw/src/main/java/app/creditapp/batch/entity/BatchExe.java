package app.creditapp.batch.entity;
import app.base.BaseDomain;
/**
* Title: BatchExe.java
* Description:
* @version：1.0
**/
public class BatchExe extends BaseDomain {
	private String node_no;//节点号
	private String node_name;//节点名称
	private String node_status;//节点状态
	private String batch_date;//执行日期
	private Integer arrive_step;//执行步骤

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
	 * @return 节点状态
	 */
	public String getNode_status() {
	 	return node_status;
	}
	/**
	 * @设置 节点状态
	 * @param node_status
	 */
	public void setNode_status(String node_status) {
	 	this.node_status = node_status;
	}
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
	 * @return 执行步骤
	 */
	public Integer getArrive_step() {
	 	return arrive_step;
	}
	/**
	 * @设置 执行步骤
	 * @param arrive_step
	 */
	public void setArrive_step(Integer arrive_step) {
	 	this.arrive_step = arrive_step;
	}
}