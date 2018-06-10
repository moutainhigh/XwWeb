package app.creditapp.inf.client.zf;

import app.creditapp.inf.client.IBody;

public class AccountInfo implements IBody{

	private String AccountNo;//账户号
	private String AccountType;//账户类型
	private String AccountName;//持卡人姓名
	private String ChannelNo;//支付系统渠道（银行）编号
	private String BankDetailNo;//联行号
	private String BankName;//开户行名称
	private String Province;//开户行所在省
	private String City;//开户行所在市
	private String CVN2;//信用卡尾号
	private String VALDATE;//信用卡有效期
	private String PhoneNo;//手机号
	private String Email;//email
	private String CertificateType;//证件类型
	private String CertificateNo;//证件号
	
	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		
		s.append("<AccountInfo>");
		s.append("<AccountNo>");
		s.append(this.AccountNo==null?"":this.AccountNo);
		s.append("</AccountNo>");
		s.append("<AccountType>");
		s.append(this.AccountType==null?"":this.AccountType);
		s.append("</AccountType>");
		s.append("<AccountName>");
		s.append(this.AccountName==null?"":this.AccountName);
		s.append("</AccountName>");
		s.append("<ChannelNo>");
		s.append(this.ChannelNo==null?"":this.ChannelNo);
		s.append("</ChannelNo>");
		s.append("<BankDetailNo>");
		s.append(this.BankDetailNo==null?"":this.BankDetailNo);
		s.append("</BankDetailNo>");
		s.append("<BankName>");
		s.append(this.BankName==null?"":this.BankName);
		s.append("</BankName>");
		s.append("<Province>");
		s.append(this.Province==null?"":this.Province);
		s.append("</Province>");
		s.append("<City>");
		s.append(this.City==null?"":this.City);
		s.append("</City>");
		s.append("<CVN2>");
		s.append(this.CVN2==null?"":this.CVN2);
		s.append("</CVN2>");
		s.append("<VALDATE>");
		s.append(this.VALDATE==null?"":this.VALDATE);
		s.append("</VALDATE>");
		s.append("<PhoneNo>");
		s.append(this.PhoneNo==null?"":this.PhoneNo);
		s.append("</PhoneNo>");
		s.append("<Email>");
		s.append(this.Email==null?"":this.Email);
		s.append("</Email>");
		s.append("<CertificateType>");
		s.append(this.CertificateType==null?"":this.CertificateType);
		s.append("</CertificateType>");
		s.append("<CertificateNo>");
		s.append(this.CertificateNo==null?"":this.CertificateNo);
		s.append("</CertificateNo>");
		s.append("</AccountInfo>");
		return s.toString();
	}
	
	public String getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public String getChannelNo() {
		return ChannelNo;
	}
	public void setChannelNo(String channelNo) {
		ChannelNo = channelNo;
	}
	public String getBankDetailNo() {
		return BankDetailNo;
	}
	public void setBankDetailNo(String bankDetailNo) {
		BankDetailNo = bankDetailNo;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCVN2() {
		return CVN2;
	}
	public void setCVN2(String cVN2) {
		CVN2 = cVN2;
	}
	public String getVALDATE() {
		return VALDATE;
	}
	public void setVALDATE(String vALDATE) {
		VALDATE = vALDATE;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCertificateType() {
		return CertificateType;
	}
	public void setCertificateType(String certificateType) {
		CertificateType = certificateType;
	}
	public String getCertificateNo() {
		return CertificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		CertificateNo = certificateNo;
	}
	
	
}
