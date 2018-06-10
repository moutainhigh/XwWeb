package accounting.interf.loan;

import java.sql.Connection;

import accounting.biz.pub.IntstBiz;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.AfferentDomain;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;

/**
 * 
 * @项目名称：nxnxnew
 * @类名称：CalIntstOp
 * @类描述： 提前还款利息试算
 * 
 * 
 */

public class CalIntstOp extends Operation {

	@Override
	public Object doExecute(AfferentDomain afferentDomain) throws AccountingException{
		CalIntst intst;
		if (afferentDomain instanceof CalIntst) {
			// 获取传递业务数据的对象
			intst = (CalIntst) afferentDomain;
		} else {
			throw new AccountingException("参数类型不匹配");
		}

		Connection conn = this.getConnection();
		String traceNo = this.getTraceNo() ;
		OperaInfo operaInfo = new OperaInfo(afferentDomain, traceNo, conn) ;
		
		String loanNo = intst.getLoanNo();				// 借据号
		double remAmt = intst.getRemAmt();				// 提前还本金额
		AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
//		PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());
		PrdtBase prdtBase =(PrdtBase)JdbcDao.query(new PrdtBase(), "prdt_no = '"+acLnMst.getPrdtNo()+"'", "prdt_base", operaInfo.getConn());
		
		IntstDetailDatas datas = new IntstDetailDatas();

		try {
			datas = IntstBiz.calculateTtlIntIsMortgage(acLnMst, remAmt, prdtBase, operaInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		System.out.println("当前交易日期-->" + operaInfo.getBizDt());
		System.out.println("系统日期-->" + operaInfo.getTxDt());
		System.out.println("欠款本金-->" + datas.getPrcpAmt());//欠本
		System.out.println("欠正常利息-->" + datas.getNormInt());//欠息
		System.out.println("逾期罚息-->" + datas.getFineInt());
		System.out.println("提前还本金额-->" + datas.getRemPrcpAmt());//还本金额
		System.out.println("还款日所在的当期利息-->" + datas.getCurInt());//归还利息111
		System.out.println("应还利息-->" + NumberUtil.amtAdd(datas.getCurInt(), datas.getFineInt()));
		
		
		return datas;
	}

}
