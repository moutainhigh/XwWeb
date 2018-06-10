package  app.creditapp.acc.loan.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcDebitDao;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.inf.entity.WsOut2305_1;
/**
* Title: AcDebitDaoImpl.java
* Description:
**/
public class AcDebitDaoImpl extends BaseIbatisDao implements AcDebitDao {

	@Override
	public void delByLoanNoAndSts(AcDebit acDebit) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcDebit.delByLoanNoAndSts", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	public void del(AcDebit acDebit) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcDebit.del", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcDebit> findByPage(AcDebit acDebit) throws DAOException {
		List acDebitList = null;
		try {
			acDebitList = getSqlMapClientTemplate().queryForList(
					"AcDebit.findByPage", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitList;
	}

	public AcDebit getById(AcDebit acDebit) throws DAOException {
		try {
			acDebit = (AcDebit) getSqlMapClientTemplate()
					.queryForObject("AcDebit.getById", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebit;
	}
	
	public AcDebit getByLoanNoIng(AcDebit acDebit) throws DAOException {
		try {
			acDebit = (AcDebit) getSqlMapClientTemplate()
					.queryForObject("AcDebit.getByLoanNoIng", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebit;
	}
	
	public AcDebit getByDebitNo(AcDebit acDebit) throws DAOException {
		try {
			acDebit = (AcDebit) getSqlMapClientTemplate()
					.queryForObject("AcDebit.getByDebitNo", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebit;
	}

	public int getCount(AcDebit acDebit) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcDebit.findPage.count", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//获取符合条件的 2305 结果
	public int getCountFor2305(AcDebit acDebit) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcDebit.findPage.countFor2305", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//接口 2305 返回符合条件的list
	public List<WsOut2305_1> findByPageFor2305(AcDebit acDebit) throws DAOException {
		List acDebitList = null;
		try {
			acDebitList = getSqlMapClientTemplate().queryForList(
					"AcDebit.findByPageFor2305", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitList;
	}

	public void insert(AcDebit acDebit) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcDebit.insert",
					acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcDebit acDebit) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcDebit.update",
					acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//查询需要发送的定时扣款
	@Override
	public List<AcDebit > timDebit(AcDebit acDebit) throws DAOException {
		List acDebitList = null;
		try {
			acDebitList = getSqlMapClientTemplate().queryForList(
					"AcDebit.findTimDebit", acDebit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitList;
		
	}
}