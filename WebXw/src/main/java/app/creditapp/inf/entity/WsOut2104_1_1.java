package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list listAc（账户信息）
 * @描述 进件批次查询---输出实体类
 */
public class WsOut2104_1_1 {

	private String acUse;// 账户用途
	private double acAmt;// 放款金额
	private String acType;// 账户类型
	private String Acno;// 账户号
	private String acName;// 账户户名
	private String bankCode;// 银行代码
	private String bankProv;// 账户开户省
	private String bankCity;// 账户开户市
	
	public String getAcUse() {
		return acUse;
	}
	public void setAcUse(String acUse) {
		this.acUse = acUse;
	}
	public double getAcAmt() {
		return acAmt;
	}
	public void setAcAmt(double acAmt) {
		this.acAmt = acAmt;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getAcno() {
		return Acno;
	}
	public void setAcno(String acno) {
		Acno = acno;
	}
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankProv() {
		return bankProv;
	}
	public void setBankProv(String bankProv) {
		this.bankProv = bankProv;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	
}
