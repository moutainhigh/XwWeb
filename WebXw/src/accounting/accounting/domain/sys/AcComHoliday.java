package accounting.domain.sys;
/**
* Title: AcComHoliday.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcComHoliday extends accounting.domain.sys.CMISDomain {
	private String begDt;                   //节假日起始日期
	private String endDt;                   //节假日终止日期
	private String days;                    //天数
	private String typ; 
	private String hoId; //节假日Id

	/**
	 * @return 返回 节假日Id
	 */
	public String getHoId() {
		return hoId;
	}
	/**
	 * @设置 节假日Id
	 * @param hoId
	 */
	public void setHoId(String hoId) {
		this.hoId = hoId;
	}
	/**
	 * @return 返回 节假日起始日期
	 */
	public String getBegDt() {
		return begDt;
	}
	/**
	 * @设置 节假日起始日期
	 * @param begDt
	 */
	public void setBegDt(String begDt) {
		this.begDt=begDt;
	}
	/**
	 * @return 返回 节假日终止日期
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * @设置 节假日终止日期
	 * @param endDt
	 */
	public void setEndDt(String endDt) {
		this.endDt=endDt;
	}
	/**
	 * @return 返回 天数
	 */
	public String getDays() {
		return days;
	}
	/**
	 * @设置 天数
	 * @param days
	 */
	public void setDays(String days) {
		this.days=days;
	}
	/**
	 * @return 返回 类型
	 */
	public String getTyp() {
		return typ;
	}
	/**
	 * @设置 类型
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ=typ;
	}
}