package app.creditapp.corp.entity;
public class RptCorpPrdt {
	private String rptDate;//报表日期
	private String rptType;//日期类型[01-月初04-月底05-天]
	private String brNo;//合作机构号
	private String prdtType;//产品大类
	private String prdtNo;//产品号
	private int cifNum;//户数
	private int lnCnt;//笔数
	private double lnBal;//余额
	private double overBal;//逾期余额
	private double intstBal;//欠息
	private int cntYear;//本年发放笔数
	private int cntMon;//本月发放笔数
	private int cntDay;//本日发放笔数
	private int cntAvg;//日均笔数
	private double amtTot;//累放金额
	private double amtYear;//本年累放
	private double amtMon;//本月累放
	private double amtDay;//本日累放
	private double amtAvg;//日均放款
	public String getRptDate(){
		return rptDate;
	}

	public void setRptDate(String rptDate){
		this.rptDate = rptDate;
	}

	public String getRptType(){
		return rptType;
	}

	public void setRptType(String rptType){
		this.rptType = rptType;
	}

	public String getBrNo(){
		return brNo;
	}

	public void setBrNo(String brNo){
		this.brNo = brNo;
	}

	public String getPrdtType(){
		return prdtType;
	}

	public void setPrdtType(String prdtType){
		this.prdtType = prdtType;
	}

	public String getPrdtNo(){
		return prdtNo;
	}

	public void setPrdtNo(String prdtNo){
		this.prdtNo = prdtNo;
	}

	public int getCifNum(){
		return cifNum;
	}

	public void setCifNum(int cifNum){
		this.cifNum = cifNum;
	}

	public int getLnCnt(){
		return lnCnt;
	}

	public void setLnCnt(int lnCnt){
		this.lnCnt = lnCnt;
	}

	public double getLnBal(){
		return lnBal;
	}

	public void setLnBal(double lnBal){
		this.lnBal = lnBal;
	}

	public double getOverBal(){
		return overBal;
	}

	public void setOverBal(double overBal){
		this.overBal = overBal;
	}

	public double getIntstBal(){
		return intstBal;
	}

	public void setIntstBal(double intstBal){
		this.intstBal = intstBal;
	}

	public int getCntYear(){
		return cntYear;
	}

	public void setCntYear(int cntYear){
		this.cntYear = cntYear;
	}

	public int getCntMon(){
		return cntMon;
	}

	public void setCntMon(int cntMon){
		this.cntMon = cntMon;
	}

	public int getCntDay(){
		return cntDay;
	}

	public void setCntDay(int cntDay){
		this.cntDay = cntDay;
	}

	public int getCntAvg(){
		return cntAvg;
	}

	public void setCntAvg(int cntAvg){
		this.cntAvg = cntAvg;
	}

	public double getAmtTot(){
		return amtTot;
	}

	public void setAmtTot(double amtTot){
		this.amtTot = amtTot;
	}

	public double getAmtYear(){
		return amtYear;
	}

	public void setAmtYear(double amtYear){
		this.amtYear = amtYear;
	}

	public double getAmtMon(){
		return amtMon;
	}

	public void setAmtMon(double amtMon){
		this.amtMon = amtMon;
	}

	public double getAmtDay(){
		return amtDay;
	}

	public void setAmtDay(double amtDay){
		this.amtDay = amtDay;
	}

	public double getAmtAvg(){
		return amtAvg;
	}

	public void setAmtAvg(double amtAvg){
		this.amtAvg = amtAvg;
	}


}
