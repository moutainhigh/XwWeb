package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjMang.java
* Description:
* @version：1.0
**/
public class ProjMang extends BaseDomain {
	private String loginId;//运营人ID[登录名(LOGIN_ID)]
	private String projNo;//项目编号
	private String relId;//关系ID[主键]
	private String userName;//项目经理名称
	private String prdtName;//项目名称
	private String perSOn;  //指人编号
	private String tiMe;    //指派时间
	private String id;//编号
	private String managers;//项目经理编号    
	private String loginName;//运营人名称
	private String vipFlag;//标记0未标记1VIP标记
	private String perSOnName;  //指派人名称
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return 项目经理[登录名(LOGIN_ID)]
	 */
	public String getLoginId() {
	 	return loginId;
	}
	/**
	 * @设置 项目经理[登录名(LOGIN_ID)]
	 * @param loginId
	 */
	public void setLoginId(String loginId) {
	 	this.loginId = loginId;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 关系ID[主键]
	 */
	public String getRelId() {
	 	return relId;
	}
	/**
	 * @设置 关系ID[主键]
	 * @param relId
	 */
	public void setRelId(String relId) {
	 	this.relId = relId;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getPerSOn() {
		return perSOn;
	}
	public void setPerSOn(String perSOn) {
		this.perSOn = perSOn;
	}
	public String getTiMe() {
		return tiMe;
	}
	public void setTiMe(String tiMe) {
		this.tiMe = tiMe;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagers() {
		return managers;
	}
	public void setManagers(String managers) {
		this.managers = managers;
	}
	public String getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPerSOnName() {
		return perSOnName;
	}
	public void setPerSOnName(String perSOnName) {
		this.perSOnName = perSOnName;
	}
}