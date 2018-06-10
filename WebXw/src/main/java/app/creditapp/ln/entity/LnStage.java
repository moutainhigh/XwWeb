package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnStage.java
* Description:
* @version：1.0
**/
public class LnStage extends BaseDomain {
	private String partSts;//分账标志[01未分账02分账通过03分账失败]
	private String mstSts;//主文件生成标志[01未生成02已生成]
	private String sendSts;//发送支付平台状态[01未发送02已发送]
	private String paySts;//放款状态[01未放款02已放款]
	private String rsDesc;//结果说明
	private String txTime;//申请日期
	private String upTime;//更新日期
	private String appId;//申请ID
	private String valSts;//校验状态[01未校验02通过03未通过]
	private String dupSts;//重复状态[01未检查02不重复03重复]
	private String chkSts;//筛查状态[01未筛查02通过03未通过]
	private String evalSts;//客户评级[1未评级A级B级C级]C为拒绝
	private String splitSts; //拆分状态[01未拆分02拆分成功03拆分失败]
	private String zdappSts;//自动审批状态[01待审批02通过03否决]
	private String pactSts;//合同生成标志[01未生成02已生成]
	private String rgappSts;//人工审批状态[00不审批01待审批02通过03否决]
	private String dueSts;//借据生成标志[01未生成02已生成]

	/**
	 * @return 分账标志[01未分账02分账通过03分账失败]
	 */
	public String getPartSts() {
	 	return partSts;
	}
	/**
	 * @设置 分账标志[01未分账02分账通过03分账失败]
	 * @param partSts
	 */
	public void setPartSts(String partSts) {
	 	this.partSts = partSts;
	}
	/**
	 * @return 主文件生成标志[01未生成02已生成]
	 */
	public String getMstSts() {
	 	return mstSts;
	}
	/**
	 * @设置 主文件生成标志[01未生成02已生成]
	 * @param mstSts
	 */
	public void setMstSts(String mstSts) {
	 	this.mstSts = mstSts;
	}
	/**
	 * @return 发送支付平台状态[01未发送02已发送]
	 */
	public String getSendSts() {
	 	return sendSts;
	}
	/**
	 * @设置 发送支付平台状态[01未发送02已发送]
	 * @param sendSts
	 */
	public void setSendSts(String sendSts) {
	 	this.sendSts = sendSts;
	}
	/**
	 * @return 放款状态[01未放款02已放款]
	 */
	public String getPaySts() {
	 	return paySts;
	}
	/**
	 * @设置 放款状态[01未放款02已放款]
	 * @param paySts
	 */
	public void setPaySts(String paySts) {
	 	this.paySts = paySts;
	}
	/**
	 * @return 结果说明
	 */
	public String getRsDesc() {
	 	return rsDesc;
	}
	/**
	 * @设置 结果说明
	 * @param rsDesc
	 */
	public void setRsDesc(String rsDesc) {
	 	this.rsDesc = rsDesc;
	}
	/**
	 * @return 申请日期
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 申请日期
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	/**
	 * @return 更新日期
	 */
	public String getUpTime() {
	 	return upTime;
	}
	/**
	 * @设置 更新日期
	 * @param upTime
	 */
	public void setUpTime(String upTime) {
	 	this.upTime = upTime;
	}
	/**
	 * @return 申请ID
	 */
	public String getAppId() {
	 	return appId;
	}
	/**
	 * @设置 申请ID
	 * @param appId
	 */
	public void setAppId(String appId) {
	 	this.appId = appId;
	}
	/**
	 * @return 校验状态[01未校验02通过03未通过]
	 */
	public String getValSts() {
	 	return valSts;
	}
	/**
	 * @设置 校验状态[01未校验02通过03未通过]
	 * @param valSts
	 */
	public void setValSts(String valSts) {
	 	this.valSts = valSts;
	}
	/**
	 * @return 重复状态[01未检查02不重复03重复]
	 */
	public String getDupSts() {
	 	return dupSts;
	}
	/**
	 * @设置 重复状态[01未检查02不重复03重复]
	 * @param dupSts
	 */
	public void setDupSts(String dupSts) {
	 	this.dupSts = dupSts;
	}
	/**
	 * @return 筛查状态[01未筛查02通过03未通过]
	 */
	public String getChkSts() {
	 	return chkSts;
	}
	/**
	 * @设置 筛查状态[01未筛查02通过03未通过]
	 * @param chkSts
	 */
	public void setChkSts(String chkSts) {
	 	this.chkSts = chkSts;
	}
	/**
	 * @return 客户评级[1未评级A级B级C级]C为拒绝
	 */
	public String getEvalSts() {
	 	return evalSts;
	}
	/**
	 * @设置 客户评级[1未评级A级B级C级]C为拒绝
	 * @param evalSts
	 */
	public void setEvalSts(String evalSts) {
	 	this.evalSts = evalSts;
	}
	/**
	 * @return 自动审批状态[01待审批02通过03否决]
	 */
	public String getZdappSts() {
	 	return zdappSts;
	}
	/**
	 * @设置 自动审批状态[01待审批02通过03否决]
	 * @param zdappSts
	 */
	public void setZdappSts(String zdappSts) {
	 	this.zdappSts = zdappSts;
	}
	/**
	 * @return 合同生成标志[01未生成02已生成]
	 */
	public String getPactSts() {
	 	return pactSts;
	}
	/**
	 * @设置 合同生成标志[01未生成02已生成]
	 * @param pactSts
	 */
	public void setPactSts(String pactSts) {
	 	this.pactSts = pactSts;
	}
	/**
	 * @return 人工审批状态[00不审批01待审批02通过03否决]
	 */
	public String getRgappSts() {
	 	return rgappSts;
	}
	/**
	 * @设置 人工审批状态[00不审批01待审批02通过03否决]
	 * @param rgappSts
	 */
	public void setRgappSts(String rgappSts) {
	 	this.rgappSts = rgappSts;
	}
	/**
	 * @return 借据生成标志[01未生成02已生成]
	 */
	public String getDueSts() {
	 	return dueSts;
	}
	/**
	 * @设置 借据生成标志[01未生成02已生成]
	 * @param dueSts
	 */
	public void setDueSts(String dueSts) {
	 	this.dueSts = dueSts;
	}
	/**
	 *  拆分状态[01未拆分02拆分成功03拆分失败]
	 */
	public String getSplitSts() {
		return splitSts;
	}
	/**
	 * 拆分状态[01未拆分02拆分成功03拆分失败]
	 * @param splitSts
	 */
	public void setSplitSts(String splitSts) {
		this.splitSts = splitSts;
	}
}