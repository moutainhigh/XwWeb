package app.creditapp.inf.client.zf;

import app.creditapp.inf.client.IBody;


public class PayDetail implements IBody{
	private String EntryNo;
	private String TradeType;
	private String PayPriority;
	private String BusinessEntryType;
	private int Amount;
	private String AccountNo;
	private String BankNo;
	private String BankDetailNo;
	private String AccountType;
	private String AccountName;
	private String Province;
	private String City;
	private String BankName;
	private String CVN2;
	private String VALDATE;
	private String PayChannel;
	private String PayAccount;
	private String BankCode;
	private String PayStatus;
	private String EntryMessage;
	private String DescCode;
	private String CertificateType;
	private String CertificateNo;
	private String PhoneNo;
	private String Email;
	
	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		s.append("<PayDetail>");
		s.append("<EntryNo>"+this.EntryNo+"</EntryNo>");
		s.append("<TradeType>"+this.TradeType+"</TradeType>");
		s.append("<PayPriority>"+this.PayPriority+"</PayPriority>");
		s.append("<BusinessEntryType>"+this.BusinessEntryType+"</BusinessEntryType>");
		s.append("<Amount>"+this.Amount+"</Amount>");
		s.append("<AccountNo>"+this.AccountNo+"</AccountNo>");
		s.append("<BankNo>"+this.BankNo+"</BankNo>");
		s.append("<BankDetailNo>"+this.BankDetailNo+"</BankDetailNo>");
		s.append("<AccountType>"+this.AccountType+"</AccountType>");
		s.append("<AccountName>"+this.AccountName+"</AccountName>");
		s.append("<Province>"+this.Province+"</Province>");
		s.append("<City>"+this.City+"</City>");
		s.append("<BankName>"+this.BankName+"</BankName>");
		s.append("<CVN2>"+this.CVN2+"</CVN2>");
		s.append("<VALDATE>"+this.VALDATE+"</VALDATE>");
		s.append("<PayChannel>"+this.PayChannel+"</PayChannel>");
		s.append("<PayAccount>"+this.PayAccount+"</PayAccount>");
		s.append("<BankCode>"+this.BankCode+"</BankCode>");
		s.append("<CertificateType>"+this.CertificateType+"</CertificateType>");
		s.append("<CertificateNo>"+this.CertificateNo+"</CertificateNo>");
		s.append("<PhoneNo>"+this.PhoneNo+"</PhoneNo>");
		s.append("<Email>"+this.Email+"</Email>");

		s.append("</PayDetail>");
		
		return s.toString();
	}
	
	public String getEntryNo() {
		return EntryNo;
	}
	public String getTradeType() {
		return TradeType;
	}
	public String getPayPriority() {
		return PayPriority;
	}
	public int getAmount() {
		return Amount;
	}
	public String getAccountNo() {
		return AccountNo;
	}
	public String getBankNo() {
		return BankNo;
	}
	public String getBankDetailNo() {
		return BankDetailNo;
	}
	public String getAccountType() {
		return AccountType;
	}
	public String getAccountName() {
		return AccountName;
	}
	public String getProvince() {
		return Province;
	}
	public String getCity() {
		return City;
	}
	public String getBankName() {
		return BankName;
	}
	public String getCVN2() {
		return CVN2;
	}
	public String getVALDATE() {
		return VALDATE;
	}
	public String getPayChannel() {
		return PayChannel;
	}
	public void setEntryNo(String entryNo) {
		EntryNo = entryNo;
	}
	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}
	public void setPayPriority(String payPriority) {
		PayPriority = payPriority;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}
	public void setBankNo(String bankNo) {
		BankNo = bankNo;
	}
	public void setBankDetailNo(String bankDetailNo) {
		BankDetailNo = bankDetailNo;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public void setCity(String city) {
		City = city;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public void setCVN2(String cVN2) {
		CVN2 = cVN2;
	}
	public void setVALDATE(String vALDATE) {
		VALDATE = vALDATE;
	}
	public void setPayChannel(String payChannel) {
		PayChannel = payChannel;
	}

	public String getPayAccount() {
		return PayAccount;
	}

	public void setPayAccount(String payAccount) {
		PayAccount = payAccount;
	}

	public String getPayStatus() {
		return PayStatus;
	}

	public String getEntryMessage() {
		return EntryMessage;
	}

	public void setPayStatus(String payStatus) {
		PayStatus = payStatus;
	}

	public void setEntryMessage(String entryMessage) {
		EntryMessage = entryMessage;
	}

	public String getBusinessEntryType() {
		return BusinessEntryType;
	}

	public void setBusinessEntryType(String businessEntryType) {
		BusinessEntryType = businessEntryType;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getCertificateType() {
		return CertificateType;
	}

	public String getCertificateNo() {
		return CertificateNo;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setCertificateType(String certificateType) {
		CertificateType = certificateType;
	}

	public void setCertificateNo(String certificateNo) {
		CertificateNo = certificateNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDescCode() {
		return DescCode;
	}

	public void setDescCode(String descCode) {
		DescCode = descCode;
	}
	
	
}