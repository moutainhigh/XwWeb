package app.creditapp.batch.entity;
import app.base.BaseDomain;
/**
* Title: BatchStep.java
* Description:
* @version：1.0
**/
public class BatchStep extends BaseDomain {
	private String node_no;//节点号
	private String node_name;//节点名称
	private String up_node_no;//上级结点号
	private String up_node_name;//上级结点名称

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
	 * @return 上级结点号
	 */
	public String getUp_node_no() {
	 	return up_node_no;
	}
	/**
	 * @设置 上级结点号
	 * @param up_node_no
	 */
	public void setUp_node_no(String up_node_no) {
	 	this.up_node_no = up_node_no;
	}
	/**
	 * @return 上级结点名称
	 */
	public String getUp_node_name() {
	 	return up_node_name;
	}
	/**
	 * @设置 上级结点名称
	 * @param up_node_name
	 */
	public void setUp_node_name(String up_node_name) {
	 	this.up_node_name = up_node_name;
	}
}