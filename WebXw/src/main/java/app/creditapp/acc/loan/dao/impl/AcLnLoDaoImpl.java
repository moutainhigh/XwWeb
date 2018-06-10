package  app.creditapp.acc.loan.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLnLoDao;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.inf.entity.WsOut3203_1;
/**
* Title: AcLnLoDaoImpl.java
* Description:
**/
public class AcLnLoDaoImpl extends BaseIbatisDao implements AcLnLoDao {

	public void del(AcLnLo acLnLo) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnLo.del", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnLo> findByPage(AcLnLo acLnLo) throws DAOException {
		List acLnLoList = null;
		try {
			acLnLoList = getSqlMapClientTemplate().queryForList(
					"AcLnLo.findByPage", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLoList;
	}
	
	public List<AcLnLo> getListByLoanNo(AcLnLo acLnLo) throws DAOException {
		List acLnLoList = null;
		try {
			acLnLoList = getSqlMapClientTemplate().queryForList(
					"AcLnLo.getListByLoanNo", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLoList;
	}

	public AcLnLo getById(AcLnLo acLnLo) throws DAOException {
		try {
			acLnLo = (AcLnLo) getSqlMapClientTemplate()
					.queryForObject("AcLnLo.getById", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLo;
	}
	
	public AcLnLo getMinLnLo(AcLnLo acLnLo) throws DAOException {
		try {
			acLnLo = (AcLnLo) getSqlMapClientTemplate()
					.queryForObject("AcLnLo.getMinLnLo", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLo;
	}

	public int getCount(AcLnLo acLnLo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnLo.findPage.count", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnLo acLnLo) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnLo.insert",
					acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnLo acLnLo) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnLo.update",
					acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public AcLnLo getLoAmt(AcLnLo acLnLo) throws DAOException {
		try {
			acLnLo = (AcLnLo) getSqlMapClientTemplate()
					.queryForObject("AcLnLo.getLoAmt", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLo;
	}
	//接口ws3202查询返回列表
	public List<WsOut3203_1> findByPageforws3203(AcLnLo acLnLo) throws DAOException {
		List<WsOut3203_1> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(
					"AcLnLo.findByPageforws3203", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}
	//接口ws3202查询返回 符合条件的数量
	public int getCountfor3203(AcLnLo acLnLo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnLo.findPage.countforws3203", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	//根据借据号 合同号 机构号取数据
	public List<AcLnLo> getByLoanPactBrNo(AcLnLo acLnLo) throws DAOException{
		List acLnLoList = null;
		try {
			acLnLoList = getSqlMapClientTemplate()
					.queryForList("AcLnLo.getByLoanPactBrNo", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLoList;
	}

	@Override
	public AcLnLo getAcLnLoByCnt(AcLnLo acLnLo) {
		try {
			acLnLo = (AcLnLo) getSqlMapClientTemplate()
					.queryForObject("AcLnLo.getAcLnLoByCnt", acLnLo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnLo;
	}
}