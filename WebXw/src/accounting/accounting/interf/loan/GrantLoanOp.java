package accounting.interf.loan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import accounting.biz.pub.ParmBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcCorpParm;
import accounting.domain.loan.AcDebit;
import accounting.domain.loan.AcDebitDtl;
import accounting.domain.loan.AcLnDue;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLoanBackLog;
import accounting.domain.loan.AcLoanLog;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.loan.LnAcct;
import accounting.domain.loan.LnStage;
import accounting.domain.loan.PrdtBase;
import accounting.domain.loan.ProjAcct;
import accounting.domain.sys.AfferentDomain;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.dao.JdbcDao;
import app.base.CreateKey;
import app.base.SourceTemplate;
import app.creditapp.acc.loan.action.AcLoanBackLogAction;
import app.creditapp.inf.client.XMLUtil;
import app.creditapp.inf.client.ZFHead;
import app.creditapp.inf.client.zf.AccountInfo;
import app.creditapp.inf.client.zf.BodyLoan;
import app.creditapp.inf.client.zf.EntryInfo;
import app.creditapp.inf.client.zf.Exception_Exception;
import app.creditapp.inf.client.zf.SourceAccountInfo;
import app.creditapp.inf.client.zf.TargetAccountInfo;
import app.creditapp.inf.client.zf.TradeInfo;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.redis.util.RedisUtil;
import app.oscache.CachecodeUtil;
import redis.clients.jedis.Jedis;


public class GrantLoanOp extends Operation {
	/**
	 * 贷款放款操作类 
	 * @param afferentDomain     贷款放款数据对象
	 * @return  
	 */
	public Object doExecute(AfferentDomain afferentDomain)throws AccountingException{
		GrantLoan gl;
		if (afferentDomain instanceof GrantLoan) {
			gl = (GrantLoan) afferentDomain;
		} else {
			throw new AccountingException("参数类型不匹配！");
		}
		
		Connection conn = this.getConnection();
		GrantLoanBackResult grantLoanBackResult = new GrantLoanBackResult();
		//获取放款支付渠道
		PreparedStatement queryLnApplyMidPst = null;
		ResultSet queryLnApplyMidRs = null;
		try {
		//得到业务参数
		String traceNo = ParmBiz.getTraceNo(conn);
		String loanNo = gl.getLoanNo();	
//		List<AcChrgLog> acChrgLogList = gl.getAcChrgLogList();
		
		// 设置业务操作对象
		OperaInfo operaInfo = new OperaInfo(afferentDomain, traceNo, conn);
		operaInfo.setBizDt(ParmBiz.getBizDt(conn));//核算交易日期
		
		// 生成贷款主表信息
//		AcLnDue acLnDue = (AcLnDue)JdbcDao.query(new AcLnDue(), "due_no='"+loanNo+"'", "ln_due", operaInfo.getConn());
		LnDueBo lnDueBo = (LnDueBo) SourceTemplate.getContext().getBean("lnDueBo");
		LnDue acLnDue = new LnDue();
		acLnDue.setDueNo(loanNo);
		acLnDue =lnDueBo.getById(acLnDue);
		//进件申请表，生成贷款主文件需要更新mst_sts字段
		LnStage lnStage = (LnStage)JdbcDao.query(new LnStage(), "app_id='"+acLnDue.getAppId()+"'", "ln_stage", operaInfo.getConn());
		
		String cardChn = "";//虚拟户支付渠道
		
		queryLnApplyMidPst = conn.prepareStatement("select card_chn from ln_apply_mid where app_id=?");
		queryLnApplyMidPst.setString(1, acLnDue.getAppId());
		queryLnApplyMidRs = queryLnApplyMidPst.executeQuery();
		if(queryLnApplyMidRs.next()){
		cardChn = queryLnApplyMidRs.getString("card_chn");			
		}
		PrdtBase prdtBase = (PrdtBase) JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnDue.getPrdtNo()+"'", "PRDT_BASE", conn);

		if (prdtBase == null) {
			throw new AccountingException("查询不到相应产品表信息!");
		}
		// 生成贷款主表信息
		AcLnMst acLnMst = this.getLnMst(prdtBase, acLnDue, operaInfo);
		
		//业务交易类型
		String busOrderType = "001";

		//生成放款信息
		List<AcLoanLog> acLoanLogList = this.getAcLoanLogList(acLnDue,cardChn,operaInfo,busOrderType,acLnDue.getProjNo());
		JdbcDao.insertList(acLoanLogList, "ac_loan_log", conn);
		
		//生成发送支付及反馈信息
		List<AcLoanBackLog> acLoanBackLogList = new ArrayList<AcLoanBackLog>();
		
//		String backLogNo = ParmBiz.getBackLoanNo(conn);//发送支付及反馈流水号
		String backLogNo = ParmBiz.getAcLoanBackLogNo(conn);//发送支付及反馈流水号
		
		String uUID = CreateKey.getUUID();//接口流水号uUID
		//每条放款信息对应一笔支付流水
		
		for(AcLoanLog acLoanLog : acLoanLogList){
			AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			acLoanBackLog.setBackLogNo(backLogNo);
			acLoanBackLog.setBackCnt(ParmBiz.getBackCntSeq(conn));
			acLoanBackLog.setLoanLogNo(acLoanLog.getLoanLogNo());
			acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_01);//放款
			acLoanBackLog.setBackRes("");
			acLoanBackLog.setFailCaus("");
			acLoanBackLog.setBackSts(PUBConstant.BACK_STS_01);//待处理
			acLoanBackLog.setBusOrderType(busOrderType);
			acLoanBackLog.setBusEntryType(acLoanLog.getBusEntryType());
			acLoanBackLog.setTraceNo(acLoanLog.getTraceNo());
			acLoanBackLog.setUuid(CachecodeUtil.MFSPREFIX+uUID);
			acLoanBackLogList.add(acLoanBackLog);
		}

		acLnMst.setLstDt(operaInfo.getBizDt());			//设置上笔发生日期“当前营业日期”

 		JdbcDao.insert(acLnMst, "ac_ln_mst", conn);
 		
 		//更新进件申请表
 		lnStage.setMstSts("02");
 		lnStage.setUpTime(ParmBiz.getOracleUpDate(conn));
 		JdbcDao.update(lnStage, "app_id='"+lnStage.getAppId()+"'", "ln_stage", conn);
 		JdbcDao.insertList(acLoanBackLogList, "ac_loan_back_log", conn);
		//获得登记交易流水
		AcTraceLog traceLog = this.getTraceLog(operaInfo, acLnMst);
		JdbcDao.insert(traceLog, "AC_TRACE_LOG", conn);
		try {
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}

		acLnMst.setDealSts("02");
		JdbcDao.update(acLnMst, "loan_no='"+acLnMst.getLoanNo()+"' and deal_sts='01'", "ac_ln_mst", conn);
			
		//更新阶段表为已发送
		lnStage.setSendSts("02");
		lnStage.setRsDesc("已发送支付，等待放款");
		JdbcDao.update(lnStage, "app_id='"+lnStage.getAppId()+"' and send_sts='01'", "ln_stage", conn);		

		grantLoanBackResult.setAcLoanBackLogList(acLoanBackLogList);
		grantLoanBackResult.setUuid(CachecodeUtil.MFSPREFIX+uUID);
		
		try {
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grantLoanBackResult;
	}
	/**
	 * 获得贷款主表信息
	 * 
	 * @throws AccountingException
	 */
	private AcLnMst getLnMst(PrdtBase prdtBase,LnDue acLnDue,OperaInfo operaInfo) throws AccountingException {
		AcLnMst lnMst = new AcLnMst();
		
		AcCorpParm acCorpParm = (AcCorpParm)JdbcDao.query(new AcCorpParm(), "br_no='"+acLnDue.getBrNo()+"'", "corp_parm", operaInfo.getConn());

		lnMst.setLoanNo(acLnDue.getDueNo());
		lnMst.setBrNo(acLnDue.getBrNo());
		lnMst.setPactNo(acLnDue.getPactNo());
		lnMst.setBrNo(acLnDue.getBrNo());
		lnMst.setProjNo(acLnDue.getProjNo());
		lnMst.setBatchNo(acLnDue.getBatchNo());
		lnMst.setPrdtNo(acLnDue.getPrdtNo());
		lnMst.setBrAccType(acLnDue.getBrAccType());
		lnMst.setCifNo(acLnDue.getCifNo());
		lnMst.setCifName(acLnDue.getCifName());
		lnMst.setLoanTyp(acLnDue.getLnType());
		lnMst.setAssureMeans(acLnDue.getVouType());
		//若贷款期限日不等于0，则贷款期限设定为 贷款期限月+1 ，若期限日等于0，则贷款期限设定为贷款期限月
		lnMst.setLoanTerm(acLnDue.getTermDay()==0?acLnDue.getTermMon():(acLnDue.getTermMon()+1));
//		lnMst.setOpnDt(acLnDue.getBegDate());
//		lnMst.setMtrDt(acLnDue.getEndDate());
		lnMst.setTermDay(acLnDue.getTermDay());
		lnMst.setTermMon(acLnDue.getTermMon());
		lnMst.setTermTyp(acLnDue.getTermType());
		lnMst.setCurNo(acLnDue.getCurNo());
		lnMst.setLoanAmt(acLnDue.getDueAmt());
		lnMst.setLoanBal(0.00);//未放款成功，设置贷款余额为0
		lnMst.setLnRate(acLnDue.getLnRate());
		lnMst.setLnRateYear(acLnDue.getLnRateYear());
		lnMst.setOverRate(prdtBase.getOverRate());
//		lnMst.setIcDt(acLnDue.getBegDate());
		lnMst.setRepayDay(acLnDue.getPayDay());
		lnMst.setExpInd("0");
		
		lnMst.setGraceDay(acCorpParm.getGraceDay());
		
		lnMst.setDelqInd(PUBConstant.DELQ_IND_NO); 
		lnMst.setDevaInd(PUBConstant.DEVA_IND_N); 
		lnMst.setDealSts(PUBConstant.DEAL_STS_PEND);
		lnMst.setIntToStpInd(PUBConstant.N);
		lnMst.setFiveSts(PUBConstant.FIVE_STS_1ST); 
		lnMst.setYsBal(0.00);
		lnMst.setHstBal(0.00);
		lnMst.setTtlPrvdAmt(acLnDue.getDueAmt());
		lnMst.setUpDt(operaInfo.getTxDt());
		return lnMst;
	}
	/**
	 *  
	 * @描述	获取放款信息，根据借据号查询账户表账户用途为放款账户的记录，根据该记录生成放款信息
	 */
	private List<AcLoanLog> getAcLoanLogList(LnDue acLnDue ,String cardChn, OperaInfo operaInfo,String busOrderType,String projNo) throws AccountingException{
		Connection conn = operaInfo.getConn() ;
		DecimalFormat df = new DecimalFormat("00");
		//账户信息表（账户用途为放款的）
		List<LnAcct> lnAcctList = (ArrayList) JdbcDao.queryList(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='2' order by ac_type desc" , "ln_acct", operaInfo.getConn());
		int bus = 1;
		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		for(int i=0;i<lnAcctList.size();i++){
			AcLoanLog acLoanLog = new AcLoanLog();
			acLoanLog.setTraceNo(operaInfo.getTraceNo());
			acLoanLog.setLoanLogNo(ParmBiz.getLoanLogNo(conn));
			acLoanLog.setLoanNo(acLnDue.getDueNo());
			acLoanLog.setPactNo(acLnDue.getPactNo());
			acLoanLog.setBrNo(acLnDue.getBrNo());
			acLoanLog.setLoanAmt(lnAcctList.get(i).getAcAmt());
//			if(projAcct == null){
////				acLoanLog.setXtAcNo("");
//				acLoanLog.setCardChn("");
//			}else{
			acLoanLog.setCardChn(cardChn);
//			}
			acLoanLog.setXtAcNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_03+"_"+projNo);
			acLoanLog.setLoanAcNo(lnAcctList.get(i).getAcNo());
			acLoanLog.setLoanAcType(lnAcctList.get(i).getAcType());
			acLoanLog.setLoanAcName(lnAcctList.get(i).getAcName());
			acLoanLog.setLoanBankCode(lnAcctList.get(i).getBankCode());
			acLoanLog.setLoanBankProv(lnAcctList.get(i).getBankProv());
			acLoanLog.setLoanBankCity(lnAcctList.get(i).getBankCity());
			acLoanLog.setLoanBankSite(lnAcctList.get(i).getBankSite());
			acLoanLog.setLoanSts("02");//已发送
			acLoanLog.setTxDate(operaInfo.getTxDt());
			acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			acLoanLog.setBusEntryType(busOrderType+String.valueOf(df.format(bus)));
			bus+=1;
			acLoanLog.setIdType(lnAcctList.get(i).getIdType());
			acLoanLog.setIdNo(lnAcctList.get(i).getIdNo());
			acLoanLog.setPhoneNo(lnAcctList.get(i).getPhoneNo());
			acLoanLog.setValidDate(lnAcctList.get(i).getValidDate());
			acLoanLog.setCvvNo(lnAcctList.get(i).getCvvNo());
			acLoanLogList.add(acLoanLog);
		}
		return acLoanLogList;
	}
	/**
	 * 
	 * @throws AccountingException 
	 * @throws Exception_Exception 
	 * @throws IOException 
	 * @throws SQLException 
	 * 
	 * @描述	发送第三方支付放款
	 */
	private Map sendZfMes(List<AcLoanBackLog> acLoanBackLogList,String projNo,String traceNo,Connection conn) throws AccountingException, Exception_Exception, IOException, SQLException {
//		String backLogNo = acLoanBackLogList.get(0).getBackLogNo();
		ZFHead zfHead = new ZFHead();
		zfHead.setRequestType("P100001");
		String uuid = acLoanBackLogList.get(0).getUuid();
		zfHead.setUUID(uuid);//接口流水号
		zfHead.setComId(CachecodeUtil.MFSPREFIX);
		zfHead.setComIP("11");
		zfHead.setSendTime(ParmBiz.getOracleUpDate2(conn));
		zfHead.setAsyn("0");
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties"));
		String zfReturnUrl = pathProperties.getProperty("zfloanreturn.url");
		zfHead.setReturnUrl(zfReturnUrl);
		zfHead.setSigned("");
		zfHead.setClientType("0");
		zfHead.setPayCount(1);

		String headXml = XMLUtil.createHead(zfHead); // 头文件
//		ProjAcct projAcct = (ProjAcct)JdbcDao.query(domain, condition, tableName, conn)
		
		BodyLoan bodyLoan = new BodyLoan();
		
		TradeInfo tradeInfo = new TradeInfo();
//		String TradeNo = ParmBiz.getOrderNoSeq(conn);
		tradeInfo.setTradeNo(CachecodeUtil.MFSPREFIX+traceNo);//交易号
		tradeInfo.setTradeTypeNo(acLoanBackLogList.get(0).getBusOrderType());//交易业务类型
		tradeInfo.setTotalEntryNum(acLoanBackLogList.size());//条目数量
		
		List<TradeInfo> tradeList = new ArrayList<TradeInfo>();
		EntryInfo entryInfo = null;
		List<EntryInfo> entryList = new ArrayList<EntryInfo>();
		
		//放款信息
		AcLoanLog acLoanLog = null;
		//放款信息List
		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		//扣款信息
		AcDebit acDebit = null;
		//扣款信息list
		List<AcDebit> acDebitList = new ArrayList<AcDebit>();
		//账户信息
		AccountInfo accountInfo = null;
		
		SourceAccountInfo sourceAccountInfo = null;//源
		TargetAccountInfo targetAccountInfo = null;//目的
		
		for (AcLoanBackLog acLoanBackLog : acLoanBackLogList) {
			if(PUBConstant.BACK_STS_01.equals(acLoanBackLog.getBackType())){//放款
				//获取放款信息
				acLoanLog = (AcLoanLog) JdbcDao.query(new AcLoanLog(), "loan_log_no='"+acLoanBackLog.getLoanLogNo()+"'", "ac_loan_log", conn);
				acLoanLogList.add(acLoanLog);
				
				entryInfo = new EntryInfo();
				
				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());//条目号
				entryInfo.setEnTradeType("01");
				entryInfo.setAmount(acLoanLog.getLoanAmt());
				entryInfo.setEntryTypeNo(acLoanBackLog.getBusEntryType());
				entryInfo.setChannelNo(acLoanLog.getCardChn());
				
				List<SourceAccountInfo> sourceAccountInfoListfk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListfk = new ArrayList<TargetAccountInfo>();
				
				sourceAccountInfo = new SourceAccountInfo();//源1
				
				accountInfo = new AccountInfo();
				accountInfo.setAccountNo(acLoanLog.getXtAcNo());//放款 03，收款 04
				accountInfo.setAccountType("0");
				
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListfk.add(sourceAccountInfo);
				
				targetAccountInfo = new TargetAccountInfo();//目的
				
				accountInfo = new AccountInfo();
				accountInfo.setAccountNo(acLoanLog.getLoanAcNo());
				if("10".equals(acLoanLog.getLoanAcType())){//个人贷记卡
					accountInfo.setAccountType("3");
				}else if("11".equals(acLoanLog.getLoanAcType())){//个人借记卡
					accountInfo.setAccountType("1");
				}
//				else if("12".equals(acLoanLog.getLoanAcType())){//企业账户
//					accountInfo.setAccountType("2");
//				}
//				else if("14".equals(acLoanLog.getLoanAcType())){//商户
//					accountInfo.setAccountType("0");
//				}
				if("14".equals(acLoanLog.getLoanAcType())||"12".equals(acLoanLog.getLoanAcType())){//商户或对公
					accountInfo.setAccountNo(acLoanLog.getLoanAcNo());//(商户或对公)放款账户
					targetAccountInfo.setLevel("01");
					accountInfo.setAccountType("0");
					targetAccountInfo.setAccountInfo(accountInfo);
					targetAccountInfoListfk.add(targetAccountInfo);
					entryInfo.setSourceAccountList(sourceAccountInfoListfk);
					entryInfo.setTargetAccountList(targetAccountInfoListfk);
					entryInfo.setRemark1(acLoanLog.getBrNo());
					entryInfo.setRemark2(projNo);
					entryInfo.setRemark3(acLoanLog.getPactNo());
					entryList.add(entryInfo);
				}else{
					accountInfo.setAccountName(acLoanLog.getLoanAcName());
					accountInfo.setChannelNo(acLoanLog.getLoanBankCode());
					accountInfo.setBankDetailNo("");
					accountInfo.setBankName(acLoanLog.getLoanBankSite());
					accountInfo.setProvince(acLoanLog.getLoanBankProv());
					accountInfo.setCity(acLoanLog.getLoanBankCity()!=null?acLoanLog.getLoanBankCity():"");
					accountInfo.setCVN2(acLoanLog.getCvvNo()!=null?acLoanLog.getCvvNo():"");
					accountInfo.setVALDATE(acLoanLog.getValidDate()!=null?acLoanLog.getValidDate():"");
					accountInfo.setPhoneNo(acLoanLog.getPhoneNo()!=null?acLoanLog.getPhoneNo():"");
					accountInfo.setEmail("");
					accountInfo.setCertificateType(acLoanLog.getIdType()!=null?acLoanLog.getIdType():"");
					accountInfo.setCertificateNo(acLoanLog.getIdNo()!=null?acLoanLog.getIdNo():"");
					targetAccountInfo.setLevel("01");
					targetAccountInfo.setAccountInfo(accountInfo);
					targetAccountInfoListfk.add(targetAccountInfo);
					entryInfo.setSourceAccountList(sourceAccountInfoListfk);
					entryInfo.setTargetAccountList(targetAccountInfoListfk);
					entryInfo.setRemark1(acLoanLog.getBrNo());
					entryInfo.setRemark2(projNo);
					entryInfo.setRemark3(acLoanLog.getPactNo());
					entryList.add(entryInfo);
				}	
			}else{
				acDebit = (AcDebit)JdbcDao.query(new AcDebit(), "debit_no='"+acLoanBackLog.getLoanLogNo()+"'", "ac_debit", conn);
				acDebitList.add(acDebit);
				
				entryInfo = new EntryInfo();
				
				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());//条目号
				entryInfo.setEnTradeType("02");//扣款
				entryInfo.setAmount(acDebit.getAtpyAmt());
				entryInfo.setEntryTypeNo(acDebit.getBusEntryType());
				entryInfo.setChannelNo(acDebit.getCardChn());
				
				List<SourceAccountInfo> sourceAccountInfoListsk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListsk = new ArrayList<TargetAccountInfo>();
				
				sourceAccountInfo = new SourceAccountInfo();//源1
				targetAccountInfo = new TargetAccountInfo();//目的
				
				accountInfo = new AccountInfo();
				
				accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+acDebit.getBrNo()+"_"+acDebit.getPactNo());//支付平台账户
				accountInfo.setAccountType("0");
				sourceAccountInfo.setLevel("01");//优先级
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);
				
				sourceAccountInfo = new SourceAccountInfo();//源2
				
				accountInfo = new AccountInfo();
				
				accountInfo.setAccountNo(acDebit.getAcNo());
				if("10".equals(acDebit.getAcType())){//个人贷记卡
					accountInfo.setAccountType("3");
				}else if("11".equals(acDebit.getAcType())){//个人借记卡
					accountInfo.setAccountType("1");
				}else if("12".equals(acDebit.getAcType())||"13".equals(acDebit.getAcType())||"14".equals(acDebit.getAcType())){//企业账户（自如）
					accountInfo.setAccountType("0");
				}
				accountInfo.setAccountName(acDebit.getAcName());
				accountInfo.setChannelNo(acDebit.getAcctBankCde());
				accountInfo.setBankDetailNo("");
				accountInfo.setBankName(acDebit.getBankSite());
				accountInfo.setProvince(acDebit.getBankProv());
				accountInfo.setCity(acDebit.getBankCity());
				accountInfo.setCVN2(acDebit.getCvvNo()!=null?acDebit.getCvvNo():"");
				accountInfo.setVALDATE(acDebit.getValidDate()!=null?acDebit.getValidDate():"");
				accountInfo.setPhoneNo(acDebit.getPhoneNo()!=null?acDebit.getPhoneNo():"");
				accountInfo.setEmail("");
				accountInfo.setCertificateType(acDebit.getIdType()!=null?acDebit.getIdType():"");
				accountInfo.setCertificateNo(acDebit.getIdNo()!=null?acDebit.getIdNo():"");
				sourceAccountInfo.setLevel("02");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);
				
				accountInfo = new AccountInfo();
				
				accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_04+"_"+projNo);//项目收款账户
				accountInfo.setAccountType("0");
				targetAccountInfo.setLevel("01");
				targetAccountInfo.setAccountInfo(accountInfo);
				targetAccountInfoListsk.add(targetAccountInfo);
				entryInfo.setSourceAccountList(sourceAccountInfoListsk);
				entryInfo.setTargetAccountList(targetAccountInfoListsk);
				entryInfo.setRemark1(acDebit.getBrNo());
				entryInfo.setRemark2(projNo);
				entryInfo.setRemark3(acDebit.getPactNo());
				entryList.add(entryInfo);
			}
			
		}
		tradeInfo.setEntryList(entryList);
		tradeList.add(tradeInfo);

		bodyLoan.setTradeList(tradeList);
		String bodyXml = XMLUtil.createBody(bodyLoan);// 转换为XML

		String xml = headXml + bodyXml + "</Package>";
		
		Jedis jedis = RedisUtil.getJedis();
		jedis.lpush(RedisUtil.QUEUE8, xml);// 
		jedis.close();
		
		return null ;
	}
	
	 /**

     * @description 将xml字符串转换成map

     * @param xml

     * @return Map

     */

	public static Map readStringXmlOut(String xml) {

		Map map = new HashMap();

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			Iterator iter = rootElt.elementIterator("Header"); // 获取根节点下的子节点header

			Element recordEle = (Element) iter.next();

			String RequestType = recordEle.elementTextTrim("RequestType");
			map.put("RequestType", RequestType);

			String UUID = recordEle.elementTextTrim("UUID");
			map.put("UUID", UUID);

			String ComId = recordEle.elementTextTrim("ComId");
			map.put("ComId", ComId);

			String SendTime = recordEle.elementTextTrim("SendTime");
			map.put("SendTime", SendTime);

			String Signed = recordEle.elementTextTrim("Signed");
			map.put("Signed", Signed);

			String ResultCode = recordEle.elementTextTrim("ResultCode");
			map.put("ResultCode", ResultCode);

			String ResultMsg = recordEle.elementTextTrim("ResultMsg");
			map.put("ResultMsg", ResultMsg);

		} catch (DocumentException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return map;

	}


	/**
	 *  
	 * 
	 * @描述	获取放款信息，根据借据号查询账户表账户用途为放款账户的记录，根据该记录生成放款信息
	 */
	private List<AcLoanLog> getAcLoanLogList(AcLnDue acLnDue ,ProjAcct projAcct, OperaInfo operaInfo,String busOrderType) throws AccountingException{
		Connection conn = operaInfo.getConn() ;
		//账户信息表（账户用途为放款的）
		List<LnAcct> lnAcctList = (ArrayList) JdbcDao.queryList(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='2'" , "ln_acct", operaInfo.getConn());
		
		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		for(int i=0;i<lnAcctList.size();i++){
			AcLoanLog acLoanLog = new AcLoanLog();
			acLoanLog.setTraceNo(operaInfo.getTraceNo());
			acLoanLog.setLoanLogNo(ParmBiz.getAcLoanLogNo(conn));
			acLoanLog.setLoanNo(acLnDue.getDueNo());
			acLoanLog.setPactNo(acLnDue.getPactNo());
			acLoanLog.setBrNo(acLnDue.getBrNo());
			acLoanLog.setLoanAmt(lnAcctList.get(i).getAcAmt());
			if(projAcct == null){
				acLoanLog.setXtAcNo("");
				acLoanLog.setCardChn("");
			}else{
				acLoanLog.setXtAcNo(projAcct.getAcctNo());
				acLoanLog.setCardChn(projAcct.getCardChn());
			}
			acLoanLog.setLoanAcNo(lnAcctList.get(i).getAcNo());
			acLoanLog.setLoanAcType(lnAcctList.get(i).getAcType());
			acLoanLog.setLoanAcName(lnAcctList.get(i).getAcName());
			acLoanLog.setLoanBankCode(lnAcctList.get(i).getBankCode());
			acLoanLog.setLoanBankProv(lnAcctList.get(i).getBankProv());
			acLoanLog.setLoanBankCity(lnAcctList.get(i).getBankCity());
			acLoanLog.setLoanBankSite(lnAcctList.get(i).getBankSite());
			acLoanLog.setLoanSts("01");//待处理
			acLoanLog.setTxDate(operaInfo.getTxDt());
			acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			acLoanLog.setBusEntryType(busOrderType+"01");
			acLoanLog.setIdType(lnAcctList.get(i).getIdType());
			acLoanLog.setIdNo(lnAcctList.get(i).getIdNo());
			acLoanLog.setPhoneNo(lnAcctList.get(i).getPhoneNo());
			acLoanLog.setValidDate(lnAcctList.get(i).getValidDate());
			acLoanLog.setCvvNo(lnAcctList.get(i).getCvvNo());
			
			acLoanLogList.add(acLoanLog);
		}
		return acLoanLogList;
	}
	/**
	 * 获得贷款主表信息
	 * 
	 * @throws AccountingException
	 */
	private AcLnMst getLnMst(PrdtBase prdtBase,AcLnDue acLnDue,OperaInfo operaInfo) throws AccountingException {
		AcLnMst lnMst = new AcLnMst();
		
		AcCorpParm acCorpParm = (AcCorpParm)JdbcDao.query(new AcCorpParm(), "br_no='"+acLnDue.getBrNo()+"'", "corp_parm", operaInfo.getConn());

		lnMst.setLoanNo(acLnDue.getDueNo());
		lnMst.setBrNo(acLnDue.getBrNo());
		lnMst.setPactNo(acLnDue.getPactNo());
		lnMst.setBrNo(acLnDue.getBrNo());
		lnMst.setProjNo(acLnDue.getProjNo());
		lnMst.setBatchNo(acLnDue.getBatchNo());
		lnMst.setPrdtNo(acLnDue.getPrdtNo());
		lnMst.setBrAccType(acLnDue.getBrAccType());
		lnMst.setCifNo(acLnDue.getCifNo());
		lnMst.setCifName(acLnDue.getCifName());
		lnMst.setLoanTyp(acLnDue.getLnType());
		lnMst.setAssureMeans(acLnDue.getVouType());
		//若贷款期限日不等于0，则贷款期限设定为 贷款期限月+1 ，若期限日等于0，则贷款期限设定为贷款期限月
		lnMst.setLoanTerm(acLnDue.getTermDay()==0?acLnDue.getTermMon():(acLnDue.getTermMon()+1));
//		lnMst.setOpnDt(acLnDue.getBegDate());
//		lnMst.setMtrDt(acLnDue.getEndDate());
		lnMst.setTermDay(acLnDue.getTermDay());
		lnMst.setTermMon(acLnDue.getTermMon());
		lnMst.setTermTyp(acLnDue.getTermType());
		lnMst.setCurNo(acLnDue.getCurNo());
		lnMst.setLoanAmt(acLnDue.getDueAmt());
		lnMst.setLoanBal(0.00);//未放款成功，设置贷款余额为0
		lnMst.setLnRate(acLnDue.getLnRate());
		lnMst.setOverRate(prdtBase.getOverRate());
//		lnMst.setIcDt(acLnDue.getBegDate());
		lnMst.setRepayDay(acLnDue.getPayDay());
		lnMst.setExpInd("0");
		if(acCorpParm != null){
			lnMst.setGraceDay(acCorpParm.getGraceDay());
		}
		lnMst.setDelqInd(PUBConstant.DELQ_IND_NO); 
		lnMst.setDevaInd(PUBConstant.DEVA_IND_N); 
		lnMst.setDealSts(PUBConstant.DEAL_STS_PEND);
//		lnMst.setLoanSts(PUBConstant.LOAN_STS_ACTV); 
		lnMst.setIntToStpInd(PUBConstant.N);
		lnMst.setFiveSts(PUBConstant.FIVE_STS_1ST); 
		lnMst.setYsBal(0.00);
		lnMst.setHstBal(0.00);
		lnMst.setTtlPrvdAmt(acLnDue.getDueAmt());
		lnMst.setUpDt(operaInfo.getTxDt());

		return lnMst;
	}
	
	/**
	 * 得到登记交易流水表信息
	 * @param operaInfo 业务操作对象
	 * @param lnMst 贷款主表对象
	 * 
	 * @return traceLog    登记交易流水表对象
	 * @throws AccountingException
	 */
	private AcTraceLog getTraceLog(OperaInfo operaInfo,AcLnMst lnMst)throws AccountingException {
		AcTraceLog traceLog = new AcTraceLog();
		
		Connection conn = operaInfo.getConn() ;

		traceLog.setTraceNo(operaInfo.getTraceNo());//设置流水号
		traceLog.setTraceCnt(1);					//设置流水笔次
		traceLog.setTxDt(operaInfo.getTxDt());		//设置交易日期
    	traceLog.setTxTime(ParmBiz.getOracleTxTime(conn)) ;	//设置交易时间
		traceLog.setTxBrNo(operaInfo.getTxBrNo());	//设置交易机构号
		traceLog.setTxCde(TransCode.LNC3);			//设置交易代码
		traceLog.setSubTxCde(TransCode.LNC3);		//设置子交易代码
		traceLog.setCurNo(lnMst.getCurNo());		//设置币种
		traceLog.setPrdtNo(lnMst.getPrdtNo());		//设置产品编号
		traceLog.setLoanNo(lnMst.getLoanNo());		//设置借据号
		traceLog.setPactNo(lnMst.getPactNo());		//设置合同号
		traceLog.setBrNo(lnMst.getBrNo());			//设置机构号
		traceLog.setAddInd(PUBConstant.INC);		//设置增减标志为“增加”
		traceLog.setCtInd(PUBConstant.TRANS);		//设置现转标志为“转账”
		traceLog.setCancelInd(PUBConstant.REV_ROL_NORM);// 设置撤销标志		
		traceLog.setAmt(lnMst.getLoanAmt());		//设置发生额
		traceLog.setMemo("贷款放款");					//设置摘要
		
		return traceLog;
	}
	
	/**
	 * 
	 * @throws AccountingException 
	 * 
	 * @描述	根据费用明细 生成扣款信息，主要在放款环节 若有趸缴利息，则需要发送扣款指令
	 */
	public Map<String,Object>  getAcDDL(AcLnMst acLnMst,List<AcChrgLog> acChrgLogList,ProjAcct virProjAcct,OperaInfo operaInfo) throws AccountingException{
		List<AcDebitDtl> acDebitDtlList = new ArrayList<AcDebitDtl>();
		
		Connection conn = operaInfo.getConn();
		
	 	Map<String,Object> acDebitDtlMap = new HashMap<String, Object>();

		//借据信息
		AcLnDue acLnDue = (AcLnDue) JdbcDao.query(new AcLnDue(), "due_no='"+acLnMst.getLoanNo()+"'", "ln_due", operaInfo.getConn());

		//扣款账户信息表
		List<LnAcct> lnAcctList = (ArrayList) JdbcDao.queryList(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='1'", "ln_acct", operaInfo.getConn());
		
		double myFeeAmt = 0.00;//自收费
		double otherFeeAmt = 0.00;//代收费

		for(int i=0;i<acChrgLogList.size();i++){
			AcDebitDtl acDebitDtl = new AcDebitDtl();
			//费用配置信息
			if("01".equals(acChrgLogList.get(i).getFeeKind())){//自收费用
				myFeeAmt = myFeeAmt+acChrgLogList.get(i).getChrgAmt();
			}else if("02".equals(acChrgLogList.get(i).getFeeKind())){//代收费用
				otherFeeAmt = otherFeeAmt+acChrgLogList.get(i).getChrgAmt();
			}
			if(virProjAcct!=null){
				acDebitDtl.setXtAcNo(virProjAcct.getAcctNo());//信托账号
			}

			acDebitDtl.setTraceNo(operaInfo.getTraceNo());//主机流水号
			acDebitDtl.setDdtlNo(ParmBiz.getAcDebitDtlNo(conn));//扣款明细流水号
			acDebitDtl.setLoanNo(acLnMst.getLoanNo());//借据号
			acDebitDtl.setPactNo(acLnMst.getPactNo());//合同号
			acDebitDtl.setBrNo(acLnMst.getBrNo());//机构号
			acDebitDtl.setPerdNo(acChrgLogList.get(i).getPerdNo());
			acDebitDtl.setDdtlPrcpAmt(0.00);//实扣本金
			acDebitDtl.setDdtlNormInt(0.00);//实扣利息
			acDebitDtl.setDdtlFineInt(0.00);//实扣罚息
			acDebitDtl.setDdtlFeeAmt(acChrgLogList.get(i).getChrgAmt());//实扣费用总计
			acDebitDtl.setDdtlAmt(acChrgLogList.get(i).getChrgAmt());//实扣总金额
			if(lnAcctList.size()>0){
				acDebitDtl.setDdtlAcNo(lnAcctList.get(0).getAcNo());//扣款账号
				acDebitDtl.setDdtlAcName(lnAcctList.get(0).getAcName());//账户户名
				acDebitDtl.setDdtlBankCode(lnAcctList.get(0).getBankCode());//开户银行代码
				acDebitDtl.setDdtlBankProv(lnAcctList.get(0).getBankProv());//开户银行所在省
				acDebitDtl.setDdtlBankCity(lnAcctList.get(0).getBankCity());//开户银行所在市
				acDebitDtl.setDdtlBankSite(lnAcctList.get(0).getBankSite());//开户银行网点
				acDebitDtl.setDdtlSts(PUBConstant.DDTL_STS_PEND);//待处理
				acDebitDtl.setTxDate(operaInfo.getTxDt());//登记日期
				acDebitDtl.setTxTime(ParmBiz.getOracleTxTime(conn));//登记时间
			}else{
				throw new AccountingException("查询不到借据号为:"+acLnMst.getLoanNo()+"的扣款账户！");
			}
			acDebitDtlList.add(acDebitDtl);
		}
		
		acDebitDtlMap.put("acDebitDtlList", acDebitDtlList);
		acDebitDtlMap.put("myFeeAmt", myFeeAmt);
		acDebitDtlMap.put("otherFeeAmt", otherFeeAmt);
		
		return acDebitDtlMap ;
	}
}