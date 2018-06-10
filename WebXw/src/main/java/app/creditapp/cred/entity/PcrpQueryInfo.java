package app.creditapp.cred.entity;

import app.base.BaseDomain;
/**
 * 
  * 类名称：PcrpQueryInfo
  * 类描述： 
  * 创建人：lidong dhcc
  * 创建时间：2015-7-15 上午01:06:30
  * 修改人：Administrator  
 * @version
 */
public class PcrpQueryInfo extends BaseDomain implements java.io.Serializable{
	private String crpId;      //信用报告查询信息ID    
	private String custNo;     //客户号       
	private String batchNo;     //批次号     
	private String custName;   //客户名称              
	private String reportType; //报告类型(查询版式)    //
	private String certType;   //证件类型              
	private String certNum;    //证件号码              
	private String crpTime;    //查询时间              
	private String crpReason;  //查询原因              //
	private String crpSts;     //查询状态              
	private String crpType;    //系统类型              
	private String grandId;    //授权ID                //
	private String crpMtrDate; //信用报告失效日期      
	private String intOprrno;  //信用报告人行查询操作员
	private String reportId;   //信用报告编号
	private String crpContent; //信用报告内容          
	private String crpFileName; //信用报告文件路径      
	private String txDate;     //数据日期              
	private String ipAddr;     //IP_ADDR               
	private String macAddr;    // MAC_ADDR             
	private String remarks;    //备注                  
	private String crpExpDate; //系统人行提取日期      //
	private String crpFilePath;//信用报告路径          
	private String opprNo;     //信用报告本机查询员    
	private String brNo;       //信用报告查询机构号   
	private String querytype;       //信用报告查询机构号   
	private String aft_id;  //贷后信用报告ID
	private String existFlag;  //存在标志
	private String validFlag;  //有效标志
	private String czPactNo;  //业务编号
	
	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public PcrpQueryInfo(){
		this.crpId="";      
		this.custNo="";   
		this.batchNo="";  
		this.custName="";   
		this.reportType=""; 
		this.certType="";   
		this.certNum="";    
		this.crpTime="";    
		this.crpReason="";  
		this.crpSts="";     
		this.crpType="";    
		this.grandId="";    
		this.crpMtrDate=""; 
		this.intOprrno="";  
		this.reportId="";   
		this.crpContent=""; 
		this.crpFileName="";
		this.txDate="";     
		this.ipAddr="";     
		this.macAddr="";    
		this.remarks="";    
		this.crpExpDate=""; 
		this.crpFilePath="";
		this.opprNo="";     
		this.brNo="";       
		this.querytype="";  
		this.aft_id="";  
	}
	
	public String getAft_id() {
		return aft_id;
	}
	public void setAft_id(String aftId) {
		aft_id = aftId;
	}
	public String getCrpId() {
		return crpId;
	}
	public void setCrpId(String crpId) {
		this.crpId = crpId;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getCrpTime() {
		return crpTime;
	}
	public void setCrpTime(String crpTime) {
		this.crpTime = crpTime;
	}
	public String getCrpReason() {
		return crpReason;
	}
	public void setCrpReason(String crpReason) {
		this.crpReason = crpReason;
	}
	public String getCrpSts() {
		return crpSts;
	}
	public void setCrpSts(String crpSts) {
		this.crpSts = crpSts;
	}
	public String getCrpType() {
		return crpType;
	}
	public void setCrpType(String crpType) {
		this.crpType = crpType;
	}
	public String getGrandId() {
		return grandId;
	}
	public void setGrandId(String grandId) {
		this.grandId = grandId;
	}
	public String getCrpMtrDate() {
		return crpMtrDate;
	}
	public void setCrpMtrDate(String crpMtrDate) {
		this.crpMtrDate = crpMtrDate;
	}
	public String getIntOprrno() {
		return intOprrno;
	}
	public void setIntOprrno(String intOprrno) {
		this.intOprrno = intOprrno;
	}
	public String getCrpContent() {
		return crpContent;
	}
	public void setCrpContent(String crpContent) {
		this.crpContent = crpContent;
	}
	
	public String getCrpFileName() {
		return crpFileName;
	}
	public void setCrpFileName(String crpFileName) {
		this.crpFileName = crpFileName;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCrpExpDate() {
		return crpExpDate;
	}
	public void setCrpExpDate(String crpExpDate) {
		this.crpExpDate = crpExpDate;
	}
	public String getCrpFilePath() {
		return crpFilePath;
	}
	public void setCrpFilePath(String crpFilePath) {
		this.crpFilePath = crpFilePath;
	}
	public String getOpprNo() {
		return opprNo;
	}
	public void setOpprNo(String opprNo) {
		this.opprNo = opprNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getQuerytype() {
		return querytype;
	}
	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCzPactNo() {
		return czPactNo;
	}

	public void setCzPactNo(String czPactNo) {
		this.czPactNo = czPactNo;
	}

	   
}
