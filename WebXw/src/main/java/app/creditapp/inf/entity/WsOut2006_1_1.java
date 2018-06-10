package app.creditapp.inf.entity;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   预审批批次查询---输出实体类
 * 列表  list<Gage>
 */
public class WsOut2006_1_1 {
	
	private String gcustName; //押品所有权名称
	private String gcustIdtype; //押品所有权人证件类型
	private String gcustIdno;   //押品所有权人证件号码
	private String gType;       //押品类型
	private String gName;       //押品名称
	private String gDesc;       //押品描述
	private double gValue;      //押品评估价值
	private String gLicno;      //权证号码
	private String LicType;     //权证类型
	
	
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
	public String getLicType() {
		return LicType;
	}
	public void setLicType(String licType) {
		LicType = licType;
	}
	
	

}
