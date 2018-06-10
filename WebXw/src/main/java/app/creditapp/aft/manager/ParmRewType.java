package app.creditapp.aft.manager;

public enum ParmRewType {
	Cash_Not_Suff_Funds("100016","资金现金账户余额不足"),
	Vuser_Not_Suff_Funds("100014","项目虚拟户余额不足预警"),
	Cash_Due_Audit("100015","到期清算提醒"),
	Entrust_Collection("100000","委外催收预警"),
	Suser_Not_Suff_Funds("100001","项目专户余额不足预警"),
	Proj_Overdue_Rate("100002","项目逾期率预警"),
	Payback_Datum_Not_Upload("100003","还款计划未上传预警"),
	Loanbefore_Datum_Not_Upload("100004","贷前资料未上传预警"),
	Image_Datum_Not_Upload("100005","影像资料未上传提醒"),
	Funds_Due_Settlement("100006","资金到期结息预警"),
	Funds_Due_Exchange("100007","资金到期兑付提醒"),
	Proj_Overdue_Payback("100008","项目到期还款提醒"),
	Strike_Balance_Succ("100009","冲账完成提醒"),
	Guar_Release("100010","押品解押提醒"),
	Pending_Approval("100011","待审批任务提醒"),
	Buyback_Differ("100012","回购信息对账差异提醒"),
	Split_Bill_Fail("100013","分账失败预警"),
	Proj_compensatory_Rate("100017","项目代偿率预警"),
	Proj_buy_back_Rate("100018","项目回购率预警"),
	Real_time_transaction_Rate("100019","实时交易预警");
	
	
	private String rewName;
	private String rewNo;
	private ParmRewType(String rewNo,String rewName){
		this.rewName = rewName;
		this.rewNo = rewNo;
	}
	public String getRewName() {
		return rewName;
	}
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
	public String getRewNo() {
		return rewNo;
	}
	public void setRewNo(String rewNo) {
		this.rewNo = rewNo;
	}
	
	public static ParmRewType getByRewNo(String rewNo){
		for(ParmRewType type:values()){
			if(type.getRewNo().equals(rewNo))return type;
		}
		return null;
	}
}
