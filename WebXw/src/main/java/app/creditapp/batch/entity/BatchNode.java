package app.creditapp.batch.entity;
import app.base.BaseDomain;

/**
 * Title: BatchNode.java Description:
 * 
 * @version：1.0
 **/
public class BatchNode extends BaseDomain {

	private String node_no;// 节点号
	private String node_name;// 节点名称
	private String scheme_no;// 执行类型
	private String scheme_info;// 执行方案
	private String filler;// 备注
	private String scheme_time_type;//执行时间类型
	private String scheme_time_detail;//执行时间明细
	private String use_sts;//启用标志
	private String err_flag;//执行出错时处理机制
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
	 * @return 执行类型
	 */
	public String getScheme_no() {
		return scheme_no;
	}

	/**
	 * @设置 执行类型
	 * @param scheme_no
	 */
	public void setScheme_no(String scheme_no) {
		this.scheme_no = scheme_no;
	}

	/**
	 * @return 执行方案
	 */
	public String getScheme_info() {
		return scheme_info;
	}

	/**
	 * @设置 执行方案
	 * @param scheme_info
	 */
	public void setScheme_info(String scheme_info) {
		this.scheme_info = scheme_info;
	}

	/**
	 * @return 备注
	 */
	public String getFiller() {
		return filler;
	}

	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getScheme_time_type() {
		return scheme_time_type;
	}

	public void setScheme_time_type(String schemeTimeType) {
		scheme_time_type = schemeTimeType;
	}

	public String getScheme_time_detail() {
		return scheme_time_detail;
	}

	public void setScheme_time_detail(String schemeTimeDetail) {
		scheme_time_detail = schemeTimeDetail;
	}

	public String getUse_sts() {
		return use_sts;
	}

	public void setUse_sts(String useSts) {
		use_sts = useSts;
	}

	public String getErr_flag() {
		return err_flag;
	}

	public void setErr_flag(String errFlag) {
		err_flag = errFlag;
	}
	

}