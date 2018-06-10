package app.creditapp.proj.entity;
public class RptProj {
	private String rptDate;//报表日期
	private String rptType;//日期类型
	private String projNo;//项目编号
	private String projNatu;//项目类型01单一02集合
	private double projAmt;//项目规模
	private double priAmt;//优先级规模
	private double subAmt;//次级规模
	private double accBal;//专户余额
	private double virBal;//虚拟户余额
	private int cifNum;//贷款户数
	private int lnCnt;//未结清笔数
	private double lnBal;//贷款余额
	private double overBal;//其中逾期余额
	private double intstBal;//欠息
	private double fineBal;//欠罚息
	private double mtrAmt;//本月到期本金
	private double mtrIntst;//本月到期利息
	private int cntYear;//本年放款笔数
	private int cntMon;//本月放款笔数
	private int cntDay;//本日放款笔数
	private int cntAvg;//日均放款笔数
	private double amtTot;//累放金额
	private double amtYear;//本年累放
	private double amtMon;//本月累放
	private double amtDay;//本日累放
	private double amtAvg;//日均放款
	private int repayDaycnt;//本日回收笔数
	private int repayMoncnt;//本月回收笔数
	private int repayYearcnt;//本年回收笔数
	private int repayTotcnt;//日均回收笔数
	private int repayAvgcnt;//本日回收笔数
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
	private double passDayrate;//累计代偿金额
	private double passMonrate;//累计代偿金额
	private double failDayrate;//累计代偿金额
	private double failMonrate;//累计代偿金额
	private double cancleRate;//累计代偿金额
	
	public String getRptDate(){
		return rptDate;
	}

	/**
	 * @return the passDayrate
	 */
	public double getPassDayrate() {
		return passDayrate;
	}

	/**
	 * @param passDayrate the passDayrate to set
	 */
	public void setPassDayrate(double passDayrate) {
		this.passDayrate = passDayrate;
	}

	/**
	 * @return the passMonrate
	 */
	public double getPassMonrate() {
		return passMonrate;
	}

	/**
	 * @param passMonrate the passMonrate to set
	 */
	public void setPassMonrate(double passMonrate) {
		this.passMonrate = passMonrate;
	}

	/**
	 * @return the failDayrate
	 */
	public double getFailDayrate() {
		return failDayrate;
	}

	/**
	 * @param failDayrate the failDayrate to set
	 */
	public void setFailDayrate(double failDayrate) {
		this.failDayrate = failDayrate;
	}

	/**
	 * @return the failMonrate
	 */
	public double getFailMonrate() {
		return failMonrate;
	}

	/**
	 * @param failMonrate the failMonrate to set
	 */
	public void setFailMonrate(double failMonrate) {
		this.failMonrate = failMonrate;
	}

	/**
	 * @return the cancleRate
	 */
	public double getCancleRate() {
		return cancleRate;
	}

	/**
	 * @param cancleRate the cancleRate to set
	 */
	public void setCancleRate(double cancleRate) {
		this.cancleRate = cancleRate;
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

	public String getProjNo(){
		return projNo;
	}

	public void setProjNo(String projNo){
		this.projNo = projNo;
	}

	public String getProjNatu(){
		return projNatu;
	}

	public void setProjNatu(String projNatu){
		this.projNatu = projNatu;
	}

	public double getProjAmt(){
		return projAmt;
	}

	public void setProjAmt(double projAmt){
		this.projAmt = projAmt;
	}

	public double getPriAmt(){
		return priAmt;
	}

	public void setPriAmt(double priAmt){
		this.priAmt = priAmt;
	}

	public double getSubAmt(){
		return subAmt;
	}

	public void setSubAmt(double subAmt){
		this.subAmt = subAmt;
	}

	public double getAccBal(){
		return accBal;
	}

	public void setAccBal(double accBal){
		this.accBal = accBal;
	}

	public double getVirBal(){
		return virBal;
	}

	public void setVirBal(double virBal){
		this.virBal = virBal;
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


}
