package app.creditapp.inf.entity;

/**
 * Title: AllocateReg.java Description:
 * 
 * @version：1.0
 **/
public class MainTransactionCommonDTO {

	private String id;// 唯一标识本条记录
	private String projectID;// 项目ID
	private String ddType;// 代垫类型
	private String transDate;// 业务日期(拨款日期)
	private String transReason;// 拨款依据
	private String purpose;// 用途
	private String memo;// 摘要
	// private String userID ;//创建人ID
	// private String submitUserID ;//提交人ID
	private String userCode;// 创建人CODE
	private String staffApp;// 拨款对象 (自营代垫必填：0：外部账户 1：个人员工户2：信托户)

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id == null ? "" : id;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID
	 *            the projectID to set
	 */
	public void setProjectID(String projectID) {
		this.projectID = projectID == null ? "" : projectID;
	}

	/**
	 * @return the ddType
	 */
	public String getDdType() {
		return ddType;
	}

	/**
	 * @param ddType
	 *            the ddType to set
	 */
	public void setDdType(String ddType) {
		this.ddType = ddType == null ? "" : ddType;
	}

	/**
	 * @return the transDate
	 */
	public String getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate
	 *            the transDate to set
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate == null ? "" : transDate;
	}

	/**
	 * @return the transReason
	 */
	public String getTransReason() {
		return transReason;
	}

	/**
	 * @param transReason
	 *            the transReason to set
	 */
	public void setTransReason(String transReason) {
		this.transReason = transReason == null ? "" : transReason;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose == null ? "" : purpose;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo == null ? "" : memo;
	}

	/**
	 * @return the staffapp
	 */
	public String getStaffApp() {
		return staffApp;
	}

	/**
	 * @param staffapp
	 *            the staffapp to set
	 */
	public void setStaffApp(String staffApp) {
		this.staffApp = staffApp == null ? "" : staffApp;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? "" : userCode;
	}

}