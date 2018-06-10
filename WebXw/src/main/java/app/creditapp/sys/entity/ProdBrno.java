package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: ProdBrno.java
* Description:
* @version：1.0
**/
public class ProdBrno extends BaseDomain {
	private String pb_id;//编号
	private String br_no;//机构/事业部编号
	private String prod_no;//产品号
	private String filler;//备注
	private String op_no;//登记人
	private String op_brno;//登记机构
	private String tx_date;//登记日期
	private String up_date;//更新日期
	private String prod_name;

	/**
	 * @return 编号
	 */
	public String getPb_id() {
	 	return pb_id;
	}
	/**
	 * @设置 编号
	 * @param pb_id
	 */
	public void setPb_id(String pb_id) {
	 	this.pb_id = pb_id;
	}
	/**
	 * @return 机构/事业部编号
	 */
	public String getBr_no() {
	 	return br_no;
	}
	/**
	 * @设置 机构/事业部编号
	 * @param br_no
	 */
	public void setBr_no(String br_no) {
	 	this.br_no = br_no;
	}
	/**
	 * @return 产品号
	 */
	public String getProd_no() {
	 	return prod_no;
	}
	/**
	 * @设置 产品号
	 * @param prod_no
	 */
	public void setProd_no(String prod_no) {
	 	this.prod_no = prod_no;
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
	/**
	 * @return 登记人
	 */
	public String getOp_no() {
	 	return op_no;
	}
	/**
	 * @设置 登记人
	 * @param op_no
	 */
	public void setOp_no(String op_no) {
	 	this.op_no = op_no;
	}
	/**
	 * @return 登记机构
	 */
	public String getOp_brno() {
	 	return op_brno;
	}
	/**
	 * @设置 登记机构
	 * @param op_brno
	 */
	public void setOp_brno(String op_brno) {
	 	this.op_brno = op_brno;
	}
	/**
	 * @return 登记日期
	 */
	public String getTx_date() {
	 	return tx_date;
	}
	/**
	 * @设置 登记日期
	 * @param tx_date
	 */
	public void setTx_date(String tx_date) {
	 	this.tx_date = tx_date;
	}
	/**
	 * @return 更新日期
	 */
	public String getUp_date() {
	 	return up_date;
	}
	/**
	 * @设置 更新日期
	 * @param up_date
	 */
	public void setUp_date(String up_date) {
	 	this.up_date = up_date;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
}