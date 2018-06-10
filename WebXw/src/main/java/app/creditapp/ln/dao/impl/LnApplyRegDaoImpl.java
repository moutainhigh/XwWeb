package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnApplyRegDao;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.LnApplyReg;
/**
* Title: LnApplyRegDaoImpl.java
* Description:
**/
public class LnApplyRegDaoImpl extends BaseIbatisDao implements LnApplyRegDao {

	public void del(LnApplyReg lnApplyReg) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnApplyReg.del", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnApplyReg> findByPage(LnApplyReg lnApplyReg) throws DAOException {
		List lnApplyRegList = null;
		try {
			lnApplyRegList = getSqlMapClientTemplate().queryForList(
					"LnApplyReg.findByPage", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyRegList;
	}

	public LnApplyReg getById(LnApplyReg lnApplyReg) throws DAOException {
		try {
			lnApplyReg = (LnApplyReg) getSqlMapClientTemplate()
					.queryForObject("LnApplyReg.getById", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyReg;
	}

	public int getCount(LnApplyReg lnApplyReg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApplyReg.findPage.count", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//接口 5101 账户开户使用
	public LnApplyReg getByPact(LnApplyReg lnApplyReg) throws DAOException {
		try {
			lnApplyReg = (LnApplyReg) getSqlMapClientTemplate()
					.queryForObject("LnApplyReg.findPageFor5101", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyReg;
	}
	public void insert(LnApplyReg lnApplyReg) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnApplyReg.insert",
					lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnApplyReg lnApplyReg) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnApplyReg.update",
					lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * 插入自动审批返回resultId到字段if_flag
	 */
	public int resultIdUpdate(LnApplyReg lnApplyReg) throws DAOException{
		int result = 0;
		try {
			result = getSqlMapClientTemplate().update("LnApplyReg.resultIdUpdate",
					lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public String getKey() throws DAOException {
		String lnSeq = "";
		try {
			lnSeq = (String) getSqlMapClientTemplate()
					.queryForObject("LnApplyReg.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnSeq;
	}

	@Override
	public String getByNo(LnApplyReg lnApplyReg) {
		// TODO Auto-generated method stub
		String t= null;
		try {
			t = (String) getSqlMapClientTemplate()
					.queryForObject("LnApplyReg.getByNo",lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return t;
	}

	@Override
	public LnApplyReg getBypactNo(LnApplyReg lnApplyReg) {
		// TODO Auto-generated method stub
		try {
			lnApplyReg = (LnApplyReg) getSqlMapClientTemplate()
					.queryForObject("LnApplyReg.getBypactNo", lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyReg;
	}

	@Override
	public void updateBypactNo(LnApplyReg lnApplyReg) {
		// TODO Auto-generated method stub
		try {
			getSqlMapClientTemplate().update("LnApplyReg.updateBypactNo",
					lnApplyReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}