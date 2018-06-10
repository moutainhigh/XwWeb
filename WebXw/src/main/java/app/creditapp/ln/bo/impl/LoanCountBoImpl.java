package app.creditapp.ln.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LoanCountBo;
import app.creditapp.ln.dao.LoanCountDao;
import app.creditapp.ln.entity.LoanCount;
import app.util.toolkit.Ipage;

public class LoanCountBoImpl  extends BaseService implements LoanCountBo {
	
	private LoanCountDao loanCountDao;

	@Override
	public Ipage findByPage(Ipage ipg, LoanCount loanCount)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) loanCountDao
					.getCount(loanCount) });// 初始化分页类
			loanCount.setStartNumAndEndNum (ipg);
			ipg.setResult(loanCountDao.findByPage(loanCount));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LoanCountDao getLoanCountDao() {
		return loanCountDao;
	}

	public void setLoanCountDao(LoanCountDao loanCountDao) {
		this.loanCountDao = loanCountDao;
	}

}
