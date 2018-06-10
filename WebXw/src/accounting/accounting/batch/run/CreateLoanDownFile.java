package accounting.batch.run;

import java.io.BufferedReader;
import java.io.File;

import accounting.batch.util.FileUtil;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.init.BusinessInitializer;

public class CreateLoanDownFile {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		BusinessInitializer buz = new BusinessInitializer();
		buz.initialize();
		
		String txDate = "2011-05-20" ;
		String loanNo="c20122";
		boolean get = false ;
		if(get){
//			getKkAmt(txDate, loanNo) ;
		}else{
			cleanFiles("E:\\batch\\");
			double amt = 0 ;
//			CreateLoanDownFile.kk(txDate,loanNo,amt);
			System.out.println("扣款完成");
		}

	}

	/**
	 * 删除文件夹中所有文件
	 * @param path 文件夹绝对路径 
	 */
	public static void cleanFiles(String path) {
		File dir = new File(path);
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File f:files){
				f.delete();
			}
		}

	}

//	public static void kk (String txDt,String loanNo1,double kkAmt) {
//
//		String downFileName = PUBConstant.PRE_LOAN_REPAY_BATCH_DOWN+txDt+PUBConstant.POST_BATCH_DOWN;
//		String upFileName = PUBConstant.PRE_LOAN_REPAY_BATCH_DOWN + txDt + PUBConstant.POST_BATCH_UP;
//		Connection conn =   ConnectionPool.getInstance().getConnection();
//		Connection traceNoConn =   ConnectionPool.getInstance().getConnection();
//		String seqNo = PUBConstant.BAT_LOAN_REPAY;
//		String txCode = TransCode.LNCQ; 
//		KeyGen kg = KeyGen.getKeyGen();	
//		try {
//
//
//			//删除自动扣款交易主表信息
//			String deleteAcLmAtpySql="delete from AC_LM_ATPY where  tx_dt='"+txDt+"' and seq_No='"+seqNo+"'";
//			PreparedStatement deleteAcLmAtpyPst = conn.prepareStatement(deleteAcLmAtpySql);
//			deleteAcLmAtpyPst.executeUpdate();
//			deleteAcLmAtpyPst.close();
//
//			//删除自动扣款交易明细表信息
//			String deleteAcLmAtpyDetlSql="delete from AC_LM_ATPY_DETL where  tx_dt='"+txDt+"' and seq_No='"+seqNo+"'";
//			PreparedStatement deleteAcLmAtpyDetlPst = conn.prepareStatement(deleteAcLmAtpyDetlSql);
//			deleteAcLmAtpyDetlPst.executeUpdate();
//			deleteAcLmAtpyDetlPst.close();
//
//			// 当期还款计划表数据插入到批扣明细表中
//			PreparedStatement insertAcLmAtpyDetlPst = conn.prepareStatement("insert into AC_LM_ATPY_DETL (seq_no,tx_dt,ac_id,ac_seq,loan_no,prcp_amt,int_amt,create_dt,paym_amt,perd_no,cur_ind,mortgage_ind) select "+seqNo+",'"+txDt+"',ac_id,ac_seq,loan_no,prcp_amt,norm_intst,'"+txDt+"',prcp_amt+norm_intst,perd_no,'"+PUBConstant.Y+"','"+PUBConstant.Y+"' from AC_LN_PAY_PLN_CUR where end_dt = '"+txDt+"' and loan_no='"+loanNo1+"' ");
//			int countCur = insertAcLmAtpyDetlPst.executeUpdate();
//			// 欠款表数据插入到批扣明细表中
//			insertAcLmAtpyDetlPst = conn.prepareStatement("insert into AC_LM_ATPY_DETL (seq_no,tx_dt,ac_id,ac_seq,loan_no,prcp_amt,int_amt,create_dt,paym_amt,perd_no,cur_ind,mortgage_ind,od_int,comp_int) select "+seqNo+",'"+txDt+"',ac_id,ac_seq,loan_no,prcp_amt,norm_intst,'"+txDt+"',prcp_amt+norm_intst+over_intst+cmpd_amt,perd_no,'"+PUBConstant.N+"','"+PUBConstant.Y+"',over_intst,cmpd_amt from AC_LN_LO where setl_sts = '"+PUBConstant.N+"' and end_dt<='"+txDt+"' and loan_no='"+loanNo1+"' ");
//			int countLo = insertAcLmAtpyDetlPst.executeUpdate();
//			insertAcLmAtpyDetlPst.close();
//
//			/**
//			 * 将扣款明细表记录插入扣款交易主表 AC_LM_ATPY
//			 */
//			// 从贷款主表查还款账户
//			PreparedStatement selectAcLnMstPst = conn.prepareStatement("select repay_ac_id from AC_LN_MST where ac_id = ? and ac_seq = ?");
//			ResultSet selectAcLnMstRs;
//
//			// 插入一条批扣主表记录
//			PreparedStatement insertAcLmAtpyPst = conn.prepareStatement("insert into ac_lm_atpy(seq_no,tx_dt,txlog_no,txlog_seq_no,ac_id,ac_seq,loan_no,repay_ac_id,paym_amt,paym_lo_amt,paym_cur_amt,create_dt,spool_dt,tx_amt,sts,mortgage_ind) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//
//			// 从扣款明细表中查询未还清欠款记录并对金额汇总,查当期还款记录,对结果全连接
//			PreparedStatement selectAcLmAtpyDeltPst = conn.prepareStatement("select nvl(l.ac_id,c.ac_id)as ac_id,nvl(l.ac_seq,c.ac_seq)as ac_seq,nvl(c.loan_no,l.loan_no) as loan_no,l.paym_lo_amt,c.paym_cur_amt,nvl(l.paym_lo_amt,0)+nvl(c.paym_cur_amt,0) as paym_amt from (select loan_no,ac_id,ac_seq,sum(paym_amt) as paym_lo_amt from AC_LM_ATPY_DETL where cur_ind='N' and seq_no=? and tx_dt = ? group by ac_id,ac_seq,loan_no) l full join (select loan_no,ac_id,ac_seq,paym_amt as paym_cur_amt from AC_LM_ATPY_DETL where cur_ind='Y' and tx_dt = ? and seq_no=?) c on l.ac_id=c.ac_id and l.ac_seq=c.ac_seq");
//			selectAcLmAtpyDeltPst.setString(1, seqNo);		// 设置批扣序号
//			selectAcLmAtpyDeltPst.setString(2, txDt);		// 设置交易日期
//			selectAcLmAtpyDeltPst.setString(3, txDt);		// 设置交易日期
//			selectAcLmAtpyDeltPst.setString(4, seqNo);		// 设置批扣序号
//			//log("select nvl(l.ac_id,c.ac_id)as ac_id,nvl(l.ac_seqn,c.ac_seqn)as ac_seqn,nvl(c.loan_no,l.loan_no) as loan_no,l.paym_lo_amt,c.paym_cur_amt,nvl(l.paym_lo_amt,0)+nvl(c.paym_cur_amt,0) as paym_amt from (select loan_no,ac_id,ac_seqn,sum(paym_amt) as paym_lo_amt from AC_LM_ATPY_DETL where cur_ind='N' and seq_no='"+seqNo+"' group by ac_id,ac_seqn,loan_no) l full join (select ac_id,ac_seqn,paym_amt as paym_cur_amt from AC_LM_ATPY_DETL where cur_ind='Y' and tx_dt = '"+txDt+"' and seq_no='"+seqNo+"') c on l.ac_id=c.ac_id and l.ac_seqn=c.ac_seqn");
//			ResultSet selectAcLmAtpyDeltRs = selectAcLmAtpyDeltPst.executeQuery();
//			for(int i=1;selectAcLmAtpyDeltRs.next();i++){
//				String acId = selectAcLmAtpyDeltRs.getString("ac_id");				// 账户id
//				String acSeq = selectAcLmAtpyDeltRs.getString("ac_seq");			// 账户序号
//				String loanNo = selectAcLmAtpyDeltRs.getString("loan_no");			// 借据号
//				double paymLoAmt = selectAcLmAtpyDeltRs.getDouble("paym_lo_amt");	// 应还欠款金额
//				double paymCurAmt = selectAcLmAtpyDeltRs.getDouble("paym_cur_amt");	// 应还当期金额
//				double paymAmt = selectAcLmAtpyDeltRs.getDouble("paym_amt");		// 应还总金额
//				String repayAcId = null;								// 结算账户id
//				String sts = PUBConstant.AC_LM_ATPY_STS_CREATE;			// 批扣状态
//
//				// 从贷款主表查还款账户
//				selectAcLnMstPst.setString(1, acId);					// 账户id
//				selectAcLnMstPst.setString(2, acSeq);					// 账户序号
//				//log("select repay_ac_id from AC_LN_MST where ac_id = '"+acId+"' and ac_seqn = '"+acSeqn+"'");
//				selectAcLnMstRs = selectAcLnMstPst.executeQuery();		// 查询贷款主表
//				if(selectAcLnMstRs.next()) {
//					repayAcId = selectAcLnMstRs.getString("repay_ac_id"); 	// 结算账户id
//				}else {
//					throw new Exception("找不到账户id:"+acId+" 账户序号:"+acSeq+" 的记录");
//				}
//				selectAcLnMstRs.close();
//
//				// 插入一条新批扣记录
//				long txlogNo = kg.getTraceNo(traceNoConn);		// 获取交易流水号
//				insertAcLmAtpyPst.setString(1, seqNo);		// 设置批扣序号
//				insertAcLmAtpyPst.setString(2, txDt);		// 设置交易日期
//				insertAcLmAtpyPst.setLong(3, txlogNo);		// 设置交易流水号
//				insertAcLmAtpyPst.setInt(4, 1);				// 设置交易流水子序号
//				insertAcLmAtpyPst.setString(5, acId);		// 设置账户id
//				insertAcLmAtpyPst.setString(6, acSeq);		// 设置账户序号
//				insertAcLmAtpyPst.setString(7, loanNo);		// 设置借据号
//				insertAcLmAtpyPst.setString(8, repayAcId);	// 设置结算账户
//				insertAcLmAtpyPst.setDouble(9, paymAmt);	// 设置应扣总金额
//				insertAcLmAtpyPst.setDouble(10, paymLoAmt);	// 设置应扣欠款金额
//				insertAcLmAtpyPst.setDouble(11, paymCurAmt);// 设置应扣当期金额
//				insertAcLmAtpyPst.setString(12, txDt);		// 设置创建日期
//				insertAcLmAtpyPst.setString(13, txDt);		// 文件生成日期
//				insertAcLmAtpyPst.setDouble(14, 0);			// 设置实还金额
//				insertAcLmAtpyPst.setString(15, sts);		// 设置批扣状态
//				insertAcLmAtpyPst.setString(16, PUBConstant.Y);	// 设置按揭标志
//				insertAcLmAtpyPst.addBatch();
//
//				insertAcLmAtpyPst.executeBatch();
//
//			}
//			insertAcLmAtpyPst.close();
//			selectAcLmAtpyDeltRs.close();
//			selectAcLmAtpyDeltPst.close();
//			selectAcLnMstPst.close();
//			
//			/**
//			 * 生成上传文件
//			 */
//			FileUtil.write(upFileName, new String[] { "" });
//			PreparedStatement selectAcLmAtpyPst = conn
//					.prepareStatement("select txlog_no,ac_id,ac_seq,loan_no,repay_ac_id,paym_amt from AC_LM_ATPY where tx_dt='"
//							+ txDt
//							+ "' and seq_no = '"
//							+ seqNo
//							+ "' and sts = '"
//							+ PUBConstant.AC_LM_ATPY_STS_CREATE
//							+ "'");
//			ResultSet selectAcLmAtpyRs = selectAcLmAtpyPst.executeQuery();
//			while (selectAcLmAtpyRs.next()) {
//				String loanTxNo = selectAcLmAtpyRs.getString("txlog_no"); // 流水号
//				String acId = selectAcLmAtpyRs.getString("ac_id"); // 账户id
//				String acSeq = selectAcLmAtpyRs.getString("ac_seq"); // 账户序号
//				String loanNo = selectAcLmAtpyRs.getString("loan_no"); // 借据号
//				String paymAcctNo = selectAcLmAtpyRs.getString("repay_ac_id"); // 结算账户
//				double paymAmt = selectAcLmAtpyRs.getDouble("paym_amt"); // 扣款金额
//				String drCr = PUBConstant.DC_IND_D; // 借贷标志
//				String funID = txCode; // 交易代码
//
//				FileUtil.write(upFileName, new String[] { loanTxNo, acId, acSeq, loanNo, paymAcctNo, paymAmt + "",
//						drCr, funID });
//			}
//			selectAcLmAtpyPst.close();
//			selectAcLmAtpyRs.close();
//
//
//			// 生成下传文件
//			createDownFile(upFileName,downFileName, kkAmt);
//
//			// 读取下传文件
//			BufferedReader br = FileUtil.getReader(downFileName); // 读取下传文件输入流
//			String str = null;
//			// 更新批扣主表
//			// PreparedStatement updateAcLmAtpy =
//			// conn.prepareStatement("update AC_LM_ATPY set hold_tx_no=?,hold_buss_typ=?,tx_amt=?,sts=? where txlog_no = ? and txlog_seq_no = ?  ");
//			PreparedStatement updateAcLmAtpy = conn
//			.prepareStatement("update AC_LM_ATPY set hold_tx_no=?,hold_buss_typ=?,tx_amt=?,sts=? where ac_id = ? and ac_seq = ? and tx_dt = ? and seq_no =?");
//
//			while ((str = br.readLine()) != null) {
//				if (str.equals("\n") || str.trim().equals("")) {
//					continue;
//				} else {
//					String arg[] = str.split("\\|");
//					String txlogNo = arg[0]; // 流水号
//					String acId = arg[1]; // 账户id
//					String acSeqn = arg[2]; // 账户序号
//					String holdTxNo = arg[8]; // 冻结流水号
//					String holdTrace = arg[9]; // 冻结业务号
//					double actPaymAmt = Double.valueOf(arg[10]); // 实际可扣金额
//
//					// 更新批扣主表信息
//					updateAcLmAtpy.setString(1, holdTxNo); // 设置冻结流水号
//					updateAcLmAtpy.setString(2, holdTrace); // 设置冻结业务编号
//					updateAcLmAtpy.setDouble(3, actPaymAmt); // 设置实扣金额
//					updateAcLmAtpy.setString(4, PUBConstant.AC_LM_ATPY_STS_FREEZE); // 设置批扣状态
//					// updateAcLmAtpy.setString(5, txlogNo); // 设置流水号
//					// updateAcLmAtpy.setInt(6, 1); // 设置交易流水子序号
//					updateAcLmAtpy.setString(5, acId); // 设置账户id
//					updateAcLmAtpy.setString(6, acSeqn); // 设置账户序号
//					updateAcLmAtpy.setString(7, txDt); // 设置交易日期
//					updateAcLmAtpy.setString(8, seqNo); // 设置批扣序号
//					// log("update AC_LM_ATPY set hold_tx_no='"+holdTxNo+"',hold_buss_typ='"+holdTrace+"',tx_amt="+actPaymAmt+",sts='"+PUBConstant.AC_LM_ATPY_STS_FREEZE+"' where ac_id = "+acId+" and ac_seqn = "+acSeqn+" and tx_dt = '"+txDt+"' and seq_no ='"+seqNo+"'");
//					updateAcLmAtpy.executeUpdate();
//				}
//			}
//			updateAcLmAtpy.close();
//
//			conn.commit();
////			LoanRepayBatch  lr = new LoanRepayBatch();
////			lr.doBatch(null, null);
//
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//
//	}
	/**
	 * 生成下传文件
	 * 
	 * @throws Exception
	 */
	private static void createDownFile(String upFileName, String downFileName,double kkAmt) throws Exception {
		BufferedReader br = FileUtil.getReader(upFileName); // 读取下传文件输入流
		String str = null;
		FileUtil.write(downFileName, new String[] { "" });
		while ((str = br.readLine()) != null) {
			if (str.equals("\n") || str.trim().equals("")) {
				continue;
			} else {
				String arg[] = str.split("\\|");

				String loanTxNo = arg[0];                   // 流水号
				String acId = arg[1]; 			            // 账户id
				String acSeqn = arg[2]; 		            // 账户序号
				String loanNo = arg[3];		                // 借据号
				String paymAcctNo = arg[4];                 // 结算账户
				double paymAmt = Double.valueOf(arg[5]);; 	// 扣款金额
				String drCr = PUBConstant.DC_IND_D;			// 借贷标志
				String funID = TransCode.LNCQ;				// 交易代码

				FileUtil.write(downFileName,new String[]{loanTxNo,acId,acSeqn,loanNo,paymAcctNo,paymAmt+"",drCr,funID,"","",kkAmt+""});
			}
		}
		br.close();
	}

//	public static void getKkAmt (String txDt,String loanNo1) {
//
//
//		Connection conn =   ConnectionPool.getInstance().getConnection();
//		String seqNo = PUBConstant.BAT_LOAN_REPAY;
//		try {
//
//			//删除自动扣款交易主表信息
//			String deleteAcLmAtpySql="delete from AC_LM_ATPY where  tx_dt='"+txDt+"' and seq_No='"+seqNo+"'";
//			PreparedStatement deleteAcLmAtpyPst = conn.prepareStatement(deleteAcLmAtpySql);
//			deleteAcLmAtpyPst.executeUpdate();
//			deleteAcLmAtpyPst.close();
//
//			//删除自动扣款交易明细表信息
//			String deleteAcLmAtpyDetlSql="delete from AC_LM_ATPY_DETL where  tx_dt='"+txDt+"' and seq_No='"+seqNo+"'";
//			PreparedStatement deleteAcLmAtpyDetlPst = conn.prepareStatement(deleteAcLmAtpyDetlSql);
//			deleteAcLmAtpyDetlPst.executeUpdate();
//			deleteAcLmAtpyDetlPst.close();
//			
//			
//			// 当期还款计划表数据插入到批扣明细表中
//			PreparedStatement insertAcLmAtpyDetlPst = conn.prepareStatement("insert into AC_LM_ATPY_DETL (seq_no,tx_dt,ac_id,ac_seq,loan_no,prcp_amt,int_amt,create_dt,paym_amt,perd_no,cur_ind,mortgage_ind) select "+seqNo+",'"+txDt+"',ac_id,ac_seq,loan_no,prcp_amt,norm_intst,'"+txDt+"',prcp_amt+norm_intst,perd_no,'"+PUBConstant.Y+"','"+PUBConstant.Y+"' from AC_LN_PAY_PLN_CUR where end_dt = '"+txDt+"' and loan_no='"+loanNo1+"' ");
//			int countCur = insertAcLmAtpyDetlPst.executeUpdate();
//			// 欠款表数据插入到批扣明细表中
//			String str2 = "insert into AC_LM_ATPY_DETL (seq_no,tx_dt,ac_id,ac_seq,loan_no,prcp_amt,int_amt,create_dt,paym_amt,perd_no,cur_ind,mortgage_ind,od_int,comp_int) select "+seqNo+",'"+txDt+"',ac_id,ac_seq,loan_no,prcp_amt,norm_intst,'"+txDt+"',prcp_amt+norm_intst+over_intst+cmpd_amt,perd_no,'"+PUBConstant.N+"','"+PUBConstant.Y+"',over_intst,cmpd_amt from AC_LN_LO where setl_sts = '"+PUBConstant.N+"' and end_dt<='"+txDt+"' and loan_no='"+loanNo1+"' ";
//			System.out.println(str2); 
//			insertAcLmAtpyDetlPst = conn.prepareStatement(str2);
//			int countLo = insertAcLmAtpyDetlPst.executeUpdate();
//			insertAcLmAtpyDetlPst.close();
//
//			/**
//			 * 将扣款明细表记录插入扣款交易主表 AC_LM_ATPY
//			 */
//			// 从贷款主表查还款账户
//			PreparedStatement selectAcLnMstPst = conn.prepareStatement("select repay_ac_id from AC_LN_MST where ac_id = ? and ac_seq = ?");
//
//			// 插入一条批扣主表记录
//			PreparedStatement insertAcLmAtpyPst = conn.prepareStatement("insert into ac_lm_atpy(seq_no,tx_dt,txlog_no,txlog_seq_no,ac_id,ac_seq,loan_no,repay_ac_id,paym_amt,paym_lo_amt,paym_cur_amt,create_dt,spool_dt,tx_amt,sts,mortgage_ind) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//
//			// 从扣款明细表中查询未还清欠款记录并对金额汇总,查当期还款记录,对结果全连接
//			PreparedStatement selectAcLmAtpyDeltPst = conn.prepareStatement("select nvl(l.ac_id,c.ac_id)as ac_id,nvl(l.ac_seq,c.ac_seq)as ac_seq,nvl(c.loan_no,l.loan_no) as loan_no,l.paym_lo_amt,c.paym_cur_amt,nvl(l.paym_lo_amt,0)+nvl(c.paym_cur_amt,0) as paym_amt from (select loan_no,ac_id,ac_seq,sum(paym_amt) as paym_lo_amt from AC_LM_ATPY_DETL where cur_ind='N' and seq_no=? and tx_dt = ? group by ac_id,ac_seq,loan_no) l full join (select loan_no,ac_id,ac_seq,paym_amt as paym_cur_amt from AC_LM_ATPY_DETL where cur_ind='Y' and tx_dt = ? and seq_no=?) c on l.ac_id=c.ac_id and l.ac_seq=c.ac_seq");
//			selectAcLmAtpyDeltPst.setString(1, seqNo);		// 设置批扣序号
//			selectAcLmAtpyDeltPst.setString(2, txDt);		// 设置交易日期
//			selectAcLmAtpyDeltPst.setString(3, txDt);		// 设置交易日期
//			selectAcLmAtpyDeltPst.setString(4, seqNo);		// 设置批扣序号
//			ResultSet selectAcLmAtpyDeltRs = selectAcLmAtpyDeltPst.executeQuery();
//			int i=1;
//			for( i=1;selectAcLmAtpyDeltRs.next();i++){
//
//				String loanNo = selectAcLmAtpyDeltRs.getString("loan_no");			// 借据号
//				double paymLoAmt = selectAcLmAtpyDeltRs.getDouble("paym_lo_amt");	// 应还欠款金额
//				double paymCurAmt = selectAcLmAtpyDeltRs.getDouble("paym_cur_amt");	// 应还当期金额
//				double paymAmt = selectAcLmAtpyDeltRs.getDouble("paym_amt");		// 应还总金额
//				System.out.println(loanNo+"应还总金额为："+paymAmt);
//				System.out.println(loanNo+"欠款金额为："+paymLoAmt);
//				System.out.println(loanNo+"当期金额为："+paymCurAmt);
//
//			}
//			System.out.println(i-1+"条记录");
//			insertAcLmAtpyPst.close();
//			selectAcLmAtpyDeltRs.close();
//			selectAcLmAtpyDeltPst.close();
//			selectAcLnMstPst.close();
//
//
//
//			conn.commit();
//
//		} catch (Exception e) {
//			try {
//				e.printStackTrace() ;
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//
//	}
}
