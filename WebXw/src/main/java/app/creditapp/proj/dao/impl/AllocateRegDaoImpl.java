package  app.creditapp.proj.dao.impl;
import java.util.List;
import java.util.Map;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.AllocateRegDao;
import app.creditapp.proj.entity.AllocateReg;
/**
* Title: AllocateRegDaoImpl.java
* Description:
**/
public class AllocateRegDaoImpl extends BaseIbatisDao implements AllocateRegDao {

	public void del(AllocateReg allocateReg) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AllocateReg.del", allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AllocateReg> findByPage(AllocateReg allocateReg) throws DAOException {
		List allocateRegList = null;
		try {
			allocateRegList = getSqlMapClientTemplate().queryForList(
					"AllocateReg.findByPage", allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return allocateRegList;
	}

	public AllocateReg getById(AllocateReg allocateReg) throws DAOException {
		try {
			allocateReg = (AllocateReg) getSqlMapClientTemplate()
					.queryForObject("AllocateReg.getById", allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return allocateReg;
	}

	public int getCount(AllocateReg allocateReg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AllocateReg.findPage.count", allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AllocateReg allocateReg) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AllocateReg.insert",allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AllocateReg allocateReg) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AllocateReg.update",
					allocateReg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}