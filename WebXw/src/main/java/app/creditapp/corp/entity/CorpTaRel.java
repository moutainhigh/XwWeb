package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpTaRel.java
* Description:
* @version：1.0
**/
public class CorpTaRel extends BaseDomain {
	private String relid;//关联关系ID
	private String brNo;//信贷合作机构编号
	private String taCifNo;//软通TA客户编号
	//非本实体类中的字段
	private String projNo;//项目编号

	/**
	 * @return 关联关系ID
	 */
	public String getRelid() {
	 	return relid;
	}
	/**
	 * @设置 关联关系ID
	 * @param relid
	 */
	public void setRelid(String relid) {
	 	this.relid = relid;
	}
	/**
	 * @return 信贷合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 信贷合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 软通TA客户编号
	 */
	public String getTaCifNo() {
	 	return taCifNo;
	}
	/**
	 * @设置 软通TA客户编号
	 * @param taCifNo
	 */
	public void setTaCifNo(String taCifNo) {
	 	this.taCifNo = taCifNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
}