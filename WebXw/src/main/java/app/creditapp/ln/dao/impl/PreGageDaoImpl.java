package  app.creditapp.ln.dao.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2006_1_1;
import app.creditapp.ln.dao.PreGageDao;
import app.creditapp.ln.entity.PreGage;
/**
* Title: PreGageDaoImpl.java
* Description:
**/
public class PreGageDaoImpl extends BaseIbatisDao implements PreGageDao {

	public void del(PreGage preGage) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("PreGage.del", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<PreGage> findByPage(PreGage preGage) throws DAOException {
		List preGageList = null;
		try {
			preGageList = getSqlMapClientTemplate().queryForList(
					"PreGage.findByPage", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preGageList;
	}

	public PreGage getById(PreGage preGage) throws DAOException {
		try {
			preGage = (PreGage) getSqlMapClientTemplate()
					.queryForObject("PreGage.getById", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preGage;
	}
	//接口ws2006  List<gage>
	public List<WsOut2006_1_1> getws2006_1_1list(PreGage preGage) throws DAOException {
		List<WsOut2006_1_1> wsOut2006_1_1list = new  ArrayList<WsOut2006_1_1>();
		try {
			wsOut2006_1_1list = (List<WsOut2006_1_1>) getSqlMapClientTemplate()
					.queryForList("PreGage.getByIdforws2006", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsOut2006_1_1list;
	}
	//结束

	public int getCount(PreGage preGage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PreGage.findPage.count", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(PreGage preGage) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PreGage.insert",
					preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	//接口wsIn2004_1插入
	public void insertfor2004_1(WsIn2004_1 wsIn2004_1) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsIn2004_1.insert",
					wsIn2004_1);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	
	

	public void update(PreGage preGage) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PreGage.update",
					preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<PreGage> findByPageQuotaForLn(PreGage preGage) throws DAOException {
		List preGageList = null;
		try {
			preGageList = getSqlMapClientTemplate().queryForList(
					"PreGage.findByPageQuotaForLn", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preGageList;
	}

	@Override
	public int getCountQuotaForLn(PreGage preGage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PreGage.findByPageQuotaForLn.count", preGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}