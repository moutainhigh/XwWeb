package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * listGage
 * @描述   进件批量申请--输入实体类 LN_GAGE_MID
 */
public class WsIn2101_1_2 {
	
	
	private String gcustName;   //押品所有权人名称
	private String gcustIdtype; //押品所有权人证件类型
	private String gcustIdno;   //押品所有权人证件号码
	private String gType;       //押品类型
	private String gName;       //押品名称
	private String gDesc;       //押品描述
    private double gValue;		//押品评估价值
    private String gLicno; 	    //权证号码
    private String gLicType;    //权证类型
    private String pactNo; //合同号
    
    public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	//添加
    private String appId;
    private String gLicbr;
    private String gBegdate;
    private String gEnddate;
    private String gAmt;
	public String getgType() {
		return gType;
	}
	public void setgType(String type) {
		gType = type;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String name) {
		gName = name;
	}
	public String getgDesc() {
		return gDesc;
	}
	public void setgDesc(String desc) {
		gDesc = desc;
	}
	public double getgValue() {
		return gValue;
	}
	public void setgValue(double value) {
		gValue = value;
	}
	public String getgLicno() {
		return gLicno;
	}
	public void setgLicno(String licno) {
		gLicno = licno;
	}
	public String getgLicType() {
		return gLicType;
	}
	public void setgLicType(String licType) {
		gLicType = licType;
	}
	public String getgLicbr() {
		return gLicbr;
	}
	public void setgLicbr(String licbr) {
		gLicbr = licbr;
	}
	public String getgBegdate() {
		return gBegdate;
	}
	public void setgBegdate(String begdate) {
		gBegdate = begdate;
	}
	public String getgEnddate() {
		return gEnddate;
	}
	public void setgEnddate(String enddate) {
		gEnddate = enddate;
	}
	public String getgAmt() {
		return gAmt;
	}
	public void setgAmt(String amt) {
		gAmt = amt;
	}
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
}
