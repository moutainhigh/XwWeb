package  app.creditapp.inf.dao.impl;
import java.util.List;
import java.util.Map;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.AllocateRegWsInDao;
import app.creditapp.inf.entity.AllocateRegWsIn;
/**
* Title: AllocateRegWsInDaoImpl.java
* Description:
**/
public class AllocateRegWsInDaoImpl extends BaseIbatisDao implements AllocateRegWsInDao {

	public void del(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AllocateRegWsIn.del", allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AllocateRegWsIn> findByPage(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		List allocateRegWsInList = null;
		try {
			allocateRegWsInList = getSqlMapClientTemplate().queryForList(
					"AllocateRegWsIn.findByPage", allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return allocateRegWsInList;
	}

	public AllocateRegWsIn getById(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		try {
			allocateRegWsIn = (AllocateRegWsIn) getSqlMapClientTemplate()
					.queryForObject("AllocateRegWsIn.getById", allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return allocateRegWsIn;
	}

	public int getCount(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AllocateRegWsIn.findPage.count", allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public String getByid() throws DAOException {
		String count = "";
		try {
			count = (String) getSqlMapClientTemplate().queryForObject(
					"AllocateRegWsIn.Byid");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AllocateRegWsIn.insert",
					allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AllocateRegWsIn allocateRegWsIn) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AllocateRegWsIn.update",
					allocateRegWsIn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}