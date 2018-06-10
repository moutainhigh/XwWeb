package accounting.biz.sts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.sys.OperaInfo;
import accounting.domain.sys.StsTransfer;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TimeUtil;

/**
 * 形态转移
 *
 */
public class StsTransferBiz {
	private final Logger log = Logger.getLogger(StsTransferBiz.class);
	
	String traceNo = null ;
	int traceCnt = 0 ;
	List traceLogList = new ArrayList();

	/**
	 * @throws SQLException 
	 * 形态转移入口方法 ,不进行结息操作
	 * @param txCde	交易码
	 * @throws AccountingException
	 * @throws  
	 */
//	public void dealStsTransfer(OperaInfo operaInfo, String txCde) throws AccountingException{
//		Connection conn = operaInfo.getConn();
//		//查找欠款表信息,查询条件为 还清标志为"未还清"、项目未结束
//		List<AcLnLo> allLnLoList = (ArrayList)JdbcDao.queryList(new AcLnLo(), "select l.* from ac_ln_lo l where setl_sts = '"+PUBConstant.N+"' and loan_no in(select loan_no from ac_ln_mst a,proj_base p where a.proj_no=p.proj_no and p.proj_sts in('01','03','04') and a.loan_sts in('01','02','03')) order by l.loan_no,l.perd_no", conn);
//		
//		String loanNo = "";
//		AcLnMst acLnMst = null;
//		StsTransfer st = new StsTransfer();	//业务对象
//		
//		List acLnLos = new ArrayList();
//		
//		//遍历全部未还清的欠款表信息
//		for(int i=0;i<allLnLoList.size();i++){
//			AcLnLo acLnLo = (AcLnLo)allLnLoList.get(i);
//			//如果此记录和上一条记录不属于同一账户,对欠款表进行处理,然后更新主表
//			if(!loanNo.equals(acLnLo.getLoanNo())){
//				if(acLnLos.size()>0){
//					acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+acLnLo.getLoanNo()+"'","ac_ln_mst", conn);
//					for(int j=0;j<acLnLos.size();j++){
//						AcLnLo lnLo = (AcLnLo)acLnLos.get(j);
//						this.dealLnLo3(acLnMst, lnLo, st, operaInfo);
//					}
//					this.dealMstAndDcLog(operaInfo, acLnMst, st, txCde);
//					operaInfo.setTraceCnt(operaInfo.getTraceCnt()+1);
//				}
//				
//				st = new StsTransfer();
//				acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+acLnLo.getLoanNo()+"'", "ac_ln_mst", conn);
//				
//				AcTraceLog acTraceLog = new AcTraceLog();
//				acTraceLog.setTraceNo(operaInfo.getTraceNo());
//				acTraceLog.setTraceCnt(operaInfo.getTraceCnt());
//				acTraceLog.setTxCde(TransCode.LNAN); 
//				acTraceLog.setSubTxCde(TransCode.LNAN); 
//				acTraceLog.setLoanNo(acLnMst.getLoanNo()); 
//				acTraceLog.setCurNo(acLnMst.getCurNo()); 
//				acTraceLog.setPrdtNo(acLnMst.getPrdtNo()); 
//				acTraceLog.setPactNo(acLnMst.getPactNo());
//				acTraceLog.setBrNo(acLnMst.getBrNo());
//				acTraceLog.setCancelInd(PUBConstant.REV_ROL_NORM);
//				acTraceLog.setTxDt(operaInfo.getTxDt());
//				acTraceLog.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));
//				acTraceLog.setTxBrNo(operaInfo.getTxBrNo());
//				acTraceLog.setMemo("形态转移");
//				traceLogList.add(acTraceLog);
//				
//				acLnLos.clear();
//				acLnLos.add(acLnLo);
//				loanNo = acLnLo.getLoanNo();
//			}else{
//				acLnLos.add(acLnLo);
//				loanNo = acLnLo.getLoanNo();
//			}
//		}
//		
//		for(int j=0;j<acLnLos.size();j++){
//			AcLnLo lnLo = (AcLnLo)acLnLos.get(j);
//			this.dealLnLo3(acLnMst, lnLo, st, operaInfo);
//		}
//		//处理上一笔主文件,更新主文件并记分录
//		if(acLnLos.size()>0){
//			this.dealMstAndDcLog(operaInfo, acLnMst, st, txCde);
//		}
//		
//		
//		//处理交易流水表
//		if(traceLogList.size()>0){
//			JdbcDao.insertList(traceLogList, "AC_TRACE_LOG", conn);
//		}
//	}
	public void dealStsTransfer(OperaInfo operaInfo, String txCde) throws AccountingException, SQLException{
		Connection conn = operaInfo.getConn();
		//更新欠款表本金状态、利息状态
		PreparedStatement updateLnLoPrcpStsPst = null;
		//ResultSet updateLnLoPrcpStsRs = null;
		//更新欠款表 结清标志与结清日期
		PreparedStatement updateSetlStsPst = null;
		ResultSet updateSetlStsRs = null;
		//更新贷款主文件 状态 
		PreparedStatement updateLnMstPst = null;
		//ResultSet updateLnMstRs = null;
		//更新贷款主文件 结清标志 
		PreparedStatement updateLnMst2Pst = null;
		//ResultSet updateLnMst2Rs = null;
		//更新还款计划表状态
		PreparedStatement updateAcLnRepayPlnPst = null;
		
		int nums = 0;
		try {
			updateLnLoPrcpStsPst = conn
					.prepareStatement("update ac_ln_lo l set l.prcp_sts = (case when (nvl(prcp_amt,0) - nvl(repay_prcp_amt,0) - nvl(wv_prcp_amt,0)) > 0 then '30' else '40' end)"
							+ ",l.int_sts=(case when (nvl(norm_int,0)-nvl(repay_norm_int,0)-nvl(wv_norm_int,0))>0 then '30' else '40' end) where  setl_sts = 'N'  ");
			//updateLnLoPrcpStsRs = updateLnLoPrcpStsPst.executeQuery();
			nums = updateLnLoPrcpStsPst.executeUpdate();
			log.info("形态转移--更新欠款表本金状态、利息状态,RES=="+nums);
			
			updateSetlStsPst = conn
					.prepareStatement("update ac_ln_lo set setl_sts='Y' , setl_dt=? "
							+ " where prcp_sts='40' and int_sts='40' and (nvl(fine_int,0)-nvl(repay_fine_int,0)-nvl(wv_fine_int,0))<=0 and setl_sts='N'");
			updateSetlStsPst.setString(1, operaInfo.getBizDt());
			//updateSetlStsRs = updateSetlStsPst.executeQuery();
			nums = updateSetlStsPst.executeUpdate();
			log.info("形态转移--更新欠款表 结清标志与结清日期,RES=="+nums);
			
			updateLnMstPst = conn
					.prepareStatement("update ac_ln_mst a set a.loan_sts=(case when (select count(*) from ac_ln_lo b where a.loan_no=b.loan_no and b.setl_sts='N')>0 then '03' "
							+ " when a.mtr_dt>? and (select pay_dt from ac_ln_repay_pln_cur c where a.loan_no=c.loan_no)<=? then '02' "
							+ "  when a.mtr_dt>? and (select pay_dt from ac_ln_repay_pln_cur c where a.loan_no=c.loan_no)>? then '01' else '01' "
							+ "  end) where a.loan_sts in('01','02','03') ");
			updateLnMstPst.setString(1, operaInfo.getBizDt());
			updateLnMstPst.setString(2, operaInfo.getBizDt());
			updateLnMstPst.setString(3, operaInfo.getBizDt());
			updateLnMstPst.setString(4, operaInfo.getBizDt());
			//updateLnMstRs = updateLnMstPst.executeQuery();
			nums = updateLnMstPst.executeUpdate();
			log.info("形态转移--更新贷款主文件 状态 ,RES=="+nums);
			
			// 更新还款计划
			updateAcLnRepayPlnPst = conn
					.prepareStatement("update ac_ln_repay_pln a set (a.prcp_sts,a.int_sts,a.setl_sts) = ("
							+ " select prcp_sts , int_sts,setl_sts from ac_ln_lo l where a.loan_no=l.loan_no and a.perd_no=l.perd_no and a.setl_sts='N'"
							+ " ) WHERE  exists (select 1 from ac_ln_lo l where a.loan_no=l.loan_no and a.perd_no=l.perd_no and a.setl_sts='N')");
			nums = updateAcLnRepayPlnPst.executeUpdate();
			log.info("形态转移--更新还款计划表状态 ,RES=="+nums);
			
			updateLnMst2Pst = conn
					.prepareStatement(" update ac_ln_mst a set a.loan_sts = '04', a.sail_dt = ? where a.loan_sts in ('01', '02', '03') and a.loan_bal <= 0.00");
			updateLnMst2Pst.setString(1, operaInfo.getBizDt());
			//updateLnMst2Rs = updateLnMst2Pst.executeQuery();
			nums = updateLnMst2Pst.executeUpdate();
			log.info("形态转移--更新贷款主文件 结清标志  ,RES=="+nums);
			
		}catch(Exception e){
			log.error("形态转移异常:"+e.getMessage(),e);
			e.printStackTrace();
		}finally {
			if (updateLnLoPrcpStsPst != null) {
				updateLnLoPrcpStsPst.close();
			}
//			if (updateLnLoPrcpStsRs != null) {
//				updateLnLoPrcpStsRs.close();
//			}
			if (updateSetlStsPst != null) {
				updateSetlStsPst.close();
			}
			if (updateSetlStsRs != null) {
				updateSetlStsRs.close();
			}
			if (updateLnMstPst != null) {
				updateLnMstPst.close();
			}
//			if (updateLnMstRs != null) {
//				updateLnMstRs.close();
//			}
			if (updateLnMst2Pst != null) {
				updateLnMst2Pst.close();
			}
//			if (updateLnMst2Rs != null) {
//				updateLnMst2Rs.close();
//			}
			if (updateAcLnRepayPlnPst != null) {
				updateAcLnRepayPlnPst.close();
			}
		}
	}
	/**
	 * 更新主文件
	 * @param acLnMst
	 * @param st
	 * @param txBrNo
	 * @param txCde
	 * @param conn
	 * @throws AccountingException 
	 */
	private void dealMstAndDcLog(OperaInfo operaInfo, AcLnMst acLnMst, StsTransfer st, String txCde) throws AccountingException{
		AcLnRepayPlnCur acLnRepayPlnCur = (AcLnRepayPlnCur)JdbcDao.query(new AcLnRepayPlnCur(), " loan_no='"+acLnMst.getLoanNo()+"'", "ac_ln_repay_pln_cur", operaInfo.getConn());
		if(acLnMst!=null && (PUBConstant.LOAN_STS_01.equals(acLnMst.getLoanSts()) || PUBConstant.LOAN_STS_02.equals(acLnMst.getLoanSts()) || PUBConstant.LOAN_STS_03.equals(acLnMst.getLoanSts()))){
			//获取欠款表信息
			List<AcLnLo> allLnLoList = (ArrayList)JdbcDao.queryList(new AcLnLo(), "loan_no = '"+acLnMst.getLoanNo()+"' and setl_sts='N'","ac_ln_lo", operaInfo.getConn());
			if(allLnLoList.size()>0){//有欠款
				acLnMst.setLoanSts(PUBConstant.LOAN_STS_03);
			}else if(Integer.parseInt(acLnMst.getMtrDt())>Integer.parseInt(operaInfo.getBizDt())){//贷款到期日大于当前日期
				if(acLnRepayPlnCur != null){//若存在当期还款计划，则判断当期还款日是否小于当天营业日,若小于则更改状态为待逾期
					if(TimeUtil.checkDate1BeforeDate2(acLnRepayPlnCur.getPayDt(), operaInfo.getBizDt())){
						acLnMst.setLoanSts(PUBConstant.LOAN_STS_02);
					}else{
						//贷款形态正常
						acLnMst.setLoanSts(PUBConstant.LOAN_STS_01);
					}
				}else{
					//贷款形态正常
					acLnMst.setLoanSts(PUBConstant.LOAN_STS_01);
				}
				
			}else{
				//贷款到期且无欠款
				acLnMst.setLoanSts(PUBConstant.LOAN_STS_04);
				acLnMst.setSailDt(operaInfo.getBizDt());//结清日期
			}
			JdbcDao.executeUpdate("AC_LN_MST", "LOAN_STS='"+acLnMst.getLoanSts()+"' where loan_no='"+acLnMst.getLoanNo()+"'", operaInfo.getConn());
		}
	}
	/**
	 * 处理欠款信息:每期扣款失败后即转逾期,到期超过转表外期限即转表外
	 * @param acLnMst
	 * @param acLnLo
	 * @param st
	 * @param conn
	 * @throws AccountingException 
	 */
	private void dealLnLo3(AcLnMst acLnMst, AcLnLo acLnLo, StsTransfer st, OperaInfo operaInfo) throws AccountingException{

		//有拖欠本金
		if (NumberUtil.isAmtGreat(acLnLo.getPrcpAmt(), NumberUtil.amtAdd(acLnLo.getRepayPrcpAmt(), acLnLo.getWvPrcpAmt())) ) {
			acLnLo.setPrcpSts(PUBConstant.INT_STS_30);
			acLnLo.setSetlSts("N");
		}else {
			acLnLo.setPrcpSts(PUBConstant.INT_STS_40);
		}
			
		if(NumberUtil.isAmtGreat(acLnLo.getNormInt(), NumberUtil.amtAdd(acLnLo.getRepayNormInt(), acLnLo.getWvNormInt())) ){
			
			//利息逾期标志
			acLnLo.setIntSts(PUBConstant.INT_STS_30);
			acLnLo.setSetlSts("N");
		}else{
			acLnLo.setIntSts(PUBConstant.INT_STS_40);
		}
		
		if(NumberUtil.isAmtGreat(acLnLo.getFineInt(), NumberUtil.amtAdd(acLnLo.getRepayFineInt(),acLnLo.getWvFineInt()))){
			//罚息未还清
			acLnLo.setSetlSts("N");
		}else if(PUBConstant.INT_STS_40.equals(acLnLo.getPrcpSts()) && PUBConstant.INT_STS_40.equals(acLnLo.getIntSts())){
			acLnLo.setSetlSts("Y");
			acLnLo.setSetlDt(operaInfo.getBizDt());
		}else{
			acLnLo.setSetlSts("N");
		}
		//更新相关表:欠款表;还款计划
		this.updateInfo(acLnLo, operaInfo);
	}
	
	private void updateInfo(AcLnLo acLnLo,OperaInfo operaInfo) throws AccountingException {
		
		//更新欠款表记录
		JdbcDao.executeUpdate("AC_LN_LO", "prcp_sts='"+acLnLo.getPrcpSts()+"',int_sts='"+acLnLo.getIntSts()+"' , setl_sts='"+acLnLo.getSetlSts()+"' , setl_dt='"+acLnLo.getSetlDt()+"' where loan_no='"+acLnLo.getLoanNo()+"' and perd_no="+acLnLo.getPerdNo(), operaInfo.getConn());
		//更新对应的还款计划
		JdbcDao.executeUpdate("AC_LN_REPAY_PLN", "prcp_sts='"+acLnLo.getPrcpSts()+"',int_sts='"+acLnLo.getIntSts()+"' , setl_sts='"+acLnLo.getSetlSts()+"' where loan_no='"+acLnLo.getLoanNo()+"' and perd_no="+acLnLo.getPerdNo(), operaInfo.getConn());
	}
}
