package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list lisctRel（借款关联人）
 * @描述 进件批次查询---输出实体类
 */
public class WsOut2104_1_4 {

	private String relName;// 借款人名称
	private String relIdtype;// 证件类型
	private String relIdno;// 证件号码
	private String relTel;// 联系电话

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public String getRelIdtype() {
		return relIdtype;
	}

	public void setRelIdtype(String relIdtype) {
		this.relIdtype = relIdtype;
	}

	public String getRelIdno() {
		return relIdno;
	}

	public void setRelIdno(String relIdno) {
		this.relIdno = relIdno;
	}

	public String getRelTel() {
		return relTel;
	}

	public void setRelTel(String relTel) {
		this.relTel = relTel;
	}

}
