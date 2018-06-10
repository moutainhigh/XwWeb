package  app.creditapp.corp.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.corp.entity.CorpGdinfo;
/**
* Title: CorpGdinfoDao.java
* Description:
**/
public interface CorpGdinfoDao {

	public CorpGdinfo getById(CorpGdinfo corpGdinfo) throws DAOException;

	public void del(CorpGdinfo corpGdinfo) throws DAOException;

	public void insert(CorpGdinfo corpGdinfo) throws DAOException;

	public void update(CorpGdinfo corpGdinfo) throws DAOException;
	
	public int getCount(CorpGdinfo corpGdinfo) throws DAOException;

	public List<CorpGdinfo > findByPage(CorpGdinfo corpGdinfo) throws DAOException;

	public int getCountQuotaForCorp(CorpGdinfo corpGdinfo)throws DAOException;

	public List<CorpGdinfo> findByPageQuotaForCorp(CorpGdinfo corpGdinfo)throws DAOException;

	public String getKey()throws DAOException;

}