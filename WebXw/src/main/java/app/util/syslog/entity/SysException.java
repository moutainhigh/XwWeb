package app.util.syslog.entity;

import edu.emory.mathcs.backport.java.util.Arrays;
import app.base.BaseDomain;

/**
 * Title: SysException.java Description:
 * 
 * @version：1.0
 **/
public class SysException extends BaseDomain {
	private String expId;// 记录ID
	private String expDate;// 异常发生日期
	private String expTime;// 异常发生时间
	private String expClass;// 异常发生类和方法
	private String expInfo;// 异常信息
	private String userId;//操作员号
	//新增字段
	private String userName;//操作员名称

	/**
	 * @return 记录ID
	 */
	public String getExpId() {
		return expId;
	}

	/**
	 * @设置 记录ID
	 * @param expId
	 */
	public void setExpId(String expId) {
		this.expId = expId;
	}

	/**
	 * @return 异常发生日期
	 */
	public String getExpDate() {
		return expDate;
	}

	/**
	 * @设置 异常发生日期
	 * @param expDate
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return 异常发生时间
	 */
	public String getExpTime() {
		return expTime;
	}

	/**
	 * @设置 异常发生时间
	 * @param expTime
	 */
	public void setExpTime(String expTime) {
		this.expTime = expTime.trim();
	}

	/**
	 * @return 异常发生类和方法
	 */
	public String getExpClass() {
		return expClass;
	}

	/**
	 * @设置 异常发生类和方法
	 * @param expClass
	 */
	public void setExpClass(String expClass) {
		this.expClass = expClass;
	}

	/**
	 * @return 异常信息
	 */
	public String getExpInfo() {
		if (expInfo != null && !"".equals(expInfo)) {
			byte[] bits = expInfo.getBytes();
			if (bits.length > 400) {
				expInfo = new String(Arrays.copyOfRange(bits, 0, 400));
			}
		}
		return expInfo;
	}

	/**
	 * @设置 异常信息
	 * @param expInfo
	 */
	public void setExpInfo(String expInfo) {
		this.expInfo = expInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if (userId == null) {
			userId = "SYS0000";
		}
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}