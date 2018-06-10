package  app.creditapp.proj.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.ProjAcctDao;
import app.creditapp.proj.entity.ProjAcct;
/**
* Title: ProjAcctDaoImpl.java
* Description:
**/
public class ProjAcctDaoImpl extends BaseIbatisDao implements ProjAcctDao {

	public void del(ProjAcct projAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ProjAcct.del", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ProjAcct> findByPage(ProjAcct projAcct) throws DAOException {
		List projAcctList = null;
		try {
			projAcctList = getSqlMapClientTemplate().queryForList(
					"ProjAcct.findByPage", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctList;
	}
	public void getInfForAcct(ProjAcct projAcct)throws DAOException {
		try {
			getSqlMapClientTemplate().insert(
					"ProjAcct.findByPageForAcct", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<ProjAcct> findByPageForPop(ProjAcct projAcct) throws DAOException {
		List projAcctList = null;
		try {
			projAcctList = getSqlMapClientTemplate().queryForList(
					"ProjAcct.findByPageForPop", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctList;
	}
	public ProjAcct getById(ProjAcct projAcct) throws DAOException {
		try {
			projAcct = (ProjAcct) getSqlMapClientTemplate()
					.queryForObject("ProjAcct.getById", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcct;
	}
	public String getProjId(ProjAcct projAcct)throws DAOException{
		String projId = "";
		try {
			projId = (String)getSqlMapClientTemplate().queryForObject("ProjAcct.getProjId", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projId;
	}
	public ProjAcct getInterfaceById(ProjAcct projAcct) throws DAOException {
		try {
			projAcct = (ProjAcct) getSqlMapClientTemplate()
					.queryForObject("ProjAcct.getInterfaceById", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcct;
	}
	public ProjAcct getByProjNoAndAcctType(ProjAcct projAcct) throws DAOException {
		try {
			projAcct = (ProjAcct) getSqlMapClientTemplate()
					.queryForObject("ProjAcct.getByProjNoAndAcctType", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcct;
	}
	
	public ProjAcct getByProjNoAndAcctTypeAndChn(ProjAcct projAcct) throws DAOException {
		try {
			projAcct = (ProjAcct) getSqlMapClientTemplate()
					.queryForObject("ProjAcct.getByProjNoAndAcctTypeAndChn", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcct;
	}
	public List<ProjAcct> findByProjNoAndAcctType(ProjAcct projAcct) throws DAOException {
		List projAcctList = null;
		try {
			projAcctList =  getSqlMapClientTemplate()
					.queryForList("ProjAcct.getByProjNoAndAcctType", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctList;
	}	
	public int getCount(ProjAcct projAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcct.findPage.count", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//<!--判断  某渠道的虚拟账户是否存在，存在的话不能进行新增-->
	public int getCountForX(ProjAcct projAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcct.findPage.countForX", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	
	public int getCountForPop(ProjAcct projAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcct.findPageForPop.count", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ProjAcct projAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ProjAcct.insert",
					projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ProjAcct projAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjAcct.update",
					projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(ProjAcct projAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjAcct.updateSts",
					projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	

	public void updateAcctBal(ProjAcct projAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjAcct.updateAcctBal",
					projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<ProjAcct> findByPageQuotaForCorp(ProjAcct projAcct)
			throws DAOException {
		List projAcctList = null;
		try {
			projAcctList = getSqlMapClientTemplate().queryForList(
					"ProjAcct.findByPageQuotaForCorp", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctList;
	}
	@Override
	public List<ProjAcct> findListByProjNo(ProjAcct projAcct)
			throws DAOException {
		List projAcctList = null;
		try {
			projAcctList = getSqlMapClientTemplate().queryForList(
					"ProjAcct.findListByProjNo", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctList;
	}
	@Override
	public int getCountQuotaForCorp(ProjAcct projAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcct.findByPageQuotaForCorp.count", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public String getKey() throws DAOException {
		String projSeq = "";
		try {
			projSeq = (String) getSqlMapClientTemplate()
					.queryForObject("ProjAcct.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projSeq;
	}
	//获取软通TA借据视图VW_ACT_ACCOUNTINFO中部分信息
	public ProjAcct VW_ACT_ACCOUNTINFO(ProjAcct projAcct) throws DAOException {
		try {
			projAcct = (ProjAcct) getSqlMapClientTemplate()
					.queryForObject("VW_ACT_ACCOUNTINFO.getByprojno", projAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcct;
	}

	@Override
	public int getByProjNo(ProjAcct acct) throws DAOException {
		int size = 0;
		try{
		size = (Integer) getSqlMapClientTemplate()
		.queryForObject("ProjAcct.getByProjNo", acct);
	} catch (Exception e) {
		log.error(e);
		throw new DAOException(e.getMessage());
	}
		return size;
	}
}