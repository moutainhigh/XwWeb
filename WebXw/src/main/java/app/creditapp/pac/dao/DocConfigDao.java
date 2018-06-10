package  app.creditapp.pac.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.pac.entity.DocConfig;
/**
* Title: DocConfigDao.java
* Description:
**/
public interface DocConfigDao {

	public DocConfig getById(DocConfig docConfig) throws DAOException;

	public void del(DocConfig docConfig) throws DAOException;

	public void insert(DocConfig docConfig) throws DAOException;
	
	public String getKey()throws DAOException;

	public void update(DocConfig docConfig) throws DAOException;
	
	public int getCount(DocConfig docConfig) throws DAOException;
	
	public DocConfig findDocTypeNo(DocConfig docConfig) throws DAOException;
	
	public List<DocConfig > findByPage(DocConfig docConfig) throws DAOException;

	public DocConfig getDocType(DocConfig docConfig)throws DAOException;
	
}