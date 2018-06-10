package app.creditapp.ln.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.DebitCountDao;
import app.creditapp.ln.entity.DebitCount;

public class DebitCountDaoImpl extends BaseIbatisDao implements DebitCountDao {

	@Override
	public int getCount(DebitCount debitCount) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"Debit.findPage.count", debitCount);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<DebitCount> findByPage(DebitCount debitCount)
			throws DAOException {
		List debitList = null;
		try {
			debitList = getSqlMapClientTemplate().queryForList(
					"Debit.findByPage", debitCount);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return debitList;
	}

}
