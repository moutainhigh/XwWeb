package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述 预审批批量申请[2001] List List<Gage>
 */
public class WsIn2001_1_1 {
	private String appId;//申请id   
	private String gcustName;// 押品所有权人名称
	private String gcustIdtype;// 押品所有权人证件类型
	private String gcustIdno;// 押品所有权人证件号码
	private String gType;// 押品类型
	private String gName;// 押品名称
	private String gDesc;// 押品描述
	private double gValue;// 押品评估价值
	private String gLicno;// 权证号码
	private String gLicType;// 权证类型
	
	//实体类没有，数据库有的字段
    private String gLicbr;   
    private String gBegdate;
    private String gEnddate;
    private String gAmt;


	public String getGcustName() {
		return gcustName;
	}

	public void setGcustName(String gcustName) {
		this.gcustName = gcustName;
	}

	public String getGcustIdtype() {
		return gcustIdtype;
	}

	public void setGcustIdtype(String gcustIdtype) {
		this.gcustIdtype = gcustIdtype;
	}

	public String getGcustIdno() {
		return gcustIdno;
	}

	public void setGcustIdno(String gcustIdno) {
		this.gcustIdno = gcustIdno;
	}

	public String getgType() {
		return gType;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgDesc() {
		return gDesc;
	}

	public void setgDesc(String gDesc) {
		this.gDesc = gDesc;
	}

	public double getgValue() {
		return gValue;
	}

	public void setgValue(double gValue) {
		this.gValue = gValue;
	}

	public String getgLicno() {
		return gLicno;
	}

	public void setgLicno(String gLicno) {
		this.gLicno = gLicno;
	}

	public String getgLicType() {
		return gLicType;
	}

	public void setgLicType(String gLicType) {
		this.gLicType = gLicType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getgLicbr() {
		return gLicbr;
	}

	public void setgLicbr(String gLicbr) {
		this.gLicbr = gLicbr;
	}

	public String getgBegdate() {
		return gBegdate;
	}

	public void setgBegdate(String gBegdate) {
		this.gBegdate = gBegdate;
	}

	public String getgEnddate() {
		return gEnddate;
	}

	public void setgEnddate(String gEnddate) {
		this.gEnddate = gEnddate;
	}

	public String getgAmt() {
		return gAmt;
	}

	public void setgAmt(String gAmt) {
		this.gAmt = gAmt;
	}
	

}
