package  app.creditapp.corp.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.corp.entity.CorpCert;
/**
* Title: CorpCertDao.java
* Description:
**/
public interface CorpCertDao {

	public CorpCert getById(CorpCert corpCert) throws DAOException;

	public void del(CorpCert corpCert) throws DAOException;

	public void insert(CorpCert corpCert) throws DAOException;

	public void update(CorpCert corpCert) throws DAOException;
	
	public int getCount(CorpCert corpCert) throws DAOException;

	public List<CorpCert > findByPage(CorpCert corpCert) throws DAOException;

	public int getCountQuotaForCorp(CorpCert corpCert)throws DAOException;

	public List<CorpCert> findByPageQuotaForCorp(CorpCert corpCert)throws DAOException;

	public String getKey()throws DAOException;

}