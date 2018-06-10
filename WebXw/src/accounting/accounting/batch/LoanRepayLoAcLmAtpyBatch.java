/**
 * 
 */
package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import accounting.batch.run.Main;
import accounting.biz.pub.ParmBiz;
import accounting.domain.loan.PrdtBase;
import accounting.plat.PUBConstant;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TimeUtil;

/**
 * 贷款批扣（欠款）数据准备
 *
 */
public class LoanRepayLoAcLmAtpyBatch extends Batch {

	
	/* (non-Javadoc)
	 * @see accounting.batch.Batch#doBatch(java.lang.String, java.lang.String)
	 */
	public boolean doBatch(String startOrg, String endOrg) throws Exception {
		String step="9";//第9步

		boolean batchFlag = false;
		Connection conn = null; // 数据库连接
		try {
			conn = this.getConnection();
			
			String batchDt = "";//当前批量日期
			PreparedStatement selectSysGlobalPst = conn.prepareStatement("select sys_date from sys_global ");
			
			ResultSet selectSysGlobalRs = selectSysGlobalPst.executeQuery();
			if(selectSysGlobalRs.next()){
				batchDt = selectSysGlobalRs.getString("sys_date");
			}
			
			String batchStp = "";//当前批量成功运行步骤
			PreparedStatement selectAcComSysParmPst = conn.prepareStatement("select batch_stp from ac_com_sys_parm where batch_dt='"+batchDt+"'");
			
			ResultSet selectAcComSysParmRs = selectAcComSysParmPst.executeQuery();
			if(selectAcComSysParmRs.next()){
				batchStp = selectAcComSysParmRs.getString("batch_stp");
			}
			
			//若批量步骤小于10则说明当前该步骤还未处理
			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {
				
			String traceNo = "";
			String txDt = this.getTxDt(conn); // 获取当前日期
			int num = 1;
			/*
			 * 查询已发送批扣记录 
			 */
			PreparedStatement selectAcDebitPst = conn.prepareStatement("select loan_no from ac_debit where loan_no=? and sts='02'");
			ResultSet selectAcDebitRs = null;

			PreparedStatement deleteAcDebitPst = conn.prepareStatement("delete from ac_debit where sts='01'");
			deleteAcDebitPst.executeUpdate();
			/*
			 * 查询欠款表group by loan_no--贷款状态为正常、待逾期、逾期 
			 */
			PreparedStatement selectAcLnLoTtlPst = conn.prepareStatement("select loan_no, sum(prcp_amt-repay_prcp_amt-wv_prcp_amt)+sum(norm_int-repay_norm_int-wv_norm_int)+sum(FINE_int-repay_FINE_int-wv_FINE_int) as atpy_amt from ac_ln_lo where setl_sts = '"+PUBConstant.N+"' and loan_no in(select a.loan_no from ac_ln_mst a where a.loan_sts in('01','02','03') and a.br_acc_type='A' ) group by loan_no");

			ResultSet selectAcLnLoTtlRs = selectAcLnLoTtlPst.executeQuery();
			
			//向批扣主表中加入记录
			PreparedStatement insertAcDebitPst = conn.prepareStatement("INSERT INTO AC_DEBIT (TRACE_NO,TRACE_CNT,DEBIT_NO,TX_DT,LOAN_NO,PACT_NO,BR_NO,ACCT_BANK_CDE,AC_NO,XT_AC_NO,CUR_NO,DEBIT_TYPE,ATPY_AMT,LO_AMT,CUR_AMT,MY_FEE_AMT,OTHER_FEE_AMT,STS,CREATE_DT,AC_TYPE,AC_NAME,BANK_CODE,BANK_PROV,BANK_CITY,BANK_SITE,CARD_CHN,BUS_ENTRY_TYPE,ID_TYPE,ID_NO,PHONE_NO,VALID_DATE,CVV_NO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			
			//查询欠款表where by loanNo
			PreparedStatement selectAcLnLoPst = conn.prepareStatement("select loan_no,pact_no,br_no, perd_no,pay_dt, (prcp_amt-repay_prcp_amt-wv_prcp_amt) as prcp_amt, (norm_int-repay_norm_int-wv_norm_int) as norm_int, (fine_int-repay_fine_int-wv_fine_int) as fine_int from AC_LN_LO where setl_sts = '"+PUBConstant.N+"' and loan_no=?");
			ResultSet selectAcLnLoRs = null;
			
			//查询贷款主表信息
			PreparedStatement selectAcLnMstPst = conn.prepareStatement("select loan_no,pact_no,br_no,cur_no,proj_no,prdt_no  from ac_ln_mst where  loan_no=?");
			ResultSet selectAcLnMstRs = null;
			
			//查询借据表信息
			PreparedStatement selectLnDuePst = conn.prepareStatement("select app_id,proj_no,cur_no from ln_due where due_no=?");
			ResultSet selectLnDueRs = null;
			
			//查询贷款账号表--扣款账号
			PreparedStatement selectLnAcctPst = conn.prepareStatement("select ac_type,ac_no,ac_name,bank_code,bank_prov,bank_city,bank_site,ac_sts,id_type,id_no,phone_no,valid_date,cvv_no from ln_acct where app_id=? and ac_use='1' and ac_sts='01' and rownum='1'");
			ResultSet selectLnAcctRs = null;
			
			//查询信托项目款账号表--专户
			PreparedStatement selectProjAcctPst = conn.prepareStatement("select acct_no from proj_acct where proj_no=?");
			ResultSet selectProjAcctRs = null;
			
			//查询合作机构账号表
			PreparedStatement selectCorpAcctPst = conn.prepareStatement("select opn_bank,opn_acno,ac_name,exchange,province,city from corp_acct where br_no=?");
			ResultSet selectCorpAcctRs = null;

			//查询是否有当期数据
			PreparedStatement selectPlnCurByLoanNoPst = conn.prepareStatement("select pact_no,br_no,perd_no, prcp_amt, norm_int, repay_prcp_amt, repay_norm_int from AC_LN_REPAY_PLN_CUR where pay_dt<='"+txDt+"'  and end_dt>='"+txDt+"' and loan_no=?");
			ResultSet selectPlnCurByLoanNoRs = null;
			
			//查询当天是否有费用信息
			PreparedStatement selectAcChrgLogPst = conn.prepareStatement("select loan_no,perd_no,fee_kind,nvl(sum(chrg_amt)-sum(repay_chrg_amt),0) as fee_amt from ac_chrg_log where loan_no=? and fee_kind=? and tx_date<=? and chrg_sts in('01','02') group by loan_no,perd_no,fee_kind");
			ResultSet selectAcChrgLogRs = null;
		
			for(;selectAcLnLoTtlRs.next(); num++){ //查询欠款表有符合条件数据
				traceNo = ParmBiz.getTraceNo(conn);
				double ttlAmt = 0.00;
				String loanNo = selectAcLnLoTtlRs.getString("loan_no");
				
				selectAcDebitPst.setString(1, loanNo);
				selectAcDebitRs = selectAcDebitPst.executeQuery();
				//若该笔贷款存在已发送未回盘扣款信息，则不生成扣款明细及批扣文件
				if (!selectAcDebitRs.next()) {
					String curNo = "";// 币种
					String projNo = "";// 项目编号
					String pactNo = "";
					String brNo = "";
					String appId = "";
					String prdtNo = "";// 产品号

					selectAcLnMstPst.setString(1, loanNo);
					selectAcLnMstRs = selectAcLnMstPst.executeQuery();
					if (selectAcLnMstRs.next()) {
						curNo = selectAcLnMstRs.getString("cur_no");
						projNo = selectAcLnMstRs.getString("proj_no");
						pactNo = selectAcLnMstRs.getString("pact_no");
						brNo = selectAcLnMstRs.getString("br_no");
						prdtNo = selectAcLnMstRs.getString("prdt_no");
					}
					PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM
							.get(prdtNo);

					// 查询信托项目 账号
					String xtAcNo = "";
					selectProjAcctPst.setString(1, projNo);
					selectProjAcctRs = selectProjAcctPst.executeQuery();
					if (selectProjAcctRs.next()) {
						xtAcNo = selectProjAcctRs.getString("acct_no");
					}

					selectLnDuePst.setString(1, loanNo);
					selectLnDueRs = selectLnDuePst.executeQuery();
					if (selectLnDueRs.next()) {
						appId = selectLnDueRs.getString("app_id");
					}
					// 欠款金额
					double loAmt = selectAcLnLoTtlRs.getDouble("atpy_amt");

					selectAcLnLoPst.setString(1, loanNo);
					selectAcLnLoRs = selectAcLnLoPst.executeQuery();
					while (selectAcLnLoRs.next()) { // 查询欠款表有符合条件数据
						String payDt = selectAcLnLoRs.getString("pay_dt");// 还款日期
						// 欠款超过扣失天数，不扣款 ---既欠款在扣失天数内，进行扣款
						if (TimeUtil.getBetweenDays(payDt, txDt) <= prdtBase
								.getFailDays()) {
							pactNo = selectAcLnLoRs.getString("pact_no");
							brNo = selectAcLnLoRs.getString("br_no");
						}
					}

					// 查询当期
					double curAmt = 0.00;
					selectPlnCurByLoanNoPst.setString(1, loanNo);
					selectPlnCurByLoanNoRs = selectPlnCurByLoanNoPst
							.executeQuery(); // 查询是否有当期数据
					if (selectPlnCurByLoanNoRs.next()) {
						pactNo = selectPlnCurByLoanNoRs.getString("pact_no");
						brNo = selectPlnCurByLoanNoRs.getString("br_no");
						double prcpAmt = selectPlnCurByLoanNoRs
								.getDouble("prcp_amt");
						double normInt = selectPlnCurByLoanNoRs
								.getDouble("norm_int");
						double repayPrcpAmt = selectPlnCurByLoanNoRs
								.getDouble("repay_prcp_amt");
						double repayNormInt = selectPlnCurByLoanNoRs
								.getDouble("repay_norm_int");
						prcpAmt = NumberUtil.amtSubs(prcpAmt, repayPrcpAmt);
						normInt = NumberUtil.amtSubs(normInt, repayNormInt);
						curAmt = NumberUtil.amtAdd(prcpAmt, normInt);

					}

					String acctBankCde = "";//开户银行代码
					String acNo = "";//账户号码
					String acType = "";//账户类型[11个人账户12企业账户]
					String acNames = "";//账户户名
					String bankProv = "";//开户银行所在省
					String bankCity = "";//开户银行所在市
					String bankSite = "";//开户银行网点
					String cardChn = "";//支付渠道
					String busOrderType ="";//业务订单交易类型
					String idType = "";//证件类型
					String idNo = "";//证件号码
					String phoneNo = "";//手机号码
					String validDate = "";//信用卡有效期
					String cvvNo = "";//银行卡CVV2码
					selectLnAcctPst.setString(1, appId);
					selectLnAcctRs = selectLnAcctPst.executeQuery(); // 查询还款账户
					while (selectLnAcctRs.next()) {
						acctBankCde = selectLnAcctRs.getString("bank_code");
						acNo = selectLnAcctRs.getString("ac_no");
						acType = selectLnAcctRs.getString("ac_type");
						acNames = selectLnAcctRs.getString("ac_name");
						bankProv = selectLnAcctRs.getString("bank_prov");
						bankCity = selectLnAcctRs.getString("bank_city");
						bankSite = selectLnAcctRs.getString("bank_site");
						idType = selectLnAcctRs.getString("id_type");
						idNo = selectLnAcctRs.getString("id_no");
						phoneNo = selectLnAcctRs.getString("phone_no");
						validDate = selectLnAcctRs.getString("valid_date");
						cvvNo = selectLnAcctRs.getString("cvv_no");
					}

					// 查询当日应扣自收费，生成自收费扣款明细
					double myFeeAmt = 0.00;//自收费合计
					selectAcChrgLogPst.setString(1, loanNo);
					selectAcChrgLogPst.setString(2, PUBConstant.FEE_KIND_01);
					selectAcChrgLogPst.setString(3, txDt);
					selectAcChrgLogRs = selectAcChrgLogPst.executeQuery();
					while (selectAcChrgLogRs.next()) {
						myFeeAmt = myFeeAmt + selectAcChrgLogRs.getDouble("fee_amt");//自收费合计
					}

					// 查询合作机构账户，代收费用收款账户
					String opnAcno = "";// 合作机构账户
					String opnBank = "";//开户行
					String acName = "";//账户名称
					String exchange = "";//开户行代码
					String province = "";//开户所在地省名
					String city = "";//开户行所在地市名
					selectCorpAcctPst.setString(1, brNo);
					selectCorpAcctRs = selectCorpAcctPst.executeQuery();
					if (selectCorpAcctRs.next()) {
						opnAcno = selectCorpAcctRs.getString("opn_acno");
						opnBank = selectCorpAcctRs.getString("opn_bank");
						acName = selectCorpAcctRs.getString("ac_name");
						exchange = selectCorpAcctRs.getString("exchange");
						province = selectCorpAcctRs.getString("province");
						city = selectCorpAcctRs.getString("city");

					}

					// 查询当日应扣代收费，生成代收费扣款明细
					double otherFeeAmt = 0.00;//代收费合计
					selectAcChrgLogPst.setString(1, loanNo);
					selectAcChrgLogPst.setString(2, PUBConstant.FEE_KIND_02);
					selectAcChrgLogPst.setString(3, txDt);
					selectAcChrgLogRs = selectAcChrgLogPst.executeQuery();
					while (selectAcChrgLogRs.next()) {
						otherFeeAmt = otherFeeAmt + selectAcChrgLogRs.getDouble("fee_amt");//代收费合计
					}
					if(otherFeeAmt>0){//若费用大于0
						busOrderType="004";//含费用
					}else{
						busOrderType="003";//不含费用
					}
					ttlAmt = NumberUtil.amtAdd(loAmt, curAmt);
					insertAcDebitPst.setString(1, traceNo); // 流水号
					insertAcDebitPst.setInt(2, 1); // 序号
					insertAcDebitPst.setString(3, ParmBiz.getDebitNo(conn)); // 批扣流水
					insertAcDebitPst.setString(4, txDt); // 交易日
					insertAcDebitPst.setString(5, loanNo); // 借据号
					insertAcDebitPst.setString(6, pactNo); // 合同号
					insertAcDebitPst.setString(7, brNo); // 机构号
					insertAcDebitPst.setString(8, acctBankCde); // 扣款账号银行代码
					insertAcDebitPst.setString(9, acNo); // 扣款账号
					insertAcDebitPst.setString(10, xtAcNo); // 信托账号/合作机构账号
					insertAcDebitPst.setString(11, curNo); // 扣款币种
					insertAcDebitPst.setString(12, "01"); // 扣款类别-本利罚
					insertAcDebitPst.setDouble(13, ttlAmt); // 应扣金额
					insertAcDebitPst.setDouble(14, loAmt); // 应扣欠款金额
					insertAcDebitPst.setDouble(15, curAmt); // 应扣本期金额
					insertAcDebitPst.setDouble(16, myFeeAmt); // 自收费用金额
					insertAcDebitPst.setDouble(17, otherFeeAmt); // 代收费用金额
					insertAcDebitPst.setString(18, PUBConstant.DDTL_STS_PEND); // 批扣状态
					insertAcDebitPst.setString(19, txDt); // 创建日期
					insertAcDebitPst.setString(20, acType); // 账户类型[11个人账户12企业账户]
					insertAcDebitPst.setString(21, acNames); // 账户户名
					insertAcDebitPst.setString(22, acctBankCde); // 扣款账号银行代码
					insertAcDebitPst.setString(23, bankProv); // 开户银行所在省
					insertAcDebitPst.setString(24, bankCity); // 开户银行所在市
					insertAcDebitPst.setString(25, bankSite); // 开户银行网点
					insertAcDebitPst.setString(26, cardChn); // 支付渠道
					insertAcDebitPst.setString(27, busOrderType+"01"); // 业务订单交易类型
					insertAcDebitPst.setString(28, idType); // 证件类型
					insertAcDebitPst.setString(29, idNo); // 证件号码
					insertAcDebitPst.setString(30, phoneNo); // 手机号码
					insertAcDebitPst.setString(31, validDate); // 信用卡有效期
					insertAcDebitPst.setString(32, cvvNo); // 银行卡CVV2码

					insertAcDebitPst.addBatch();
					

					if (num % 10000 == 0) {
						insertAcDebitPst.executeBatch();
					}

					num++;
				}
			}
			insertAcDebitPst.executeBatch();

			/*
			 *查询当期表数据 -- 不存在逾期
			 */
			PreparedStatement selectAcLnRepayPlnCurPst = conn.prepareStatement("select cur.loan_no, cur.pact_no, cur.br_no, cur.perd_no, cur.prcp_amt, cur.norm_int,cur.repay_prcp_amt, cur.repay_norm_int from AC_LN_REPAY_PLN_CUR cur where cur.pay_dt<='"+txDt+"' and cur.end_dt>='"+txDt+"' and loan_no not in(select loan_no from ac_ln_mst where br_acc_type='B') and not exists (SELECT 1 FROM ac_ln_lo lo WHERE cur.loan_no=lo.loan_no and lo.setl_sts='N')");
			ResultSet selectAcLnRepayPlnCurRs = selectAcLnRepayPlnCurPst.executeQuery();
			
			for(; selectAcLnRepayPlnCurRs.next(); num++){
				traceNo = ParmBiz.getTraceNo(conn);
				String loanNo = selectAcLnRepayPlnCurRs.getString("loan_no");
				
				selectAcDebitPst.setString(1, loanNo);
				selectAcDebitRs = selectAcDebitPst.executeQuery();
				if (!selectAcDebitRs.next()) {
					String pactNo = selectAcLnRepayPlnCurRs
							.getString("pact_no");
					String brNo = selectAcLnRepayPlnCurRs.getString("br_no");
					double prcpAmt = selectAcLnRepayPlnCurRs
							.getDouble("prcp_amt");
					double normInt = selectAcLnRepayPlnCurRs
							.getDouble("norm_int");
					double repayPrcpAmt = selectAcLnRepayPlnCurRs
							.getDouble("repay_prcp_amt");
					double repayNormInt = selectAcLnRepayPlnCurRs
							.getDouble("repay_norm_int");
					prcpAmt = NumberUtil.amtSubs(prcpAmt, repayPrcpAmt);
					normInt = NumberUtil.amtSubs(normInt, repayNormInt);
					double curAmt = NumberUtil.amtAdd(prcpAmt, normInt);

					String appId = "";// 申请编号
					String projNo = "";// 项目编号
					String curNo = "";// 币种
					selectLnDuePst.setString(1, loanNo);
					selectLnDueRs = selectLnDuePst.executeQuery();
					if (selectLnDueRs.next()) {
						appId = selectLnDueRs.getString("app_id");
						projNo = selectLnDueRs.getString("proj_no");
						curNo = selectLnDueRs.getString("cur_no");
					}

					// 查询信托项目 账号
					String xtAcNo = "";
					selectProjAcctPst.setString(1, projNo);
					selectProjAcctRs = selectProjAcctPst.executeQuery();
					if (selectProjAcctRs.next()) {
						xtAcNo = selectProjAcctRs.getString("acct_no");
					}
					String acctBankCde = "";
					String acNo = "";
					String acType = "";//账户类型[11个人账户12企业账户]
					String acNames = "";//账户户名
					String bankProv = "";//开户银行所在省
					String bankCity = "";//开户银行所在市
					String bankSite = "";//开户银行网点
					String cardChn = "";//支付渠道
					String busOrderType ="";//业务订单交易类型
					String idType = "";//证件类型
					String idNo = "";//证件号码
					String phoneNo = "";//手机号码
					String validDate = "";//信用卡有效期
					String cvvNo = "";//银行卡CVV2码
					selectLnAcctPst.setString(1, appId);
					selectLnAcctRs = selectLnAcctPst.executeQuery(); // 查询还款账户
					while (selectLnAcctRs.next()) {
						acctBankCde = selectLnAcctRs.getString("bank_code");
						acNo = selectLnAcctRs.getString("ac_no");
						acType = selectLnAcctRs.getString("ac_type");
						acNames = selectLnAcctRs.getString("ac_name");
						bankProv = selectLnAcctRs.getString("bank_prov");
						bankCity = selectLnAcctRs.getString("bank_city");
						bankSite = selectLnAcctRs.getString("bank_site");
						idType = selectLnAcctRs.getString("id_type");
						idNo = selectLnAcctRs.getString("id_no");
						phoneNo = selectLnAcctRs.getString("phone_no");
						validDate = selectLnAcctRs.getString("valid_date");
						cvvNo = selectLnAcctRs.getString("cvv_no");
					}

					// 查询当日应扣自收费，生成自收费扣款明细
					double myFeeAmt = 0.00;// 自收费总计

					selectAcChrgLogPst.setString(1, loanNo);
					selectAcChrgLogPst.setString(2, PUBConstant.FEE_KIND_01);
					selectAcChrgLogPst.setString(3, txDt);
					selectAcChrgLogRs = selectAcChrgLogPst.executeQuery();
					while (selectAcChrgLogRs.next()) {
						myFeeAmt = myFeeAmt + selectAcChrgLogRs.getDouble("fee_amt");
					}

					// 查询合作机构账户，代收费用收款账户
					String opnAcno = "";// 合作机构账户
					String opnBank = "";//开户行
					String acName = "";//账户名称
					String exchange = "";//开户行代码
					String province = "";//开户所在地省名
					String city = "";//开户行所在地市名
					selectCorpAcctPst.setString(1, brNo);
					selectCorpAcctRs = selectCorpAcctPst.executeQuery();
					if (selectCorpAcctRs.next()) {
						opnAcno = selectCorpAcctRs.getString("opn_acno");
						opnBank = selectCorpAcctRs.getString("opn_bank");
						acName = selectCorpAcctRs.getString("ac_name");
						exchange = selectCorpAcctRs.getString("exchange");
						province = selectCorpAcctRs.getString("province");
						city = selectCorpAcctRs.getString("city");
					}

					// 查询当日应扣代收费，生成代收费扣款明细
					double otherFeeAmt = 0.00;// 代收费总计

					selectAcChrgLogPst.setString(1, loanNo);
					selectAcChrgLogPst.setString(2, PUBConstant.FEE_KIND_02);
					selectAcChrgLogPst.setString(3, txDt);
					selectAcChrgLogRs = selectAcChrgLogPst.executeQuery();
					while (selectAcChrgLogRs.next()) {
						otherFeeAmt = otherFeeAmt+selectAcChrgLogRs.getDouble("fee_amt");
					}
					if(otherFeeAmt>0){//若费用大于0
						busOrderType="004";//含费用
					}else{
						busOrderType="003";//不含费用
					}
					insertAcDebitPst.setString(1, traceNo); // 流水号
					insertAcDebitPst.setInt(2, 1); // 序号
					insertAcDebitPst.setString(3, ParmBiz.getDebitNo(conn)); // 批扣流水
					insertAcDebitPst.setString(4, txDt); // 交易日
					insertAcDebitPst.setString(5, loanNo); // 借据号
					insertAcDebitPst.setString(6, pactNo); // 合同号
					insertAcDebitPst.setString(7, brNo); // 机构号
					insertAcDebitPst.setString(8, acctBankCde); // 扣款账号银行代码
					insertAcDebitPst.setString(9, acNo); // 扣款账号
					insertAcDebitPst.setString(10, xtAcNo); // 信托账号/合作机构账号
					insertAcDebitPst.setString(11, curNo); // 扣款币种
					insertAcDebitPst.setString(12, ""); // 扣款类别-废弃
					insertAcDebitPst.setDouble(13, curAmt+myFeeAmt+otherFeeAmt); // 应扣金额
					insertAcDebitPst.setDouble(14, 0.00); // 应扣欠款金额
					insertAcDebitPst.setDouble(15, curAmt); // 应扣本期金额
					insertAcDebitPst.setDouble(16, myFeeAmt); // 自收费费用金额
					insertAcDebitPst.setDouble(17, otherFeeAmt); // 代收费
					insertAcDebitPst.setString(18, PUBConstant.DDTL_STS_PEND); // 批扣状态
					insertAcDebitPst.setString(19, this.getTxDt(conn)); // 创建日期
					insertAcDebitPst.setString(20, acType); //账户类型[11个人账户12企业账户] 
					insertAcDebitPst.setString(21, acNames); // 账户户名
					insertAcDebitPst.setString(22, acctBankCde); // 扣款账号银行代码
					insertAcDebitPst.setString(23, bankProv); // 开户银行所在省
					insertAcDebitPst.setString(24, bankCity); // 开户银行所在市
					insertAcDebitPst.setString(25, bankSite); // 开户银行网点
					insertAcDebitPst.setString(26, cardChn); // 支付渠道
					insertAcDebitPst.setString(27, busOrderType+"01"); // 业务订单交易类型
					insertAcDebitPst.setString(28, idType); // 证件类型
					insertAcDebitPst.setString(29, idNo); // 证件号码
					insertAcDebitPst.setString(30, phoneNo); //手机号码 
					insertAcDebitPst.setString(31, validDate); // 信用卡有效期
					insertAcDebitPst.setString(32, cvvNo); // 银行卡CVV2码
					
					insertAcDebitPst.addBatch();


					if (num % 10000 == 0) {
						insertAcDebitPst.executeBatch();
					}
					num++;
				}
			}
			if(null!=selectAcDebitPst){
				selectAcDebitPst.close();
			}
			if(null!=selectAcDebitRs){
				selectAcDebitRs.close();
			}
			if(null!=selectAcLnLoRs){
				selectAcLnLoRs.close();
			}
			if(null!=selectAcLnLoPst){
				selectAcLnLoPst.close();
			}
			if(null!=selectPlnCurByLoanNoRs){
				selectPlnCurByLoanNoRs.close();
			}
			if(null!=selectAcLnMstPst){
				selectAcLnMstPst.close();
			}
			if(null!=selectAcLnMstRs){
				selectAcLnMstRs.close();
			}
			if(null!=selectLnDuePst){
				selectLnDuePst.close();
			}
			if(null!=selectLnDueRs){
				selectLnDueRs.close();
			}
			if(null!=selectLnAcctPst){
				selectLnAcctPst.close();
			}
			if(null!=selectLnAcctRs){
				selectLnAcctRs.close();
			}
			if(null!=selectProjAcctPst){
				selectProjAcctPst.close();
			}
			if(null!=selectProjAcctRs){
				selectProjAcctRs.close();
			}
			if(null!=selectCorpAcctPst){
				selectCorpAcctPst.close();
			}
			if(null!=selectCorpAcctRs){
				selectCorpAcctRs.close();
			}
			if(null!=selectAcChrgLogPst){
				selectAcChrgLogPst.close();
			}
			if(null!=selectAcChrgLogRs){
				selectAcChrgLogRs.close();
			}
			if(null!=selectPlnCurByLoanNoPst){
				selectPlnCurByLoanNoPst.close();
			}
			if(null!=selectPlnCurByLoanNoRs){
				selectPlnCurByLoanNoRs.close();
			}
			if(null!=selectAcLnLoTtlRs){
				selectAcLnLoTtlRs.close();
			}
			if(null!=selectAcLnLoTtlPst){
				selectAcLnLoTtlPst.close();
			}
			
			if(null!=insertAcDebitPst){
				insertAcDebitPst.executeBatch();
			}
			if(null!=insertAcDebitPst){
				insertAcDebitPst.close();
			}
			if(null!=insertAcDebitPst){
				deleteAcDebitPst.close();
			}
			Statement updateAcComSysParmSt2 = conn.createStatement();
			String updateAcComSysParmSql2 = "update AC_COM_SYS_PARM set batch_dt='"
					+ batchDt + "',batch_stp='" + step + "'";
			updateAcComSysParmSt2.executeUpdate(updateAcComSysParmSql2);
			updateAcComSysParmSt2.close();

			conn.commit();

			}else{
				System.out.println(batchDt+"批量步骤9已执行");
			}	
			
			selectSysGlobalPst.close();
			selectSysGlobalRs.close();
			selectAcComSysParmPst.close();
			selectAcComSysParmRs.close();
			
			batchFlag = true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error(e);
			batchFlag = false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return batchFlag;
	}
	
	public static void main(String[] args) throws Exception {
		Main.initialize();
		String txDt = PUBConstant.CUR_PRCS_DT;
		LoanRepayLoAcLmAtpyBatch lrbLo2 = new LoanRepayLoAcLmAtpyBatch();
		boolean c = lrbLo2.doBatch(null, null);
	}
}
