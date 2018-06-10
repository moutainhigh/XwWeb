package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: InterfaceBackMeg.java
* Description:
* @version：1.0
**/
public class InterfaceBackMeg extends BaseDomain {
	private String backMegKey;//返回信息编码
	private String interfaceName;//接口名称
	private String backMegDes;//返回信息描述

	/**
	 * @return 返回信息编码
	 */
	public String getBackMegKey() {
	 	return backMegKey;
	}
	/**
	 * @设置 返回信息编码
	 * @param backMegKey
	 */
	public void setBackMegKey(String backMegKey) {
	 	this.backMegKey = backMegKey;
	}
	/**
	 * @return 接口名称
	 */
	public String getInterfaceName() {
	 	return interfaceName;
	}
	/**
	 * @设置 接口名称
	 * @param interfaceName
	 */
	public void setInterfaceName(String interfaceName) {
	 	this.interfaceName = interfaceName;
	}
	/**
	 * @return 返回信息描述
	 */
	public String getBackMegDes() {
	 	return backMegDes;
	}
	/**
	 * @设置 返回信息描述
	 * @param backMegDes
	 */
	public void setBackMegDes(String backMegDes) {
	 	this.backMegDes = backMegDes;
	}
}