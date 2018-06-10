package app.creditapp.ln.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LoanCountDao;
import app.creditapp.ln.entity.LoanCount;

public class LoanCountDaoImpl extends BaseIbatisDao implements LoanCountDao {

	@Override
	public int getCount(LoanCount loanCount) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"Loan.findPage.count", loanCount);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<LoanCount> findByPage(LoanCount loanCount) throws DAOException {
		List LoanList= null;
		try {
			LoanList = getSqlMapClientTemplate().queryForList(
					"Loan.findByPage", loanCount);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return LoanList;
	}

}
