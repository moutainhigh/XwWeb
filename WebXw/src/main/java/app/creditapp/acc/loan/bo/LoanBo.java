package app.creditapp.acc.loan.bo;

import app.base.ServiceException;
import app.creditapp.ln.entity.LnDue;

/**
 * 
 * 
 */
public interface LoanBo {

	// 放款
	public String grantLoan(LnDue lnDue)
			throws ServiceException;
	
	// 放款
		public String grantLoan(String brNo)throws ServiceException;
	
	// 放款反馈
		public String grantLoanBack(String backLogNo,String backCnt,String backRes,String failCaus,String PayStatus,String cardChn)
				throws ServiceException;
	

}
