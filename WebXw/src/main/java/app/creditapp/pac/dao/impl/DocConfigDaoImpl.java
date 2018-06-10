package  app.creditapp.pac.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.pac.dao.DocConfigDao;
import app.creditapp.pac.entity.DocConfig;
/**
* Title: DocConfigDaoImpl.java
* Description:
**/
public class DocConfigDaoImpl extends BaseIbatisDao implements DocConfigDao {
	
	
	
	public DocConfig findDocTypeNo(DocConfig docConfig) throws DAOException {
		try{
			docConfig = (DocConfig) getSqlMapClientTemplate().queryForObject("DocConfig.findDocTypeNo", docConfig);
		}catch(Exception e){
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return docConfig;
	}

	public void del(DocConfig docConfig) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("DocConfig.del", docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<DocConfig> findByPage(DocConfig docConfig) throws DAOException {
		List docConfigList = null;
		try {
			docConfigList = getSqlMapClientTemplate().queryForList(
					"DocConfig.findByPage", docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return docConfigList;
	}

	public DocConfig getById(DocConfig docConfig) throws DAOException {
		try {
			docConfig = (DocConfig) getSqlMapClientTemplate()
					.queryForObject("DocConfig.getById", docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return docConfig;
	}

	public int getCount(DocConfig docConfig) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"DocConfig.findPage.count", docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(DocConfig docConfig) throws DAOException {
		try {
			
			getSqlMapClientTemplate().insert("DocConfig.insert",
					docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(DocConfig docConfig) throws DAOException {
		try {
			getSqlMapClientTemplate().update("DocConfig.update",
					docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	
	public String getKey() throws DAOException {

		String docCoSeq=" ";
		 try {
			 docCoSeq=(String)getSqlMapClientTemplate().queryForObject("DocConfig.getKey");
			
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return docCoSeq;
	}

	@Override
	public DocConfig getDocType(DocConfig docConfig) throws DAOException {
		try {
			docConfig = (DocConfig) getSqlMapClientTemplate()
					.queryForObject("DocConfig.getDocType", docConfig);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return docConfig;
	}

	

	
	
}