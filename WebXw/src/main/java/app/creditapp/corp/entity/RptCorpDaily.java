package app.creditapp.corp.entity;
public class RptCorpDaily {
	private String rptDate;//报表日期
	private String rptType;//日期类型01-月初04-月底05-天
	private String brNo;//合作机构编号
	private int cifNum;//贷款户数
	private int lnCnt;//未结清笔数
	private double lnBal;//贷款余额
	private double overBal;//其中逾期余额
	private double intstBal;//欠息
	private double fineBal;//欠罚息
	private double mtrAmt;//本月到期本金
	private double mtrIntst;//本月到期利息
	private int cntYear;//本年发放笔数
	private int cntMon;//本月发放笔数
	private int cntDay;//本日发放笔数
	private int cntAvg;//日均发放笔数
	private double amtTot;//累放金额
	private double amtYear;//本年累放
	private double amtMon;//本月累放
	private double amtDay;//本日累放
	private double amtAvg;//日均放款
	private int repayDaycnt;//本日回收笔数
	private int repayMoncnt;//本月回收笔数
	private int repayYearcnt;//本年回收笔数
	private int repayTotcnt;//累计回收笔数
	private int repayAvgcnt;//日均回收笔数
	private double repayTot;//累计回收本金
	private double repayYear;//本年回收本金
	private double repayMon;//本月回收本金
	private double repayDay;//本日回收本金
	private double intstTot;//累计回收利息
	private double intstYear;//本年回收利息
	private double intstMon;//本月回收利息
	private double intstDay;//本日回收利息
	private double fineTot;//累计回收罚息
	private double fineYear;//本年回收罚息
	private double fineMon;//本月回收罚息
	private double fineDay;//本日回收罚息
	private double repayAvg;//日均回收（本金+利息）
	private double rebuyAmt;//累计回购金额
	private double compAmt;//累计代偿金额
	private double passDayrate;//本日通过率
	private double passMonrate;//本月通过率
	private double failDayrate;//本日扣失率
	private double failMonrate;//本月扣失率
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

	public double getFineBal(){
		return fineBal;
	}

	public void setFineBal(double fineBal){
		this.fineBal = fineBal;
	}

	public double getMtrAmt(){
		return mtrAmt;
	}

	public void setMtrAmt(double mtrAmt){
		this.mtrAmt = mtrAmt;
	}

	public double getMtrIntst(){
		return mtrIntst;
	}

	public void setMtrIntst(double mtrIntst){
		this.mtrIntst = mtrIntst;
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

	public int getRepayDaycnt(){
		return repayDaycnt;
	}

	public void setRepayDaycnt(int repayDaycnt){
		this.repayDaycnt = repayDaycnt;
	}

	public int getRepayMoncnt(){
		return repayMoncnt;
	}

	public void setRepayMoncnt(int repayMoncnt){
		this.repayMoncnt = repayMoncnt;
	}

	public int getRepayYearcnt(){
		return repayYearcnt;
	}

	public void setRepayYearcnt(int repayYearcnt){
		this.repayYearcnt = repayYearcnt;
	}

	public int getRepayTotcnt(){
		return repayTotcnt;
	}

	public void setRepayTotcnt(int repayTotcnt){
		this.repayTotcnt = repayTotcnt;
	}

	public int getRepayAvgcnt(){
		return repayAvgcnt;
	}

	public void setRepayAvgcnt(int repayAvgcnt){
		this.repayAvgcnt = repayAvgcnt;
	}

	public double getRepayTot(){
		return repayTot;
	}

	public void setRepayTot(double repayTot){
		this.repayTot = repayTot;
	}

	public double getRepayYear(){
		return repayYear;
	}

	public void setRepayYear(double repayYear){
		this.repayYear = repayYear;
	}

	public double getRepayMon(){
		return repayMon;
	}

	public void setRepayMon(double repayMon){
		this.repayMon = repayMon;
	}

	public double getRepayDay(){
		return repayDay;
	}

	public void setRepayDay(double repayDay){
		this.repayDay = repayDay;
	}

	public double getIntstTot(){
		return intstTot;
	}

	public void setIntstTot(double intstTot){
		this.intstTot = intstTot;
	}

	public double getIntstYear(){
		return intstYear;
	}

	public void setIntstYear(double intstYear){
		this.intstYear = intstYear;
	}

	public double getIntstMon(){
		return intstMon;
	}

	public void setIntstMon(double intstMon){
		this.intstMon = intstMon;
	}

	public double getIntstDay(){
		return intstDay;
	}

	public void setIntstDay(double intstDay){
		this.intstDay = intstDay;
	}

	public double getFineTot(){
		return fineTot;
	}

	public void setFineTot(double fineTot){
		this.fineTot = fineTot;
	}

	public double getFineYear(){
		return fineYear;
	}

	public void setFineYear(double fineYear){
		this.fineYear = fineYear;
	}

	public double getFineMon(){
		return fineMon;
	}

	public void setFineMon(double fineMon){
		this.fineMon = fineMon;
	}

	public double getFineDay(){
		return fineDay;
	}

	public void setFineDay(double fineDay){
		this.fineDay = fineDay;
	}

	public double getRepayAvg(){
		return repayAvg;
	}

	public void setRepayAvg(double repayAvg){
		this.repayAvg = repayAvg;
	}

	public double getRebuyAmt(){
		return rebuyAmt;
	}

	public void setRebuyAmt(double rebuyAmt){
		this.rebuyAmt = rebuyAmt;
	}

	public double getCompAmt(){
		return compAmt;
	}

	public void setCompAmt(double compAmt){
		this.compAmt = compAmt;
	}

	public double getPassDayrate(){
		return passDayrate;
	}

	public void setPassDayrate(double passDayrate){
		this.passDayrate = passDayrate;
	}

	public double getPassMonrate(){
		return passMonrate;
	}

	public void setPassMonrate(double passMonrate){
		this.passMonrate = passMonrate;
	}

	public double getFailDayrate(){
		return failDayrate;
	}

	public void setFailDayrate(double failDayrate){
		this.failDayrate = failDayrate;
	}

	public double getFailMonrate(){
		return failMonrate;
	}

	public void setFailMonrate(double failMonrate){
		this.failMonrate = failMonrate;
	}


}
